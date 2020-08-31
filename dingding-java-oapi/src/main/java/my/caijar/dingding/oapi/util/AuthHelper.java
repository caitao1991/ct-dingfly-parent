
package my.caijar.dingding.oapi.util;

import com.dingtalk.oapi.lib.aes.DingTalkEncryptException;
import com.dingtalk.oapi.lib.aes.DingTalkJsApiSingnature;
import my.caijar.dingding.common.bean.DdJsapiSignature;
import my.caijar.dingding.common.error.DdError;
import my.caijar.dingding.common.error.DdErrorException;
import my.caijar.dingding.common.utils.RandomUtils;

/**
 * AccessToken和jsticket的获取封装
 */
public class AuthHelper {

    public static String sign(String ticket, String nonceStr, long timeStamp, String url) throws DdErrorException {
        try {
            return DingTalkJsApiSingnature.getJsApiSingnature(url, nonceStr, timeStamp, ticket);
        } catch (DingTalkEncryptException ex) {
            throw new DdErrorException(new DdError(Long.valueOf(ex.getCode()), ex.getMessage()));
        }
    }

    /**
     * 计算当前请求的jsapi的签名数据<br/>
     * <p>
     * 如果签名数据是通过ajax异步请求的话，签名计算中的url必须是给用户展示页面的url
     *
     * @return
     */
    public static DdJsapiSignature createJsapiSignature(String url, String agentId, String corpId, String ticket) {
        /**
         * 确认url与配置的应用首页地址一致
         */
        System.out.println(url);

        /**
         * 随机字符串，我这边设置的是16位的。
         */
        String nonceStr = RandomUtils.generateString(16);
        long timeStamp = System.currentTimeMillis() / 1000;
        String signature = null;
        try {
            signature = sign(ticket, nonceStr, timeStamp, url);
        } catch (DdErrorException e) {
            e.printStackTrace();
        }
        DdJsapiSignature ddJsapiSignature = new DdJsapiSignature.DdJsapiSignatureBuilder()
                .signature(signature)
                .agentId(agentId)
                .corpId(corpId)
                .timeStamp(timeStamp)
                .nonceStr(nonceStr)
                .jsApiTicket(ticket)
                .url(url)
                .build();

        return ddJsapiSignature;
    }

}
