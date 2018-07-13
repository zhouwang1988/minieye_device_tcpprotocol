package com.cictec.middleware.tsinghua.tcp.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.cictec.middleware.tsinghua.entity.dto.DataUploadDTO;
import com.cictec.middleware.tsinghua.entity.dto.RabbitMqClientDTO;
import com.cictec.middleware.tsinghua.entity.po.AlarmType;
import com.cictec.middleware.tsinghua.model.vo.Alarm;
import com.cictec.middleware.tsinghua.model.vo.AlarmStatus;
import com.cictec.middleware.tsinghua.model.vo.MediaPacket;
import com.cictec.middleware.tsinghua.service.AlarmTypeService;
import com.cictec.middleware.tsinghua.tcp.annotation.HandleMessages;
import com.cictec.middleware.tsinghua.tcp.handler.base.AbstractReceiverHandler;
import com.cictec.middleware.tsinghua.tcp.message.TerminalMessage;
import com.cictec.middleware.tsinghua.tcp.message.request.TerminalDataUploadRequest;
import com.cictec.middleware.tsinghua.tcp.message.response.PlatformGeneralResponse;
import com.cictec.middleware.tsinghua.utils.BinaryUtils;
import com.cictec.middleware.tsinghua.utils.CamelRabbitMqDslUtils;
import com.cictec.middleware.tsinghua.utils.DateUtils;
import com.cictec.middleware.tsinghua.utils.DaxianStringUtils;
import com.cictec.middleware.tsinghua.utils.FileUtils;
import com.cictec.middleware.tsinghua.utils.SubcontractUtil;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import net.sf.json.JSONObject;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.camel.ProducerTemplate;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 位置信息汇报类TerminalDataUploadHandler类
 * 
 * @file TerminalDataUploadHandler.java
 * @author mjj
 */
@HandleMessages(0x0801)
@Component
public class TerminalDataUploadHandler extends AbstractReceiverHandler {

	private static Logger logger = LoggerFactory.getLogger(TerminalDataUploadHandler.class);

//	缓存
	public Map<String, Map<Integer, MediaPacket>> mediaPackets = new ConcurrentHashMap<>();
	
	//存储临时文件
	public Map<String, File> mePackets = new ConcurrentHashMap<>();

	@Autowired
	private AlarmTypeService alarmTypeService;
	
	//临时文件存储地址
	@Value("${subpackage.data.direct}")
    private String direct;
	
	@Value("${subpackage.data.image}")
    private String imagePath;
	
	@Value("${mq.webrooturl}")
	private String webRootUrl;
	
	@Autowired
    private ProducerTemplate producerTemplate;
	
