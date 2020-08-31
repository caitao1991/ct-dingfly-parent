package my.caijar.dingding.common.error;

import com.dingtalk.api.response.OapiReportListResponse;
import com.dingtalk.api.response.OapiUserGetuserinfoResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import my.caijar.dingding.common.DdType;

import java.io.Serializable;

/**
 * @title ct-dingfly-parent
 * @Description 错误类封装
 * @Author 12870
 * @Date 2020/4/16 0:02
 * @Version 1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DdError implements Serializable {

    private Long errorCode;
    private String errorMsg;
    private String json;

	public DdError(String errorMsg) {
		this.errorMsg = errorMsg;
	}

    public DdError(Long errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public DdError fromSource(DdType type) {
        if (errorCode.intValue() != 0 && type != null) {

            String msg;
            switch(type) {
                case DWP:
                    msg = DdDwpErrorMsgEnum.findMsgByCode(errorCode);
                    if (msg != null) {
                        this.setErrorMsg(msg);
                    }
                    break;
                case DPay:

                    break;
                case DEApp:

                    break;
                default:
                    return this;
            }
        }
        return this;
    }

    public String toString() {
        return this.json == null ? "错误代码：" + this.errorCode + ", 错误信息：" + this.errorMsg : "错误代码：" + this.errorCode + ", 错误信息：" + this.errorMsg + "，钉钉原始报文：" + this.json;
    }

    public static class DdErrorBuilder {

        private Long errorCode;
        private String errorMsg;
        private String json;

        public DdErrorBuilder(){

        }

        public DdError.DdErrorBuilder errorCode(Long errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        public DdError.DdErrorBuilder errorMsg(String errorMsg) {
            this.errorMsg = errorMsg;
            return this;
        }

        public DdError.DdErrorBuilder json(String json) {
            this.json = json;
            return this;
        }

        public DdError build() {
            return new DdError(this.errorCode, this.errorMsg, this.json);
        }

        public String toString() {
            return "DdError.DdErrorBuilder(errorCode=" + this.errorCode + ", errorMsg=" + this.errorMsg + ", json=" + this.json + ")";
        }
    }

}
