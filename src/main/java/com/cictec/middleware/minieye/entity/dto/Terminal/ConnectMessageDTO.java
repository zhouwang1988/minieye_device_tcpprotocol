package com.cictec.middleware.minieye.entity.dto.Terminal;

import com.cictec.middleware.minieye.entity.dto.MinieyeDeviceMessageDTO;

import lombok.Data;

/**
 * @author qiandaxian
 * 鉴权信息传输对象
 */
@Data
public class ConnectMessageDTO extends MinieyeDeviceMessageDTO {
    private String authenticationCode;
}
