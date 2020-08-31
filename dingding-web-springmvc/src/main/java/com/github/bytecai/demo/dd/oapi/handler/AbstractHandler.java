package com.github.bytecai.demo.dd.oapi.handler;

import com.google.gson.Gson;
import my.caijar.dingding.oapi.api.impl.DdOapiMessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
  * @ClassName AbstractHandler
  * @Description TODO
  * @Author  CaiTao
  * @Date   2020/5/7 14:41
  *  @Version 1.0
  */
public abstract class AbstractHandler implements DdOapiMessageHandler {

  protected final Gson gson = new Gson();
  protected Logger logger = LoggerFactory.getLogger(getClass());

}
