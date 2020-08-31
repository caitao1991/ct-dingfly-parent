package my.caijar.dingding.common.bean;

import lombok.*;

import java.io.Serializable;

/**
 * @title ct-dingfly-parent
 * @Description 钉钉JSAPI的封装。
 * @Author 12870
 * @Date 2020/4/13 23:36
 * @Version 1.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class DdJsapiSignature implements Serializable {

    /**
     * jsapi的ticket票据
     */
    private String jsApiTicket;

    /**
     * 当前网页的URL，不包含#及其后面部分
     */
    private String url;

    /**
     * 随机串，自己定义
     */
    private String nonceStr;

    /**
     * 应用的标识
     */
    private String agentId;

    /**
     * 时间戳
     */
    private long timeStamp;

    /**
     * 企业ID
     */
    private String corpId;

    /**
     * 验签签名
     */
    private String signature;

    public static DdJsapiSignature.DdJsapiSignatureBuilder builder() {
        return new DdJsapiSignature.DdJsapiSignatureBuilder();
    }

    public static class DdJsapiSignatureBuilder {
        private String corpId;
        private String nonceStr;
        private long timeStamp;
        private String url;
        private String agentId;
        private String signature;
        private String jsApiTicket;

        public DdJsapiSignatureBuilder() {
        }
        public DdJsapiSignature.DdJsapiSignatureBuilder corpId(String corpId) {
            this.corpId = corpId;
            return this;
        }
        public DdJsapiSignature.DdJsapiSignatureBuilder nonceStr(String nonceStr) {
            this.nonceStr = nonceStr;
            return this;
        }
        public DdJsapiSignature.DdJsapiSignatureBuilder timeStamp(long timeStamp) {
            this.timeStamp = timeStamp;
            return this;
        }
        public DdJsapiSignature.DdJsapiSignatureBuilder url(String url) {
            this.url = url;
            return this;
        }
        public DdJsapiSignature.DdJsapiSignatureBuilder agentId(String agentId) {
            this.agentId = agentId;
            return this;
        }
        public DdJsapiSignature.DdJsapiSignatureBuilder signature(String signature) {
            this.signature = signature;
            return this;
        }
        public DdJsapiSignature.DdJsapiSignatureBuilder jsApiTicket(String jsApiTicket) {
            this.jsApiTicket = jsApiTicket;
            return this;
        }
        public DdJsapiSignature build() {
            return new DdJsapiSignature(this.jsApiTicket, this.url, this.nonceStr, this.agentId, this.timeStamp, this.corpId, this.signature);
        }
    }

}
