package my.caijar.dingding.oapi.bean.message;

import com.alibaba.fastjson.JSONObject;
import com.dingtalk.oapi.lib.aes.DingTalkEncryptException;
import com.dingtalk.oapi.lib.aes.DingTalkEncryptor;
import com.dingtalk.oapi.lib.aes.Utils;
import lombok.Getter;
import lombok.Setter;
import my.caijar.dingding.common.DdType;
import my.caijar.dingding.common.api.DdConsts;
import my.caijar.dingding.common.error.DdError;
import my.caijar.dingding.common.error.DdErrorException;
import my.caijar.dingding.oapi.builder.out.TextBuilder;
import my.caijar.dingding.oapi.constant.DdOapiEventConstants;
import my.caijar.dingding.oapi.enums.DdOapiApiUrl;

import java.util.Map;

/**
 * @title ct-dingfly-parent
 * @Description 构造事件返回
 * @Author 12870
 * @Date 2020/4/28 0:53
 * @Version 1.0
 */
@Getter
@Setter
public abstract class DdOapiOutMessage {

	protected DingTalkEncryptor dingTalkEncryptor;

	public static TextBuilder TEXT() {
		return new TextBuilder();
	}

	public Map<String, String> toEncrypted() throws DdErrorException {
		try {
			return dingTalkEncryptor.getEncryptedMap(DdOapiEventConstants.CALLBACK_RESPONSE_SUCCESS, System.currentTimeMillis(), Utils.getRandomStr(8));
		} catch (DingTalkEncryptException e) {
			throw new DdErrorException(new DdError().fromSource(DdType.DWP));
		}
	}

}
