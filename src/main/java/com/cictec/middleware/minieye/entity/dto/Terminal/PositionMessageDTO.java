package com.cictec.middleware.minieye.entity.dto.Terminal;

import com.cictec.middleware.minieye.entity.dto.MinieyeDeviceMessageDTO;

import lombok.Data;

/**
 * @author qiandaxian
 * 位置信息传输对象
 */
@Data
public class PositionMessageDTO extends MinieyeDeviceMessageDTO {
    private String[] alarmSet;
    private Integer altitude;
    private Integer angle;
    private String hexLocationBuf;
    private String lat;
    private String lng;
    private Integer mile;
    private Integer speed;
    private String[] statusSet;
    private String yyMMddHHmmss;
}
