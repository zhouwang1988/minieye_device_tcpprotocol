package com.cictec.middleware.tsinghua.tcp.handler;


import com.alibaba.fastjson.JSON;
import com.cictec.middleware.tsinghua.entity.dto.RabbitMqClientDTO;
import com.cictec.middleware.tsinghua.entity.dto.Terminal.PositionMessageDTO;
import com.cictec.middleware.tsinghua.entity.po.AlarmType;
import com.cictec.middleware.tsinghua.model.vo.Alarm;
import com.cictec.middleware.tsinghua.model.vo.AlarmStatus;
import com.cictec.middleware.tsinghua.service.AlarmTypeService;
import com.cictec.middleware.tsinghua.tcp.annotation.HandleMessages;
import com.cictec.middleware.tsinghua.tcp.handler.base.AbstractReceiverHandler;
import com.cictec.middleware.tsinghua.tcp.message.TerminalMessage;
import com.cictec.middleware.tsinghua.tcp.message.request.TerminalLocationRequest;
import com.cictec.middleware.tsinghua.tcp.message.response.PlatformGeneralResponse;
import com.cictec.middleware.tsinghua.utils.BinaryUtils;
import com.cictec.middleware.tsinghua.utils.CamelRabbitMqDslUtils;
import com.cictec.middleware.tsinghua.utils.DateUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.camel.ProducerTemplate;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 位置信息汇报类TerminalLocationHandler类
 * 
 * @file TerminalLocationHandler.java
 * @author mjj
 */
@HandleMessages(0x0200)
@Component
public class TerminalLocationHandler extends AbstractReceiverHandler {

	private static Logger logger = LoggerFactory.getLogger(TerminalLocationHandler.class);
	
	@Autowired
    private ProducerTemplate producerTemplate;
	
	@Autowired
	private AlarmTypeService alarmTypeService;
	
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
		
		TerminalLocationRequest tcr = (TerminalLocationRequest)msg;
//		String id=UUID.randomUUID().toString();
        logger.info("终端 {}  Session {} 位置信息 ",msg.getTerminalId(),msg.getSessionId());
    	
        //消息推送MQ
        locationToMQ(tcr);
        
        tcr.getHeader().setTerminalId(msg.getTerminalId());
        TerminalMessage.Header locationResponseHeader = new TerminalMessage.Header(0x8001);
        locationResponseHeader.setTerminalId(tcr.getHeader().getTerminalId());
        locationResponseHeader.setMessageSequence(tcr.getHeader().getMessageSequence());
        
        PlatformGeneralResponse locationResponse = new PlatformGeneralResponse(locationResponseHeader);
        
        locationResponse.setResponseMsgId(tcr.getHeader().getMessageId());
        locationResponse.setResponseSequence(tcr.getHeader().getMessageSequence());
        
        locationResponse.setResponseResult(PlatformGeneralResponse.RESPONSE_RESULT_SUCCESS);
        sendMessageToTerminal(locationResponse,session);
	}

	private void locationToMQ(TerminalLocationRequest tcr) {
		PositionMessageDTO pmDto = new PositionMessageDTO();
		Alarm alarm = tcr.getWarnMark();
		AlarmStatus alarmStatus = tcr.getStatus();
		pmDto.setAlarmSet(alarmStrArr(alarm));
		pmDto.setAltitude(tcr.getElevation());//海拔高度
		pmDto.setAngle(tcr.getDirection());//角度，方向
		pmDto.setHexDevIdno(tcr.getTerminalId());//设备号
		pmDto.setHexFlowNo(Integer.toHexString(tcr.getHeader().getMessageSequence()));//消息流水号
		pmDto.setHexLocationBuf(tcr.getHexLocationBuf());//定位消息
		pmDto.setHexMsgId(BinaryUtils.addZeroForNum(Integer.toHexString(tcr.getHeader().getMessageId()),4));//消息ID
		pmDto.setLat(tcr.getLat()+"");
		pmDto.setLng(tcr.getLng()+"");
		pmDto.setMile(0);//本次行程里程
		pmDto.setSpeed(tcr.getSpeed());//速度
		
		pmDto.setStatusSet(alarmStatusStrArr(alarmStatus,pmDto.getAlarmSet().length>0));//状态说明
		pmDto.setYyMMddHHmmss(DateUtils.formatDate(tcr.getTime(),"yyMMddHHmmss"));

		logger.info("推送多媒体下载消息到MQ：{}",JSON.toJSONString(pmDto));
        producerTemplate.sendBody(getHttpDownloadDsl(), JSON.toJSONString(pmDto));
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
		
//		if(alarmStatus.getEvidence()==1){alarmStatusList.add("有证据");}else{alarmStatusList.add("无证据");}//24 0：无证据；1：有证据
//		if(alarmStatus.getEvidencePhoteOrVideo()==1){alarmStatusList.add("视频证据");}else{alarmStatusList.add("图片证据");}//25 0：图片证据；1：视频证据
		if(alarmStatus.getShield()==1){alarmStatusList.add("遮挡");}//26 遮挡
		if(alarmStatus.getSpeedUp()==1){alarmStatusList.add("急加速");}//27 急加速
		if(alarmStatus.getSpeedCut()==1){alarmStatusList.add("急减速");}//28 急减速
		if(alarmStatus.getSharpBend()==1){alarmStatusList.add("急转弯");}//29 急转弯
		if(alarmStatus.getDrunkDriving()==1){alarmStatusList.add("酒驾");}//30 酒驾
		alarmStatusArr = alarmStatusList.toArray(new String[alarmStatusList.size()]);  
		return alarmStatusArr;
	}

	private String[] alarmStrArr(Alarm alarm) {
//		String [] alarmArr;
//		List<String> alarmList = new ArrayList<>();
//		AlarmType alarmType = new AlarmType();
//		alarmType.setCode("B003");
//		List<AlarmType> alarmTypes = alarmTypeService.findAllTypes(alarmType);
//		Map<String, String> alarmTypeMap = new HashMap<>();
//		for (int i = 0; i < alarmTypes.size(); i++) {
//			alarmTypeMap.put(alarmTypes.get(i).getValue(), alarmTypes.get(i).getDisplay());
//		}
		
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
	
	

}
