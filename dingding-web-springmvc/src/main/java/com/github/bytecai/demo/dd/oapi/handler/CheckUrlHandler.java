package com.github.bytecai.demo.dd.oapi.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.bytecai.demo.dd.oapi.builder.TextBuilder;
import com.github.bytecai.demo.dd.oapi.service.DdingService;
import my.caijar.dingding.oapi.api.DdOapiService;
import my.caijar.dingding.oapi.bean.message.DdOapiMessage;
import my.caijar.dingding.oapi.bean.message.DdOapiOutMessage;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @title ct-dingfly-parent
 * @Description 考勤事件回调消息handler。
 * @Author CT
 * @Date 2020/5/7 14:32
 * @Version 1.0
 */

@Component
public class CheckUrlHandler extends AbstractHandler {

	private static final ObjectMapper JSON = new ObjectMapper();

	static {
		JSON.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		JSON.configure(SerializationFeature.INDENT_OUTPUT, Boolean.TRUE);
	}

	@Override
	public DdOapiOutMessage handle(DdOapiMessage ddOapiMessage,
								   Map<String, Object> context, DdOapiService ddOapiService) {

		DdingService ddService = (DdingService) ddOapiService;
		String content = "验证回调接口是否可用！";
		try {
			this.logger.info("\n验证回调接口是否可用：{}", JSON.writeValueAsString(ddOapiMessage));
			return new TextBuilder().build(content, ddOapiMessage, ddService);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
}
