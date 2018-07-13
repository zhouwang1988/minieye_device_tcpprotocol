package com.cictec.middleware.tsinghua.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.imageio.stream.FileImageInputStream;

import com.cictec.middleware.tsinghua.model.vo.MediaPacket;
import com.cictec.middleware.tsinghua.tcp.message.TerminalMessage;

/**
 * 分包操作工具类
 * 实现分包数据整合等功能
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class SubcontractUtil {

	public Map<String, Map<Integer, MediaPacket>> mediaPackets = new ConcurrentHashMap<>();
	
	/**
     * 判断是否分包
     * @param msg 待判断的包
     * @return 如果是分包，则返回true，否则返回false
     */
    public static boolean isSubcontract(TerminalMessage msg) {
    	if(msg.getHeader().isSubcontract()){
    		return true;
    	}else{
    		return false;
    	}
    }
    
    /**
     * 判断是否是分包的第一个包
     * @param msg 待判断的包
     * @return 如果是第一个包，则返回true，否则返回false
     */
    public static boolean isFristPackage(TerminalMessage msg) {
    	if (msg.getHeader().getPackageNum() == 1) {
    		return true;
    	}else{
    		return false;
    	}
    }
    
    /**
     * 获取当前包序号
     * @param msg 待获取序号的包
     * @return 包序号
     */
    public static int getCurrentPackageNum(TerminalMessage msg) {
    	return msg.getHeader().getPackageNum();
    }
    
    /**
     * 获取包的总数
     * @param msg 待分析的包
     * @return 获取包的总数
     */
    public static int getPackageCounts(TerminalMessage msg) {
    	return msg.getHeader().getPackageCounts();
    }
    

	// 使用两个 for 语句
	// java 合并两个byte数组
	public static byte[] byteMerger(byte[] bt1, byte[] bt2) {
		byte[] bt3 = new byte[bt1.length + bt2.length];
		int i = 0;
		for (byte bt : bt1) {
			bt3[i] = bt;
			i++;
		}

		for (byte bt : bt2) {
			bt3[i] = bt;
			i++;
		}
		return bt3;
	}

	// 图片到byte数组
	public static byte[] image2byte(String path) {
		byte[] data = null;
		FileImageInputStream input = null;
		try {
			input = new FileImageInputStream(new File(path));
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			int numBytesRead = 0;
			while ((numBytesRead = input.read(buf)) != -1) {
				output.write(buf, 0, numBytesRead);
			}
			data = output.toByteArray();
			output.close();
			input.close();
		} catch (FileNotFoundException ex1) {
			ex1.printStackTrace();
		} catch (IOException ex1) {
			ex1.printStackTrace();
		}
		return data;
	}
	
}
