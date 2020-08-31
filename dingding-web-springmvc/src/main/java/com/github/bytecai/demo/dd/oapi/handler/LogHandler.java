package com.github.bytecai.demo.dd.oapi.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import my.caijar.dingding.oapi.api.DdOapiService;
import my.caijar.dingding.oapi.bean.message.DdOapiMessage;
import my.caijar.dingding.oapi.bean.message.DdOapiOutMessage;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @title ct-dingfly-parent
 * @Description CT的fly系列项目
 * @Author CT
 * @Date 2020/5/7 14:32
 * @Version 1.0
 */

@Component
public class LogHandler extends AbstractHandler {

	private static final ObjectMapper JSON = new ObjectMapper();

	static {
		JSON.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		JSON.configure(SerializationFeature.INDENT_OUTPUT, Boolean.TRUE);
	}

	@Override
	public DdOapiOutMessage handle(DdOapiMessage wxMessage,
								   Map<String, Object> context, DdOapiService wxMpService) {
		try {
			this.logger.info("\n接收到请求消息，内容：{}", JSON.writeValueAsString(wxMessage));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return null;
	}
}
