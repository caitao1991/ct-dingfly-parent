package com.github.bytecai.demo.dd.oapi.builder;

import com.github.bytecai.demo.dd.oapi.service.DdingService;
import my.caijar.dingding.common.error.DdErrorException;
import my.caijar.dingding.oapi.bean.message.DdOapiMessage;
import my.caijar.dingding.oapi.bean.message.DdOapiOutMessage;

/**
 * @author Tao Cai
 */
public class TextBuilder extends AbstractBuilder {

  @Override
  public DdOapiOutMessage build(String content, DdOapiMessage ddOapiMessage,
								DdingService service) {
	  try {
		  return  DdOapiOutMessage.TEXT().dingTalkEncryptor(service.getDingTalkEncryptor()).content("返回的content")
			  .build();
	  } catch (DdErrorException e) {
		  e.printStackTrace();
	  }
	  return null;
  }

}
