package my.caijar.dingding.oapi.bean.message;

import com.alibaba.fastjson.JSONObject;
import com.dingtalk.oapi.lib.aes.DingTalkEncryptException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import my.caijar.dingding.common.DdType;
import my.caijar.dingding.common.error.DdError;
import my.caijar.dingding.common.error.DdErrorException;
import my.caijar.dingding.oapi.constant.DdOapiEventConstants;

import java.util.Map;

/**
 * @title ct-dingfly-parent
 * @Description CT的fly系列项目
 * @Author CT
 * @Date 2020/5/12 10:07
 * @Version 1.0
 */
@NoArgsConstructor
@Getter
@Setter
public class DdOapiOutTextMessage extends DdOapiOutMessage {

	private String content;

}
