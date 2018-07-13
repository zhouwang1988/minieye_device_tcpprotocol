package com.cictec.middleware.tsinghua.tcp.message.response;

import java.util.List;

import com.cictec.middleware.tsinghua.model.vo.TerminalParams;
import com.cictec.middleware.tsinghua.tcp.message.TerminalMessage;

public class PlatformSetTerminalParamsResponse extends TerminalMessage {
	
	private List<TerminalParams> paramsList;
	
	public PlatformSetTerminalParamsResponse(Header header) {
        super(header);
    }

	public List<TerminalParams> getParamsList() {
		return paramsList;
	}

	public void setParamsList(List<TerminalParams> paramsList) {
		this.paramsList = paramsList;
	}
	
}
