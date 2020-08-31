package my.caijar.dingding.oapi.api.impl;

import my.caijar.dingding.common.error.DdErrorException;
import my.caijar.dingding.oapi.api.DdOapiService;
import my.caijar.dingding.oapi.bean.message.DdOapiMessage;
import my.caijar.dingding.oapi.bean.message.DdOapiOutMessage;

import java.util.Map;

/**
 * @title ct-dingfly-parent
 * @Description
 * @Author 12870
 * @Date 2020/4/28 0:02
 * @Version 1.0
 */
public interface DdOapiMessageHandler {
    DdOapiOutMessage handle(DdOapiMessage ddOapiMessage, Map<String, Object> map, DdOapiService ddOapiService) throws DdErrorException;
}
