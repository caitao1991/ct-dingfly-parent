package my.caijar.dingding.oapi.api.impl;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiCallBackDeleteCallBackRequest;
import com.dingtalk.api.request.OapiCallBackGetCallBackRequest;
import com.dingtalk.api.request.OapiCallBackRegisterCallBackRequest;
import com.dingtalk.api.request.OapiCallBackUpdateCallBackRequest;
import com.dingtalk.api.response.OapiCallBackDeleteCallBackResponse;
import com.dingtalk.api.response.OapiCallBackGetCallBackResponse;
import com.dingtalk.api.response.OapiCallBackRegisterCallBackResponse;
import com.dingtalk.api.response.OapiCallBackUpdateCallBackResponse;
import com.taobao.api.ApiException;
import my.caijar.dingding.common.error.DdError;
import my.caijar.dingding.oapi.api.CBManagerHelper;
import my.caijar.dingding.oapi.api.DdOapiService;
import my.caijar.dingding.oapi.enums.DdOapiApiUrl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @title ct-dingfly-parent
 * @Description CT的fly系列项目
 * @Author CT
 * @Date 2020/6/4 18:54
 * @Version 1.0
 */
public class CBManagerHelperImpl implements CBManagerHelper {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private final DdOapiService ddOapiService;

	public CBManagerHelperImpl(DdOapiService ddOapiService) {
		this.ddOapiService = ddOapiService;
	}

	public Boolean registerCallBack(List<String> callBackTag) throws ApiException {
		String accessToken = ddOapiService.getAccessToken();
//		OapiCallBackGetCallBackResponse callback = this.getCallback();
//		if(callback != null && ddOapiService.getDdOapiConfigStorage().getUrl().equals(callback.getUrl())) {
//			return true;
//		}
		/**
		 * 先删除企业已有的回调
		 */
		this.deleteCallback();

		/**
		 * 重新为企业注册回调
		 */
		DefaultDingTalkClient client = new DefaultDingTalkClient(DdOapiApiUrl.CbManagement.CB_REGISTER_URL.getUrl(ddOapiService.getDdOapiConfigStorage()));
		OapiCallBackRegisterCallBackRequest registerRequest = new OapiCallBackRegisterCallBackRequest();
		registerRequest.setUrl(this.ddOapiService.getDdOapiConfigStorage().getUrl());
		registerRequest.setAesKey(this.ddOapiService.getDdOapiConfigStorage().getAesKey());
		registerRequest.setToken(this.ddOapiService.getDdOapiConfigStorage().getToken());

		//需要注册的回调事件
		registerRequest.setCallBackTag(callBackTag);
		OapiCallBackRegisterCallBackResponse registerResponse = client.execute(registerRequest, accessToken);
		if (registerResponse.isSuccess()) {
			System.out.println("回调注册成功了！！！");
			return true;
		}
		return false;
	}

	public OapiCallBackGetCallBackResponse getCallback() throws ApiException {
		String accessToken = this.ddOapiService.getAccessToken();
		DingTalkClient client = new DefaultDingTalkClient(DdOapiApiUrl.CbManagement.CB_GET_URL.getUrl(ddOapiService.getDdOapiConfigStorage()));
		OapiCallBackGetCallBackRequest request = new OapiCallBackGetCallBackRequest();
		request.setHttpMethod("GET");
		OapiCallBackGetCallBackResponse response = null;
		response = client.execute(request,accessToken);
		if(response.getErrcode().longValue() != 0L) {
			logger.warn(new DdError.DdErrorBuilder().errorMsg(response.getErrmsg()).errorCode(response.getErrcode()).toString());
			return null;
		}
		return response;
	}

	public Boolean updateCallback(List<String> callBackTag) throws ApiException {
		String accessToken = this.ddOapiService.getAccessToken();
		DingTalkClient client = new DefaultDingTalkClient(DdOapiApiUrl.CbManagement.CB_UPDATE_URL.getUrl(ddOapiService.getDdOapiConfigStorage()));
		OapiCallBackUpdateCallBackRequest request = new OapiCallBackUpdateCallBackRequest();
		request.setUrl(ddOapiService.getDdOapiConfigStorage().getUrl());
		request.setAesKey(ddOapiService.getDdOapiConfigStorage().getAesKey());
		request.setToken(ddOapiService.getDdOapiConfigStorage().getToken());
		request.setCallBackTag(callBackTag);
		OapiCallBackUpdateCallBackResponse response = null;

		try {
			response = client.execute(request,accessToken);
			if(response.getErrcode().longValue() == 0L) {
				return true;
			}
		} catch (ApiException e) {
			logger.error(new DdError(Long.valueOf(e.getErrCode()),e.getErrMsg()).toString());
			e.printStackTrace();
		}
		return false;
	}

	/***
	 * delete callback interface
	 * @return
	 */
	public Boolean deleteCallback() throws ApiException {
		String accessToken = ddOapiService.getAccessToken();
		DingTalkClient client = new DefaultDingTalkClient(DdOapiApiUrl.CbManagement.CB_DELETE_URL.getUrl(ddOapiService.getDdOapiConfigStorage()));
		OapiCallBackDeleteCallBackRequest request = new OapiCallBackDeleteCallBackRequest();
		request.setHttpMethod("GET");
		OapiCallBackDeleteCallBackResponse execute = null;
		try {
			execute = client.execute(request, accessToken);
			if(execute.getErrcode().longValue() == 0L) {
				return true;
			}
		} catch (ApiException e) {
			logger.error(new DdError(Long.valueOf(e.getErrCode()),e.getErrMsg()).toString());
			e.printStackTrace();
		}
		return false;
	}
}
