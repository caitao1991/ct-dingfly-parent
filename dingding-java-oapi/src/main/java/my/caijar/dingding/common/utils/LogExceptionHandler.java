package my.caijar.dingding.common.utils;

import my.caijar.dingding.common.api.DdErrorExceptionHandler;
import my.caijar.dingding.common.error.DdErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @title ct-dingfly-parent
 * @Description
 * @Author 12870
 * @Date 2020/4/28 0:33
 * @Version 1.0
 */
public class LogExceptionHandler implements DdErrorExceptionHandler {

    private Logger log = LoggerFactory.getLogger(DdErrorExceptionHandler.class);

    public LogExceptionHandler() {
    }

    public void handle(DdErrorException e) {
        this.log.error("Error happens", e);
    }
}
