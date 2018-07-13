package com.cictec.middleware.tsinghua.entity.dto;

import lombok.Data;

@Data
public class ConnectDTO extends TsinghuaDeviceMessageDTO {
	private String authenticationCode;//鉴权码
}
