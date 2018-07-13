package com.cictec.middleware.tsinghua.tcp.code;


import java.io.File;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import com.cictec.middleware.tsinghua.config.Constants;
import com.cictec.middleware.tsinghua.tcp.message.TerminalMessage;
import com.cictec.middleware.tsinghua.utils.BinaryUtils;
import com.cictec.middleware.tsinghua.utils.FileUtils;


/**
 * @author daxian
 * 20171025重构此方法，并实现了粘包，断包的处理。
 */
@Component("terminalProtocolDecoder")
public class TerminalProtocolDecoder extends CumulativeProtocolDecoder {

	private static Logger logger = LoggerFactory.getLogger(TerminalProtocolDecoder.class);

    private static final int VALIDITY_SUCCESS=0;
    private static final int VALIDITY_WITHOUT_START_FLAG=1;
    private static final int VALIDITY_WITHOUT_END_FLAG=2;
    private static final int VALIDITY_SHORT_LENGTH=3;
    private static final int VALIDITY_OVER_LENGTH=4;
    private static final int VALIDITY_CONTINUOUS_FLAG=5;



    @Autowired
    private CodecFounder codecFounder;

    /**
     * 方法返回值为false时，CumulativeProtocolDecoder会将iobuffer待读取的数据，合并到新接收到数据中，再次调用doDecode方法。
     * 方法返回值为true时，如果IoBuffer有数据时，CumulativeProtocolDecoder会继续调用doDecode进行解码。如果IoBuffer为空，则不会调用doDecode
     * @param ioSession
     * @param ioBuffer
     * @param protocolDecoderOutput
     * @return
     * @throws Exception
     */
    @Override
    protected boolean doDecode(IoSession ioSession, IoBuffer ioBuffer, ProtocolDecoderOutput protocolDecoderOutput) throws Exception {

        return readIoBufferMessage(ioBuffer,protocolDecoderOutput,ioSession);

    }


    /**
     * 读取IoBuffer中的消息，有需要合并的消息，返回false，待新消息进入后处理
     * 当需要递归读取消息时，返回true。
     * @param ioBuffer
     * @return
     */
    private boolean readIoBufferMessage(IoBuffer ioBuffer,ProtocolDecoderOutput out,IoSession ioSession) {

        boolean result = false;

        List<ByteBuffer> messageList = getMessageByFlag(ioBuffer);

        if (messageList != null && messageList.size() != 0) {
            //解析消息
            messageList.stream().forEach(in->decodeMessage(in,out,ioSession));
        }

        return result;
    }


    /**
     * 解析消息
     * @param in
     */
    private void decodeMessage(ByteBuffer in,ProtocolDecoderOutput out,IoSession ioSession){

        MessageDTO messageDTO = null;

        try {

            messageDTO = getMessageDTO(in);

            if(this.validityMessage(messageDTO)){

                MessageDecoder md = this.codecFounder.getDecoder(messageDTO.getHeader().getMessageId());

                if (md != null) {
                    TerminalMessage tm = null;

                    tm = md.decode(messageDTO.getHeader(), messageDTO.getBody(), ioSession);
                    tm.setSessionId(ioSession.getId());
                    out.write(tm);

                } else {

                    logger.error("找不到消息 {} 对应的解码器 ", Integer.toHexString(messageDTO.getHeader().getMessageId()));
                }
            }
        } catch (CanntFoundDecoder canntFoundDecoder) {
            canntFoundDecoder.printStackTrace();
        }catch (Exception e){
            logger.error("解码出现异常：{}，请检查消息体：｛｝",e.getMessage(),BinaryUtils.byte2HexStr(messageDTO.getBody(),messageDTO.getBody().limit()));
        }
    }



