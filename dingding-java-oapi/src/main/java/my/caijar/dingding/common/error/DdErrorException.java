package my.caijar.dingding.common.error;

import com.taobao.api.ApiException;

/**
 * @title ct-dingfly-parent
 * @Description 钉钉全局异常
 * @Author 12870
 * @Date 2020/4/16 0:51
 * @Version 1.0
 */
public class DdErrorException extends ApiException {
    private DdError error;

    public DdErrorException(DdError error) {
        super(error.toString());
        this.error = error;
    }

    public DdErrorException(DdError error, Throwable cause) {
        super(error.toString(), cause);
        this.error = error;
    }

    public DdError getError() {
        return this.error;
    }
}
