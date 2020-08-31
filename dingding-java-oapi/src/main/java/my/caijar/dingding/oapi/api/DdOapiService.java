package my.caijar.dingding.oapi.api;

import com.alibaba.fastjson.JSONObject;
import com.dingtalk.oapi.lib.aes.DingTalkEncryptor;
import my.caijar.dingding.common.bean.DdJsapiSignature;
import my.caijar.dingding.common.error.DdErrorException;
import my.caijar.dingding.oapi.config.DdOapiConfigStorage;
import my.caijar.dingding.oapi.enums.TicketType;

import java.util.Map;

/**
 * @title ct-dingfly-parent
 * @Description 通用类获取接口
 * @Author 12870
 * @Date 2020/4/22 2:55
 * @Version 1.0
 */
public interface DdOapiService {

    JSONObject checkSignature(JSONObject json, String timestamp, String nonce, String signature);

    DingTalkEncryptor getDingTalkEncryptor() throws DdErrorException;

    String getAccessToken() throws DdErrorException;

    String getAccessToken(boolean var1) throws DdErrorException;

    String getTicket(TicketType ticketType) throws DdErrorException;

    String getTicket(TicketType ticketType, boolean forceRefresh) throws DdErrorException;

    String getJsapiTicket() throws DdErrorException;

    String getJsapiTicket(boolean forceRefresh) throws DdErrorException;

    DdJsapiSignature createJsapiSignature(String var1) throws DdErrorException;

    DdOapiConfigStorage getDdOapiConfigStorage();

    void setDdOapiConfigStorage(DdOapiConfigStorage storage);

    void addConfigStorage(String oapiId, DdOapiConfigStorage storage);

    void removeConfigStorage(String oapiId);

    void setMultiConfigStorages(Map<String, DdOapiConfigStorage> var1);

    void setMultiConfigStorages(Map<String, DdOapiConfigStorage> var1, String var2);

    boolean switchover(String appKey);

    DdOapiService switchoverTo(String appKey);

    DepartmentHelper getDepartmentHelper();
}
