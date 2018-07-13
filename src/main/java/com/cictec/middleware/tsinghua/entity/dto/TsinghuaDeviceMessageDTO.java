package com.cictec.middleware.tsinghua.entity.dto;

import lombok.Data;

/**
 * @author qiandaxian
 */
@Data
public class TsinghuaDeviceMessageDTO {
    private String hexDevIdno;//设备号
    private String hexFlowNo;//消息流水号转16进制字符串
    private String hexMsgId;//消息ID转16进制字符串
}