	private String httpDownloadDsl;
	@Value("${media.file.save-model}")
    private String saveModel;
    @Value("${rabbitmq.download.host}")
    private String host;
    @Value("${rabbitmq.download.port}")
    private String port;
    @Value("${rabbitmq.download.exchangename}")
    private String exchangename;
    @Value("${rabbitmq.download.username}")
    private String username;
    @Value("${rabbitmq.download.password}")
    private String password;
    @Value("${rabbitmq.download.queuename}")
    private String queuename;
	
	
	@Override
	public void handleMessage(TerminalMessage msg, IoSession session) {

		TerminalDataUploadRequest tcr = (TerminalDataUploadRequest) msg;
		logger.info("终端 {}  Session {} 位置信息 ", msg.getTerminalId(), msg.getSessionId());
		// 判断是否分包，整合数据，将分包的数据整理再一个对象中
		TerminalDataUploadRequest tcr2;
		try {
			//加工数据对象
			tcr2 = integrationData(msg, tcr);
			
			//存储数据
			String fielPath = saveData(tcr2);
			//消息推送MQ
			dataUploadToMQ(tcr2,fielPath);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 保存数据
		logger.debug("共{}个包，第{}个包的长度为{} ",tcr.getHeader().getPackageCounts(),tcr.getHeader().getPackageNum(),tcr.getMultimediaPacket().length);;
		tcr.getHeader().setTerminalId(msg.getTerminalId());
		TerminalMessage.Header dataUploadResponseHeader = new TerminalMessage.Header(0x8001);
		dataUploadResponseHeader.setTerminalId(tcr.getHeader().getTerminalId());
		dataUploadResponseHeader.setMessageSequence(tcr.getHeader().getMessageSequence());
		PlatformGeneralResponse dataUploadResponse = new PlatformGeneralResponse(dataUploadResponseHeader);

		dataUploadResponse.setResponseMsgId(tcr.getHeader().getMessageId());
		dataUploadResponse.setResponseSequence(tcr.getHeader().getMessageSequence());

		dataUploadResponse.setResponseResult(PlatformGeneralResponse.RESPONSE_RESULT_SUCCESS);
		sendMessageToTerminal(dataUploadResponse, session);
		
	}

	private void dataUploadToMQ(TerminalDataUploadRequest tcr, String filePath) {
		DataUploadDTO duDto = new DataUploadDTO();
		if (tcr!=null) {
			//报警说明数组
			duDto.setAlarmSet(alarmStrArr(tcr.getTerminalLocationRequest().getWarnMark()));
			duDto.setAltitude(tcr.getTerminalLocationRequest().getElevation());//海拔高度
			duDto.setAngle(tcr.getTerminalLocationRequest().getDirection());//角度，方向
			duDto.setChannelId(tcr.getChannelID());//证据上传通道
			duDto.setHexDevIdno(tcr.getTerminalId());//设备号
			duDto.setHexFlowNo(Integer.toHexString(tcr.getHeader().getMessageSequence()));//消息流水号
			duDto.setHexLocationBuf(tcr.getTerminalLocationRequest().getHexLocationBuf());//定位消息
			duDto.setHexMediaId(Integer.toHexString(tcr.getMultimediaID()));
			duDto.setHexMsgId(BinaryUtils.addZeroForNum(Integer.toHexString(tcr.getHeader().getMessageId()),4));//消息ID
			duDto.setItemEncoding(Integer.parseInt(tcr.getMultimediaEventCode()));//事件项编码
			duDto.setMediaEncoding(tcr.getMultimediaCode());//多媒体格式
			duDto.setMediaType(tcr.getMultimediaType());//多媒体类型
			
			duDto.setMediaUrl(webRootUrl+"/image/"+filePath);//证据HTTP地址
			
			duDto.setLat(tcr.getTerminalLocationRequest().getLat()+"");
			duDto.setLng(tcr.getTerminalLocationRequest().getLng()+"");
			duDto.setMile(0);//本次行程里程
			duDto.setSpeed(tcr.getTerminalLocationRequest().getSpeed());//速度
			duDto.setStatusSet(alarmStatusStrArr(tcr.getTerminalLocationRequest().getStatus(),duDto.getAlarmSet().length>0));//状态说明
			duDto.setYyMMddHHmmss(DateUtils.formatDate(tcr.getTerminalLocationRequest().getTime(),"yyMMddHHmmss"));
			
			logger.info("推送多媒体下载消息到MQ：{}",JSON.toJSONString(duDto));
	        producerTemplate.sendBody(getHttpDownloadDsl(), JSON.toJSONString(duDto));
			
		}else {
			return;
		}
	}
	public String getHttpDownloadDsl(){
        if (httpDownloadDsl == null || httpDownloadDsl.equals("")){
            httpDownloadDsl = createHttpDownlodDsl();
        }
        return httpDownloadDsl;
    }
	
	private String createHttpDownlodDsl(){

        RabbitMqClientDTO rabbitMqClientDTO = new RabbitMqClientDTO(
                host,
                port,
                exchangename,
                username,
                password,
                queuename
        );

        return CamelRabbitMqDslUtils.getCamelUrl(rabbitMqClientDTO);
    }

	private String[] alarmStatusStrArr(AlarmStatus alarmStatus,boolean boo) {
		String [] alarmStatusArr;
		List<String> alarmStatusList = new ArrayList<>();
		if(alarmStatus.getACCSwitch()==1){alarmStatusList.add("ACC开");}else{alarmStatusList.add("ACC关");}//0 0：ACC 关；1： ACC 开
		if(alarmStatus.getGPSSwitch()==1){alarmStatusList.add("定位");}else{alarmStatusList.add("未定位");}//1 0：未定位；1：定位
		if(alarmStatus.getLatitude()==1){alarmStatusList.add("南纬");}else{alarmStatusList.add("北纬");}//2 0：北纬；1：南纬1
		if(alarmStatus.getLongitude()==1){alarmStatusList.add("西经");}else{alarmStatusList.add("东经");}//3 0：东经；1：西经
		if(alarmStatus.getSuperheavy()==1){alarmStatusList.add("超重");}//6 超重
		if(alarmStatus.getOverload()==1){alarmStatusList.add("超载");}//7 超载
		
		if(alarmStatus.getPressureHigh()==1){alarmStatusList.add("胎压过高");}//22 胎压过高
		if(alarmStatus.getPressureLow()==1){alarmStatusList.add("胎压过低");}//23 胎压过低
		if (boo) {//alarmSet不为空时存在
			if (alarmStatus.getEvidence() == 1) {
				alarmStatusList.add("有证据");
				if (alarmStatus.getEvidencePhoteOrVideo() == 1) {
					alarmStatusList.add("视频证据");
				} else {
					alarmStatusList.add("图片证据");
				}//25 0：图片证据；1：视频证据      alarmSet不为空并且有证据时存在
			} else {
				alarmStatusList.add("无证据");
			} // 24 0：无证据；1：有证据
		}
		if(alarmStatus.getShield()==1){alarmStatusList.add("遮挡");}//26 遮挡
		if(alarmStatus.getSpeedUp()==1){alarmStatusList.add("急加速");}//27 急加速
		if(alarmStatus.getSpeedCut()==1){alarmStatusList.add("急减速");}//28 急减速
		if(alarmStatus.getSharpBend()==1){alarmStatusList.add("急转弯");}//29 急转弯
		if(alarmStatus.getDrunkDriving()==1){alarmStatusList.add("酒驾");}//30 酒驾
		alarmStatusArr = alarmStatusList.toArray(new String[alarmStatusList.size()]);  
		return alarmStatusArr;
	}
	
	private Object getFieldValueByName(String fieldName, Object o) {
		try {
			String firstLetter = fieldName.substring(0, 1).toUpperCase();
			String getter = "get" + firstLetter + fieldName.substring(1);
			Method method = o.getClass().getMethod(getter, new Class[] {});
			Object value = method.invoke(o, new Object[] {});
			return value;
		} catch (Exception e) {
			return null;
		}
	}

	private String[] alarmStrArr(Alarm alarm) {
//		String [] alarmArr;
//		List<String> alarmList = new ArrayList<>();
		
		/*AlarmType alarmType = new AlarmType();
		alarmType.setCode("B003");
		List<AlarmType> alarmTypes = alarmTypeService.findAllTypes(alarmType);
		Map<String, String> alarmTypeMap = new HashMap<>();
		for (int i = 0; i < alarmTypes.size(); i++) {
			alarmTypeMap.put(alarmTypes.get(i).getValue(), alarmTypes.get(i).getDisplay());
		}*/
		
		/*if(alarm.getExigenceAlarmStatus()==1){alarmList.add("exigenceAlarmStatus");}//0 报警状态 1：紧急报警
		if(alarm.getSpeedAlarmStatus()==1){alarmList.add("speedAlarmStatus");}//1 超速报警 1：超速报警
		if(alarm.getTimeoutAlarmStatus()==1){alarmList.add("timeoutAlarmStatus");}//2 超时报警 1：超时报警
		if(alarm.getDangerousAlarmStatus()==1){alarmList.add("dangerousAlarmStatus");}//3 1：危险预警
		if(alarm.getGNSSModuleAlarm()==1){alarmList.add("GNSSModuleAlarm");}//4 GNSS模块发生故障 
		if(alarm.getGNSSAntennaBreakAlarm()==1){alarmList.add("GNSSAntennaBreakAlarm");}//5 1：GNSS 天线未接或被剪断 
		if(alarm.getGNSSAntennaShortAlarm()==1){alarmList.add("GNSSAntennaShortAlarm");}//6 1：GNSS 天线短路 
		if(alarm.getVoltageAlarm()==1){alarmList.add("voltageAlarm");}//7 1：终端主电源欠压 
		if(alarm.getPowerDownAlarm()==1){alarmList.add("powerDownAlarm");}//8 1：终端主电源掉电 
		if(alarm.getLCDAlarm()==1){alarmList.add("LCDAlarm");}//9 1：终端 LCD 或显示器故障
		if(alarm.getTTSAlarm()==1){alarmList.add("TTSAlarm");}//10 1：TTS 模块故障
		if(alarm.getCameraAlarm()==1){alarmList.add("cameraAlarm");}//11 1：摄像头故障 
		if(alarm.getICAlarm()==1){alarmList.add("ICAlarm");}//12 1：道路运输证 IC卡模块故障
		if(alarm.getSpeedEarlyAlarm()==1){alarmList.add("speedEarlyAlarm");}//13 1：超速预警 
		if(alarm.getFatigueDrivingAlarm()==1){alarmList.add("fatigueDrivingAlarm");}//14 1：疲劳驾驶预警 
		
		if(alarm.getCloseEyeAlarm()==1){alarmList.add("closeEyeAlarm");}//闭眼 0 0 1
		if(alarm.getYawnAlarm()==1){alarmList.add("yawnAlarm");}//打哈欠 0 1 0
		if(alarm.getDistractionAlarm()==1){alarmList.add("distractionAlarm");}//注意力分散 0 1 1
		if(alarm.getCallAlarm()==1){alarmList.add("callAlarm");}//打手机 1 0 0
		if(alarm.getSmokingAlarm()==1){alarmList.add("smokingAlarm");}//抽烟 1 0 1
		if(alarm.getForwardCrashAlarm()==1){alarmList.add("forwardCrashAlarm");}//前向碰撞 1 1 0
		
		if(alarm.getDepartureDrivingAlarm()==1){alarmList.add("departureDrivingAlarm");}//车道偏离 1 1 1
		if(alarm.getDrivingTimeoutAlarm()==1){alarmList.add("drivingTimeoutAlarm");}//18 1：当天累计驾驶超时
		if(alarm.getParkingTimeoutAlarm()==1){alarmList.add("parkingTimeoutAlarm");}//19 1：超时停车
		if(alarm.getInAndoutAreaAlarm()==1){alarmList.add("inAndoutAreaAlarm");}//20 1：进出区域
		if(alarm.getInAndoutLineAlarm()==1){alarmList.add("inAndoutLineAlarm");}//21 1：进出路线
		if(alarm.getDrivingTimeShortAlarm()==1){alarmList.add("drivingTimeShortAlarm");}//22 1：路段行驶时间不足/过长
		if(alarm.getLineLeviateAlarm()==1){alarmList.add("lineLeviateAlarm");}//23 1：路线偏离报警 
		if(alarm.getVSSAlarm()==1){alarmList.add("VSSAlarm");}//24 1：车辆 VSS 故障 
		if(alarm.getOilAlarm()==1){alarmList.add("oilAlarm");}//25 1：车辆油量异常
		if(alarm.getStolenBusAlarm()==1){alarmList.add("stolenBusAlarm");}//26 1：车辆被盗(通过车辆防盗器)
		if(alarm.getIgnitionAlarm()==1){alarmList.add("ignitionAlarm");}//27 1：车辆非法点火
		if(alarm.getDisplacementAlarm()==1){alarmList.add("displacementAlarm");}//28 1：车辆非法位移 
		if(alarm.getCrashAlarm()==1){alarmList.add("crashAlarm");}//29 1：碰撞预警 
		if(alarm.getTurnoverAlarm()==1){alarmList.add("turnoverAlarm");}//30 1：侧翻预警
		if(alarm.getOpenDoorAlarm()==1){alarmList.add("openDoorAlarm");}//31 1：非法开门报警

		alarmArr = alarmList.toArray(new String[alarmList.size()]);
		return alarmArr;*/
		
		String [] alarmArr1;
		List<String> alarmList1 = new ArrayList<>();
		Field[] fields = alarm.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			if((int)getFieldValueByName(fields[i].getName(), alarm)>0){
				alarmList1.add(fields[i].getName());
			};
		}
		alarmArr1 = alarmList1.toArray(new String[alarmList1.size()]);
		return alarmArr1;
		
	}

