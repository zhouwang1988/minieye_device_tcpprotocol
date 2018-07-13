package com.cictec.middleware.tsinghua.entity.dto;

import com.cictec.middleware.tsinghua.entity.dto.Terminal.PositionMessageDTO;

import lombok.Data;

/**
 * @author maojiajing
 * 多媒体证据对象
 */
@Data
public class DataUploadDTO extends TsinghuaDeviceMessageDTO {
    private String[] alarmSet;//报警说明
    private Integer altitude;//海拔高度
    private Integer angle;//角度
    
    private String channelId;//证据上传通道
    private String hexLocationBuf;
    private String hexMediaId;//多媒体ID
    private Integer itemEncoding;//事件项编码
    private String mediaEncoding;//多媒体格式
    private String mediaType;//多媒体类型
    private String mediaUrl;//证据HTTP地址
    
    private String lat;//纬度
    private String lng;//经度
    private Integer mile;//本次行程里程
    private Integer speed;//速度
    private String[] statusSet;//状态
    private String yyMMddHHmmss;//时间
}
