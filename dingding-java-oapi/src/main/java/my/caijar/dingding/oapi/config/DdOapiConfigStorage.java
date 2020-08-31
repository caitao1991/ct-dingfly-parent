package my.caijar.dingding.oapi.config;

import my.caijar.dingding.common.bean.DdAccessToken;
import my.caijar.dingding.oapi.bean.DdOapiHostConfig;
import my.caijar.dingding.oapi.enums.TicketType;

import java.util.concurrent.locks.Lock;

/**
 * @title ct-dingfly-parent
 * @Description CT的fly系列项目
 * @Author CT
 * @Date 2020/4/7 9:23
 * @Version 1.0
 */
public interface DdOapiConfigStorage {

    String getAccessToken();

    Lock getAccessTokenLock();

    boolean isAccessTokenExpired();

    void expireAccessToken();

    void updateAccessToken(DdAccessToken var1);

    void updateAccessToken(String var1, int var2);

    String getTicket(TicketType ticketType);

    Lock getTicketLock(TicketType ticketType);

    boolean isTicketExpired(TicketType ticketType);

    void expireTicket(TicketType ticketType);

    void updateTicket(TicketType ticketType, String var2, int var3);

    String getAppKey();

    String getSecret();

    String getToken();

    String getAesKey();

    String getCorpId();

    Long getAgentId();

    String getUrl();

    long getExpiresTime();

    boolean autoRefreshToken();

    DdOapiHostConfig getHostConfig();

}