    /*消息ID		消息体属性		终端手机号		消息流水号
	  2B		2B		          6B			2B		*/
    protected TerminalMessage.Header decodeHeader(ByteBuffer in) throws EscapeException, IllegalMessageException {

        TerminalMessage.Header header = new TerminalMessage.Header(0);

        int  length=12;
        ByteBuffer ins = msgEscape(in, 12);
        //消息ID
        header.setMessageId(BinaryUtils.wordToInt(ins));
        
//        解析消息体属性
        Map<String,Integer> body=decodeMessageBodyattr(ins);
//      消息是否分包
        boolean subcontract =(int)body.get("SUBCONTRACT")==1?true:false;
        header.setSubcontract(subcontract);
        //消息体长度
        header.setBodyLength((int)body.get("BODYLENGTH"));
        
        
        header.setTerminalId(BinaryUtils.byte2HexStr(ins,6,false));
        header.setMessageSequence(BinaryUtils.wordToInt(ins));
        //解析消息头,判断是否分包，如果分包处理消息包总数和消息包序号
        ByteBuffer msgHeadSubBuffer =null;
        if(header.isSubcontract()){
        	msgHeadSubBuffer = msgEscape(in, 4);
        	header.setPackageCounts(BinaryUtils.wordToInt(msgHeadSubBuffer));
        	header.setPackageNum(BinaryUtils.wordToInt(msgHeadSubBuffer));
        	length+=4;
        }

        header.setLength(length);
        return header;


    }
    
//    解析消息体属性
    public Map<String, Integer> decodeMessageBodyattr(ByteBuffer in) {
    	int[] messages=BinaryUtils.intToIntArray(BinaryUtils.wordToInt(in));
    	int subcontract=messages[2];
    	int bodyLength=BinaryUtils.getBodyLength(messages);//正序从消息属性的6位开始取值
    	Map<String, Integer> map = new HashMap<String, Integer>();
    	map.put("SUBCONTRACT", subcontract);
    	map.put("BODYLENGTH", bodyLength);
    	return map;
	}
    


    /**
     * 编码转义
     * @param in
     * @param length
     * @return
     */
    private ByteBuffer msgEscape(IoBuffer in,int length){
        byte[] bytes = new byte[length];
        for (int i = 0; i <length ; i++) {
            byte msg = in.get();
            if(msg==0x7d){
                byte next = in.get();
                if(next==0x02){
                    bytes[i]=0x7E;
                }else if(next==0x01){
                    bytes[i]=0x7D;
                }
            }else{
                bytes[i]=msg;
            }
        }
        ByteBuffer bytebuffer = ByteBuffer.wrap(bytes);
        return  bytebuffer;
    }


    /**
     * 消息校验
     * @return
     */
    private boolean validityMessage(MessageDTO messageDTO){
        return messageDTO!=null&&validityCheckCode(messageDTO);
    }




    /**
     * 检验码检查
     * @return
     */
    private boolean validityCheckCode(MessageDTO messageDTO){

        boolean result = true;

        Integer xorResult=0;

        for (int i = 0; i < messageDTO.getHead().limit(); i++) {
            xorResult = xorResult ^ messageDTO.getHead().get(i);
        }
        for (int i = 0; i < messageDTO.getBody().limit(); i++) {
            xorResult = xorResult ^ messageDTO.getBody().get(i);
        }

        if (messageDTO.getCheckCode() != xorResult) {
            result = false;
            logger.error("校验码校验失败,消息校验码:{},实际校验码:{}", messageDTO.getCheckCode(), xorResult);
        }

        return result;


    }



    /**
     * 数据校验
     * @return
     */
    private boolean checkValidity(ByteBuffer head,ByteBuffer body,byte checkDigit,byte endflag){

        boolean isSuccess = false;

        int xorResult=0;

        //检查结束标识
        if(endflag == Constants.MESSAGE_END_FLAG) {

            for (int i = 0; i < head.limit(); i++) {

                xorResult = xorResult ^ head.get(i);

            }
            for (int i = 0; i < body.limit(); i++) {

                xorResult = xorResult ^ body.get(i);

            }


            if (checkDigit == xorResult) {
                return true;
            } else {
                logger.error("校验码校验失败,消息校验码:{},实际校验码:{}", checkDigit, xorResult);
            }

        }
        return isSuccess;
    }








