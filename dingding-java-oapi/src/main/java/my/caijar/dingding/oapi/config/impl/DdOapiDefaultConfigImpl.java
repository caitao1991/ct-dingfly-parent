package my.caijar.dingding.oapi.config.impl;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import my.caijar.dingding.common.bean.DdAccessToken;
import my.caijar.dingding.oapi.bean.DdOapiHostConfig;
import my.caijar.dingding.oapi.config.DdOapiConfigStorage;
import my.caijar.dingding.oapi.enums.TicketType;

import java.io.File;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @title ct-dingfly-parent
 * @Description  Basic class of micro application storage information,
 * 				 including basic information of micro application and acquired temporary bill information
 * @Author CT
 * @Date 2020/4/18 1:04
 * @Version 1.0
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class DdOapiDefaultConfigImpl implements DdOapiConfigStorage {

    protected volatile String corpId;
    protected volatile Long agentId;
    protected volatile String appKey;
    protected volatile String secret;
    protected volatile String token;
    protected volatile String accessToken;
    protected volatile String aesKey;
    protected volatile long expiresTime;
    protected volatile String jsapiTicket;
    protected volatile long jsapiTicketExpiresTime;
    protected volatile String sdkTicket;
    protected volatile long sdkTicketExpiresTime;
    protected volatile File tmpDirFile;

	protected volatile String url;

    protected Lock accessTokenLock = new ReentrantLock();
    protected Lock jsapiTicketLock = new ReentrantLock();
    protected Lock sdkTicketLock = new ReentrantLock();

    public boolean isAccessTokenExpired() {
        return System.currentTimeMillis() > this.expiresTime;
    }

    public void expireAccessToken() {
        this.expiresTime = 0L;
    }

    public void updateAccessToken(DdAccessToken accessToken) {
        this.updateAccessToken(accessToken.getAccessToken(), accessToken.getExpiresIn());
    }

    public synchronized void updateAccessToken(String accessToken, int expiresInSeconds) {
        this.accessToken = accessToken;
        this.expiresTime = System.currentTimeMillis() + (long)(expiresInSeconds - 200) * 1000L;
    }

    public String getTicket(TicketType ticketType) {
        switch(ticketType) {
            case SDK:
                return this.sdkTicket;
            case JSAPI:
                return this.jsapiTicket;
            default:
                return null;
        }
    }

    public void setTicket(TicketType type, String ticket) {
        switch(type) {
            case SDK:
                this.sdkTicket = ticket;
                break;
            case JSAPI:
                this.jsapiTicket = ticket;
                break;
        }

    }

    public Lock getTicketLock(TicketType ticketType) {
        switch(ticketType) {
            case SDK:
                return this.sdkTicketLock;
            case JSAPI:
                return this.jsapiTicketLock;
            default:
                return null;
        }
    }

    public boolean isTicketExpired(TicketType ticketType) {
        switch(ticketType) {
            case SDK:
                return System.currentTimeMillis() > this.sdkTicketExpiresTime;
            case JSAPI:
                return System.currentTimeMillis() > this.jsapiTicketExpiresTime;
            default:
                return false;
        }
    }

    public void expireTicket(TicketType ticketType) {
        switch(ticketType) {
            case SDK:
                this.sdkTicketExpiresTime = 0L;
                break;
            case JSAPI: //jsapi鉴权ticket。
                this.jsapiTicketExpiresTime = 0L;
                break;
        }
    }

    public void updateTicket(TicketType ticketType, String ticket, int expiresInSeconds) {
        switch(ticketType) {
            case SDK:
                this.sdkTicket = ticket;
                this.sdkTicketExpiresTime = System.currentTimeMillis() + (long)(expiresInSeconds - 200) * 1000L;
                break;
            case JSAPI:
                this.jsapiTicket = ticket;
                this.jsapiTicketExpiresTime = System.currentTimeMillis() + (long)(expiresInSeconds - 200) * 1000L;
                break;
        }
    }


    public boolean autoRefreshToken() {
        return true;
    }

    public DdOapiHostConfig getHostConfig() {
        return null;
    }
}
