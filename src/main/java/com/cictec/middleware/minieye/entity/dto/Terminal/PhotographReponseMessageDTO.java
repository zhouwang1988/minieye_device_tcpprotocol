package com.cictec.middleware.minieye.entity.dto.Terminal;

import com.cictec.middleware.minieye.entity.dto.MinieyeDeviceMessageDTO;

import lombok.Data;

/**
 * @author qiandaxian
 * 摄像头抓拍结果上报
 */
@Data
public class PhotographReponseMessageDTO extends MinieyeDeviceMessageDTO {
    private Integer mediaCount;
    private String[] mediaIdArr;
    private String replyHexFlowNo;
    private Integer resultCode;
}