    /**
     * 1B          14B   长度消息头中      1B        1B
     * 开始标识位    消息头    消息体    校验码    结束标识位
     * @param in
     * @return
     */
    private MessageDTO getMessageDTO(ByteBuffer in)  {

        MessageDTO messageDTO = null;
        StringBuffer sb = new StringBuffer();
        try {

        	byte[] bytes = new byte[in.limit() - in.position()];
            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = in.get();
                sb.append(BinaryUtils.byte2HexStr(bytes[i])).append(' ');
            }
            ByteBuffer bytebuffer = ByteBuffer.wrap(bytes);
            //开始标识位
            bytebuffer.get();

            ByteBuffer bytebuffer1 = ByteBuffer.wrap(bytes);
            bytebuffer1.get();
            //解析消息头
            TerminalMessage.Header header = decodeHeader(bytebuffer);
           
            ByteBuffer msgHeadBuffer = msgEscape(bytebuffer1, header.getLength());
            
            //解析消息体
            ByteBuffer msgBodyBuffer = msgEscape(bytebuffer, header.getBodyLength());

            //校验码
            ByteBuffer checkDigitBuffer = msgEscape(bytebuffer, 1);
            //校验码
            byte checkCode = checkDigitBuffer.get(checkDigitBuffer.limit() - 1);

            //结束标识
            bytebuffer.get();

            messageDTO = new MessageDTO(msgHeadBuffer,msgBodyBuffer, checkCode, header);
            
            logger.info("收到消息 session 序号{} 消息ID {} ： {} ",header.getMessageSequence(), Integer.toHexString(header.getMessageId()) ,sb.toString());
        }catch (Exception e){
            in.position(0);
            logger.error("数据有误,消息：{}",BinaryUtils.byte2HexStr(in,in.limit()));
        }
        return messageDTO;
    }






    private ByteBuffer msgEscape(ByteBuffer in,int length){

        byte[] bytes = new byte[length];

        for (int i = 0; i <length ; i++) {
            byte msg = in.get();
            if(msg==0x7d){
                byte next = in.get();
                if(next==0x02){
                    bytes[i]=0x7E;
                }else if(next==0x01){
                    bytes[i]=0x7D;
                }
            }else{
                bytes[i]=msg;
            }

        }

        ByteBuffer bytebuffer = ByteBuffer.wrap(bytes);


        return  bytebuffer;

    }


    /**
     * 按照标志位截取消息
     * @param ioBuffer
     * @return
     */
    private List<ByteBuffer> getMessageByFlag(IoBuffer ioBuffer){

        //创建一个byte数组存放找到的消息
        List<ByteBuffer> messageList=new ArrayList();

        while (ioBuffer.hasRemaining()){

            Integer startPosition = ioBuffer.position();

            ByteBuffer byteBuffer = getOneMessage(ioBuffer,startPosition);




            if (byteBuffer!=null){
                logger.debug("MESSAGE:{}",BinaryUtils.byte2HexStr(byteBuffer,byteBuffer.limit()));
                byteBuffer.position(0);
                messageList.add(byteBuffer);
            }
            else {

                //如果遍历后，下标和之前相等，结束循环
                if (startPosition == ioBuffer.position()) {
                    break;
                }

            }

        }



        return messageList;
    }


    private ByteBuffer getOneMessage(IoBuffer ioBuffer,Integer startPosition){

        ByteBuffer byteBuffer = null;

        Integer messageStartPosition = -1;

        Integer messageEndPosition = -1;

        //寻找消息开始标志位
        while (ioBuffer.hasRemaining()){
            //如果找到标识位，进行读取
            if (Constants.MESSAGE_BEGIN_FLAG==ioBuffer.get()){
                messageStartPosition = ioBuffer.position()-1;
                break;
            }
        }

        //寻找消息结束标志位
        while (ioBuffer.hasRemaining()){
            if(Constants.MESSAGE_END_FLAG==ioBuffer.get()){
                messageEndPosition = ioBuffer.position()-1;
                break;
            }
        }

        if (validitySuccess(messageStartPosition,messageEndPosition)){
            //组装消息
            byte[] bytes = new byte[messageEndPosition-messageStartPosition+1];
            for (int i=0;i<=messageEndPosition-messageStartPosition;i++){
                bytes[i] = ioBuffer.get(messageStartPosition+i);
            }
            byteBuffer = ByteBuffer.wrap(bytes);
        }else {
            rollBackIoBufferForStartPositionAndEndPosition(startPosition,messageStartPosition,messageEndPosition,ioBuffer);
        }

        return byteBuffer;

    }

    /**
     * 通过校验码，移动IoBuffer位置
     */
    private void rollBackIoBufferForStartPositionAndEndPosition(Integer startPosition,Integer messageStartPosition,Integer messageEndPosition,IoBuffer ioBuffer){

        Integer validityCode = validityMessageWithStartPositionAndEndPosition(messageStartPosition,messageEndPosition);

        switch (validityCode){
            //未发现开始标识时，下标不回退
            case VALIDITY_WITHOUT_START_FLAG:
                logger.debug("未发现消息开始标识，舍弃消息从{}位到{}位",startPosition,ioBuffer.position());
                break;
            //未发现结束标识时，下班回退到开始位置
            case VALIDITY_WITHOUT_END_FLAG:
                ioBuffer.position(startPosition);
                logger.debug("未发现消息结束标识，下标回滚到开始标志位置{}",startPosition);
                break;
            //数据长度不足，消息回滚到结束标识
            //如果7E 7E则下标回退到后一个7E
            //用于处理后一个7E是下一个消息的消息头的情况
            case VALIDITY_SHORT_LENGTH:
                ioBuffer.position(messageEndPosition);
                logger.debug("消息长度不足，下标回滚消息结束标识位置{}",messageEndPosition);
                break;
            case VALIDITY_OVER_LENGTH:
                logger.debug("消息长度过长，舍弃消息从{}位到{}位",startPosition,messageEndPosition);
                break;
            case VALIDITY_CONTINUOUS_FLAG:
                ioBuffer.position(messageEndPosition);
                logger.debug("连续的标识位，舍弃前一个标识位！");

            default:
                    break;
        }
    }

    /**
     * 通过消息的开始结束下标，判断校验是否通过
     * @param messageStartPosition
     * @param messageEndPosition
     * @return
     */
    private boolean validitySuccess(Integer messageStartPosition,Integer messageEndPosition){
        Integer validityCode = validityMessageWithStartPositionAndEndPosition(messageStartPosition,messageEndPosition);
        return validityCode==TerminalProtocolDecoder.VALIDITY_SUCCESS?true:false;
    }


    /**
     *通过消息的开始结束下标，判断消息的合法性
     * @param messageStartPosition
     * @param messageEndPosition
     * @return
     */
    private Integer validityMessageWithStartPositionAndEndPosition(Integer messageStartPosition,Integer messageEndPosition){

        Integer resultCode=TerminalProtocolDecoder.VALIDITY_SUCCESS;

        if(messageStartPosition==-1){
            resultCode = TerminalProtocolDecoder.VALIDITY_WITHOUT_START_FLAG;
        }
        else if (messageEndPosition==-1){
            resultCode = TerminalProtocolDecoder.VALIDITY_WITHOUT_END_FLAG;
        }
        else if(messageEndPosition-messageStartPosition==1){
            resultCode = TerminalProtocolDecoder.VALIDITY_CONTINUOUS_FLAG;
        }
        else if(messageEndPosition-messageStartPosition<Constants.MESSAGE_MIN_LENGTH){
            resultCode = TerminalProtocolDecoder.VALIDITY_SHORT_LENGTH;
        }
        else if(messageEndPosition-messageStartPosition>Constants.MESSAGE_MAX_LENGTH){
            resultCode = TerminalProtocolDecoder.VALIDITY_OVER_LENGTH;
        }

        return resultCode;
    }
}