	// 整合数据，将分包的数据整理再一个对象中
	private TerminalDataUploadRequest integrationData(TerminalMessage msg, TerminalDataUploadRequest tcr) throws IOException {
		TerminalDataUploadRequest tcr2 = null;
		if (msg.getHeader().isSubcontract()) {
			//按照存储临时文件的方式
			if (mediaPackets != null && mediaPackets.containsKey(tcr.getHeader().getTerminalId())
					&& tcr.getHeader().getPackageNum() != 1) {
				MediaPacket mediaPacket = new MediaPacket();
				mediaPacket.setPackageCounts(msg.getHeader().getPackageCounts());
				mediaPacket.setPackageNum(tcr.getHeader().getPackageNum());
				//判断上一个包是否已经存在
				if(mediaPackets.get(tcr.getHeader().getTerminalId()).containsKey(tcr.getHeader().getPackageNum()-1) && !(new File(direct+tcr.getHeader().getTerminalId()+"_"+(tcr.getHeader().getPackageNum()-1)+".txt")).exists()){
					//给file中append此包  都是给上一个包中增加
					String fileNameUP =direct+tcr.getHeader().getTerminalId()+".txt";
					FileUtils.write(fileNameUP,true,' '+BinaryUtils.byte2HexStr(tcr.getMultimediaPacket()),false);
					mediaPacket.setMultimediaPacket(new File(fileNameUP));
				}else {
					//存放在临时文件中
					FileUtils.write(direct+tcr.getHeader().getTerminalId()+"_"+tcr.getHeader().getPackageNum()+".txt",false,BinaryUtils.byte2HexStr(tcr.getMultimediaPacket()),false);
					mediaPacket.setMultimediaPacket(new File(direct+tcr.getHeader().getTerminalId()+"_"+tcr.getHeader().getPackageNum()+".txt"));
				}
				//给文件中增加元素
				mediaPackets.get(tcr.getHeader().getTerminalId()).put(tcr.getHeader().getPackageNum(), mediaPacket);
				//map.size()等于包总数时，合并文件并将数据整合在对象中
				if (mediaPackets.get(tcr.getHeader().getTerminalId()).size()==msg.getHeader().getPackageCounts()) {
					//合并文件
					for (int i = 2; i <= tcr.getHeader().getPackageCounts(); i++) {
						if(new File(direct+tcr.getHeader().getTerminalId()+"_"+i+".txt").exists()){
							String mulPacket = FileUtils.txt2String(new File(direct+tcr.getHeader().getTerminalId()+"_"+i+".txt"));
							mulPacket = mulPacket.replaceAll("\r|\n","");//去掉里面的换行符号
							FileUtils.write(direct+tcr.getHeader().getTerminalId()+".txt", true," "+mulPacket, false);
							FileUtils.deleteFile(direct+tcr.getHeader().getTerminalId()+"_"+i+".txt");
						}
					}
					//存储数据对象
					tcr2 = saveFileObj(msg,direct+tcr.getHeader().getTerminalId()+".txt");
					//删除文件
					FileUtils.deleteFile(direct+tcr.getHeader().getTerminalId()+".txt");
				}
				
			} else {
				Map<Integer, MediaPacket> meMap = new HashMap<Integer, MediaPacket>();
				MediaPacket mediaPacket = new MediaPacket();
				TerminalDataUploadRequest tcr1 = new TerminalDataUploadRequest(msg.getHeader());
				// 将第一个包的多媒体ID，多媒体类型，多媒体格式编码，事件项编码，通道ID，位置信息汇报 赋值对象
				tcr1.setMultimediaID(tcr.getMultimediaID());
				tcr1.setMultimediaType(tcr.getMultimediaType());
				tcr1.setMultimediaCode(tcr.getMultimediaCode());
				tcr1.setMultimediaEventCode(tcr.getMultimediaEventCode());
				tcr1.setChannelID(tcr.getChannelID());
				tcr1.setTerminalLocationRequest(tcr.getTerminalLocationRequest());
				mediaPacket.setTerminalDataUploadRequest(tcr1);

				// 将包总数，包序号，数据包封装
				mediaPacket.setPackageCounts(msg.getHeader().getPackageCounts());
				mediaPacket.setPackageNum(tcr.getHeader().getPackageNum());
				//如果此设备的文件已经存在，先删除。   防止上一个包不足
				if(new File(direct+tcr.getHeader().getTerminalId()+".txt").exists()){
					//删除文件
					FileUtils.deleteFile(direct+tcr.getHeader().getTerminalId()+".txt");
				}
				//创建文件
				FileUtils.write(direct+tcr.getHeader().getTerminalId()+".txt",false,BinaryUtils.byte2HexStr(tcr.getMultimediaPacket()),false);
				mediaPacket.setMultimediaPacket(new File(direct+tcr.getHeader().getTerminalId()+".txt"));

				//存储临时文件
				Map<Integer, MediaPacket> meMap1 = new HashMap<Integer, MediaPacket>();
				meMap1.put(tcr.getHeader().getPackageNum(), mediaPacket);
				mediaPackets.put(tcr.getHeader().getTerminalId(), meMap1);
			}
		} else {
			// 不分包直接存储
			tcr2 = tcr;
		}
		return tcr2;
	}

