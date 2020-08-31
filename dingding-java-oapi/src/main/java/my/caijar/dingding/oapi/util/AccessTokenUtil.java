package my.caijar.dingding.oapi.util;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.request.OapiGetJsapiTicketRequest;
import com.dingtalk.api.request.OapiGettokenRequest;
import com.dingtalk.api.response.OapiGetJsapiTicketResponse;
import com.dingtalk.api.response.OapiGettokenResponse;
import com.taobao.api.ApiException;
import my.caijar.dingding.common.DdType;
import my.caijar.dingding.common.bean.DdAccessToken;
import my.caijar.dingding.common.bean.JsapiTicket;
import my.caijar.dingding.common.error.DdError;
import my.caijar.dingding.common.error.DdErrorException;
import my.caijar.dingding.oapi.config.DdOapiConfigStorage;
import my.caijar.dingding.oapi.enums.DdOapiApiUrl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
  * @ClassName AccessTokenUtil
  * @Description get access_token and jsapi_token utility class
  * @Author  Tao Cai
  * @Date   2020/6/4 17:36
  *  @Version 1.0
  */
public class AccessTokenUtil {

    private static final Logger bizLogger = LoggerFactory.getLogger(AccessTokenUtil.class);

    public static DdAccessToken getToken(DdOapiConfigStorage storage) throws RuntimeException, DdErrorException {
        try {
            DefaultDingTalkClient client = new DefaultDingTalkClient(DdOapiApiUrl.IdentityVerify.IDENTITY_GET_TOKEN_URL.getUrl(storage));
            OapiGettokenRequest request = new OapiGettokenRequest();

            request.setAppkey(storage.getAppKey());
            request.setAppsecret(storage.getSecret());
            request.setHttpMethod("GET");
            OapiGettokenResponse response = client.execute(request);
            if(response.getErrcode().longValue() != 0L) {
                throw new DdErrorException(new DdError().fromSource(DdType.DWP));
            }
            String accessToken = response.getAccessToken();
            return DdAccessToken.build(accessToken);
        } catch (ApiException e) {
            bizLogger.error("getAccessToken failed, appKey=" + storage.getAppKey(), e);
            throw new RuntimeException();
        }

    }

    public static JsapiTicket getJsapiTicket(String accessToken, DdOapiConfigStorage storage) throws DdErrorException {
        try {
            DefaultDingTalkClient client = new DefaultDingTalkClient(DdOapiApiUrl.IdentityVerify.GET_JSAPI_TICKET_URL.getUrl(storage));
            OapiGetJsapiTicketRequest req = new OapiGetJsapiTicketRequest();
            req.setTopHttpMethod("GET");
            OapiGetJsapiTicketResponse execute = client.execute(req, accessToken);
            if(execute.getErrcode().longValue() == 0L) {
                return JsapiTicket.build(execute.getTicket(), execute.getExpiresIn());
            } else {
                throw new DdErrorException(new DdError().fromSource(DdType.DWP));
            }
        } catch (ApiException e) {
            bizLogger.error("getJsapiTicket failed", e);
            throw new RuntimeException();
        }
    }

}
