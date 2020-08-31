package my.caijar.dingding.oapi.api;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiCallBackDeleteCallBackRequest;
import com.dingtalk.api.request.OapiCallBackGetCallBackRequest;
import com.dingtalk.api.request.OapiCallBackUpdateCallBackRequest;
import com.dingtalk.api.response.*;
import com.taobao.api.ApiException;

import java.util.Arrays;
import java.util.List;

/**
 * @title ct-dingfly-parent
 * @Description 部门管理接口
 * @Author CT
 * @Date 2020/5/13 15:53
 * @Version 1.0
 */
public interface CBManagerHelper {

	/***
	 * 注册回调接口时，钉钉服务器会向URL发起【测试回调URL】事件，来验证填写url的合法性，
	 *  url服务器需要在接收到回调之后返回字符串“success”的加密json数据，才能完成注册。
	 * @param url
	 * 		  接收事件回调的url，必须是公网可以访问的url地址
	 * @param callBackTag
	 * 		  需要监听的事件类型
	 *  token
	 * 		  加解密需要用到的token，ISV(服务提供商)推荐使用注册套件时填写的token，普通企业可以随机填写
	 *  aesKey
	 * 		  数据加密密钥。用于回调数据的加密，长度固定为43个字符，从a-z, A-Z, 0-9共62个字符中选取,您可以随机生成，
	 * 		  ISV(服务提供商)推荐使用注册套件时填写的EncodingAESKey
	 */
	Boolean registerCallBack(List<String> callBackTag) throws ApiException;

	/***
	 * 查询事件回调接口
	 */
	OapiCallBackGetCallBackResponse getCallback() throws ApiException;

	/***
	 * 更新事件回调接口
	 */
	Boolean updateCallback(List<String> callBackTag) throws ApiException;

	/***
	 * 删除事件回调接口
	 */
	Boolean deleteCallback() throws ApiException;

}
