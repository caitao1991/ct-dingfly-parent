package com.github.bytecai.demo.dd.oapi.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dingtalk.oapi.lib.aes.DingTalkEncryptException;
import com.dingtalk.oapi.lib.aes.DingTalkEncryptor;
import com.github.bytecai.demo.dd.oapi.service.DdingService;
import my.caijar.dingding.common.DdType;
import my.caijar.dingding.common.api.DdConsts;
import my.caijar.dingding.common.error.DdError;
import my.caijar.dingding.common.error.DdErrorException;
import my.caijar.dingding.common.utils.RandomUtils;
import my.caijar.dingding.oapi.bean.message.DdOapiMessage;
import my.caijar.dingding.oapi.bean.message.DdOapiOutMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
  * @ClassName UserCallbackController
  * @Description 处理钉钉回调事件。
  * @Author  CaiTao
  * @Date   2019/6/27 9:17
  *  @Version 1.0
  */
@RestController
@RequestMapping("/ctfly/portal")
public class DdOapiPortalController {


    private final Logger logger = LoggerFactory.getLogger(getClass());

	private DdingService ddService;

	@Autowired
	public DdOapiPortalController(DdingService ddService) {
		this.ddService = ddService;
	}

    @RequestMapping(value = "/callback", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> callback(@RequestParam(value = "signature", required = false) String signature,
                                        @RequestParam(value = "timestamp", required = false) String timestamp,
                                        @RequestParam(value = "nonce", required = false) String nonce,
                                        @RequestBody(required = false) JSONObject json) {
        String params = " signature:"+signature + " timestamp:"+timestamp +" nonce:"+nonce+" json:"+json;
		logger.info(params + "----------------------------" + json);
        try {
			//从post请求的body中获取回调信息的加密数据进行解密处理
			JSONObject obj = ddService.checkSignature(json, timestamp, nonce, signature);
			Map out = null;
			if (obj == null) {
				throw new IllegalArgumentException("非法请求，可能属于伪造的请求！");
			}
            /**
             * 根据回调数据类型做不同的业务处理
             */

			DdOapiMessage ddOapiMessage = new DdOapiMessage.DdOapiMessageBuilder().backObject(obj)
																			      .msgType(DdConsts.DdMsgType.EVENT)
																			      .eventType(obj.getString("EventType"))
																			      .build();
			DdOapiOutMessage outMessage = this.ddService.route(ddOapiMessage);
			if (outMessage == null) {
				return null;
			}

			out = outMessage.toEncrypted();
			this.logger.debug("\n组装回复信息：{}", out);
            return out;
        } catch (Exception e) {
            //失败的情况，应用的开发者应该通过告警感知，并干预修复
			logger.error("process callback failed！"+params,e);
			return null;
        }
    }

}
