package my.caijar.dingding.oapi.api.impl;

import my.caijar.dingding.common.bean.DdAccessToken;
import my.caijar.dingding.common.error.DdErrorException;
import my.caijar.dingding.oapi.config.DdOapiConfigStorage;
import my.caijar.dingding.oapi.util.AccessTokenUtil;

import java.util.concurrent.locks.Lock;

public class DdOapiServiceHttpClientImpl extends BaseDdOapiServiceImpl {

	/***
	 * @Desc get access_token implementation
	 * @param forceRefresh force update or not  true/false
	 * @return access_token
	 * @throws DdErrorException
	 */
    public String getAccessToken(boolean forceRefresh) throws DdErrorException {
        DdOapiConfigStorage config = this.getDdOapiConfigStorage();
        //没有过期并且不是强制更新
        if (!config.isAccessTokenExpired() && !forceRefresh) {
            return config.getAccessToken();
        } else {
            Lock lock = config.getAccessTokenLock();
            lock.lock();

            String accessToken;
            try {
                if (!config.isAccessTokenExpired() && !forceRefresh) {
                    return config.getAccessToken();
                }
                DdAccessToken ddToken = AccessTokenUtil.getToken(config);
                config.updateAccessToken(ddToken.getAccessToken(), ddToken.getExpiresIn());
                accessToken = config.getAccessToken();

            } finally {
                lock.unlock();
            }
            return accessToken;
        }
    }
}