	// 存储数据,返回文件存储地址
	private String saveData(TerminalDataUploadRequest tcr2) {
		if (tcr2 != null) {
			String fielName = tcr2.getTerminalId()+"_"+DateUtils.getDateTimeStr()+"_"+UUID.randomUUID().toString().substring(0, 10);
			String fielPath = fielName;//文件存储地址
			if ("0".equals(tcr2.getMultimediaType()) && "0".equals(tcr2.getMultimediaCode())) {
				// 多媒体类型 0：图像;多媒体格式编码 0：JPEG
				FileUtils.byte2File(tcr2.getMultimediaPacket(), imagePath+DateUtils.getDate("yyyy/MM/dd")+"/", fielName + ".JPEG");
				fielPath = DateUtils.getDate("yyyy/MM/dd")+"/"+fielPath+".JPEG";
//				System.out.println("图片解析：" + BinaryUtils.byte2HexStr(SubcontractUtil.image2byte(imagePath + fielName + ".JPEG"),SubcontractUtil.image2byte(imagePath + fielName + ".JPEG").length, true));
			} else if ("0".equals(tcr2.getMultimediaType()) && "1".equals(tcr2.getMultimediaCode())) {
				// 多媒体类型 0：图像;多媒体格式编码 1:TIF
				fielPath = DateUtils.getDate("yyyy/MM/dd")+"/"+fielPath+".TIF";
				FileUtils.byte2File(tcr2.getMultimediaPacket(), imagePath+DateUtils.getDate("yyyy/MM/dd")+"/", fielName + ".TIF");
			} else if ("1".equals(tcr2.getMultimediaType()) && "2".equals(tcr2.getMultimediaCode())) {
				// 多媒体类型 1：音频;多媒体格式编码 2：MP3
				fielPath = DateUtils.getDate("yyyy/MM/dd")+"/"+fielPath+".MP3";
				FileUtils.byte2File(tcr2.getMultimediaPacket(), imagePath, fielName + ".MP3");
			} else if ("1".equals(tcr2.getMultimediaType()) && "3".equals(tcr2.getMultimediaCode())) {
				// 多媒体类型 1：音频;多媒体格式编码 3：WAV
				fielPath = DateUtils.getDate("yyyy/MM/dd")+"/"+fielPath+".WAV";
				FileUtils.byte2File(tcr2.getMultimediaPacket(), imagePath, fielName + ".WAV");
			} else if ("2".equals(tcr2.getMultimediaType()) && "4".equals(tcr2.getMultimediaCode())) {
				// 多媒体类型 2：视频;多媒体格式编码 4：WMV
				fielPath = DateUtils.getDate("yyyy/MM/dd")+"/"+fielPath+".WMV";
				FileUtils.byte2File(tcr2.getMultimediaPacket(), imagePath, fielName + ".WMV");
			} else if ("2".equals(tcr2.getMultimediaType()) && "5".equals(tcr2.getMultimediaCode())) {
				// 多媒体类型 2：视频;多媒体格式编码5：H264
				fielPath = DateUtils.getDate("yyyy/MM/dd")+"/"+fielPath+".H264";
				FileUtils.byte2File(tcr2.getMultimediaPacket(), imagePath, fielName + ".H264");
			} else {
				System.out.println("其他格式");
			}
			logger.info("类型：{}  多媒体格式编码： {}  事件项编码：{} ",tcr2.getMultimediaType(),tcr2.getMultimediaCode(),tcr2.getMultimediaEventCode());
			logger.info("通道ID：{}  位置信息： {}  长度：{} ",tcr2.getChannelID(),tcr2.getTerminalLocationRequest(),tcr2.getMultimediaPacket().length);
			return fielPath;
		}else{
			return null;
		}
	}

    
 // 分包数据封装在一个对象中
 	private TerminalDataUploadRequest saveFileObj(TerminalMessage msg,String fileName) {
		File file = new File(fileName); // 要读取以上路径的input。txt文件  
        TerminalDataUploadRequest tecr = new TerminalDataUploadRequest(msg.getHeader());
        Map<Integer, MediaPacket> map = mediaPackets.get(tecr.getTerminalId());
        tecr = map.get(1).getTerminalDataUploadRequest();
        tecr.setMultimediaPacket(BinaryUtils.hexStringToBytes(FileUtils.txt2String(file)));
		return tecr;
 	}
}
