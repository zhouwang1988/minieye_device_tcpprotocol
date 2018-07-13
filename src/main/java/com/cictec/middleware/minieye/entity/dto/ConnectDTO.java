package com.cictec.middleware.minieye.entity.dto;

import lombok.Data;

@Data
public class ConnectDTO extends MinieyeDeviceMessageDTO {
	private String authenticationCode;//鉴权码
}
