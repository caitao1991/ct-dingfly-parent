package com.github.bytecai.demo.dd.oapi.builder;

import com.github.bytecai.demo.dd.oapi.service.DdingService;
import my.caijar.dingding.oapi.bean.message.DdOapiMessage;
import my.caijar.dingding.oapi.bean.message.DdOapiOutMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
  * @ClassName AbstractBuilder
  * @Description
  * @Author  CaiTao
  * @Date   2020/5/12 9:57
  *  @Version 1.0
  */
public abstract class AbstractBuilder {
  protected final Logger logger = LoggerFactory.getLogger(getClass());

  public abstract DdOapiOutMessage build(String content,
										 DdOapiMessage ddMessage, DdingService service);
}
