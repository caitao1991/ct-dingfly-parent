package my.caijar.dingding.oapi.api.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dingtalk.oapi.lib.aes.DingTalkEncryptException;
import com.dingtalk.oapi.lib.aes.DingTalkEncryptor;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import my.caijar.dingding.common.DdType;
import my.caijar.dingding.common.bean.DdJsapiSignature;
import my.caijar.dingding.common.bean.JsapiTicket;
import my.caijar.dingding.common.error.DdError;
import my.caijar.dingding.common.error.DdErrorException;
import my.caijar.dingding.oapi.api.*;
import my.caijar.dingding.oapi.config.DdOapiConfigStorage;
import my.caijar.dingding.oapi.constant.DdOapiEventConstants;
import my.caijar.dingding.oapi.enums.TicketType;
import my.caijar.dingding.oapi.util.AccessTokenUtil;
import my.caijar.dingding.oapi.util.AuthHelper;
import my.caijar.dingding.oapi.util.DdOapiConfigStorageHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.locks.Lock;

/**
 * @title  ct-dingfly-parent
 * @Description  API interface calling service base class
 * @Author  Tao Cai
 * @Date  2020/4/22 3:27
 * @Version  1.0
 */

@NoArgsConstructor
@Getter
@Setter
public abstract class BaseDdOapiServiceImpl implements DdOapiService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

	private static final String ENCRYPT = "encrypt";

	private DepartmentHelper departmentHelper = new DepartmentHelperImpl(this);
	private CBManagerHelper cbManagerHelper = new CBManagerHelperImpl(this);
	private UserHelper userHelper = new UserHelperImpl(this);
	private ApprovalHelper approvalHelper = new ApprovalHelperImpl(this);

    private Map<String, DdOapiConfigStorage> configStorageMap;
    private int retrySleepMillis = 1000;
    private int maxRetryTimes = 5;

	/***
	 * during the event callback, verify whether the signature is correct,
	 * 	and verify the signature to ensure that the parameters are not tampered with.
	 * @param json
	 * @param timestamp
	 * @param nonce
	 * @param signature
	 * @return
	 */
    public JSONObject checkSignature(JSONObject json, String timestamp, String nonce, String signature) {
        try {
            String encryptMsg = json.getString(ENCRYPT);
            String plainText = this.getDingTalkEncryptor().getDecryptMsg(signature, timestamp, nonce, encryptMsg);
            return JSON.parseObject(plainText);
        } catch (DingTalkEncryptException e) {
            logger.error("Checking signature failed, and the reason is :" + e.getMessage());
        } catch (DdErrorException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * generate dingding callback encryption and decryption tool class
	 * @return  DingTalkEncryptor
	 * @throws  DdErrorException
	 */
	public DingTalkEncryptor getDingTalkEncryptor() throws DdErrorException {
        try {
            return new DingTalkEncryptor(this.getDdOapiConfigStorage().getToken(), this.getDdOapiConfigStorage().getAesKey(),
                    this.getDdOapiConfigStorage().getCorpId());
        } catch (DingTalkEncryptException e) {
            throw new DdErrorException(new DdError(Long.valueOf(e.getCode()), e.getMessage()).fromSource(DdType.DWP));
        }
    }

	/***
	 * get access_token ticket to request.
	 * @return  access_token
	 * @throws  DdErrorException
	 */
    public String getAccessToken() throws DdErrorException {
        return this.getAccessToken(false);
    }

    public String getTicket(TicketType ticketType) throws DdErrorException {
        return this.getTicket(ticketType, false);
    }

	/**
	 * get jsapi authentication ticket.
	 * @param  ticketType
	 * 		   ticket type, enumeration type
	 * @param  forceRefresh
	 * 		   force update or not
	 * @return
	 * @throws DdErrorException
	 */
    public String getTicket(TicketType ticketType, boolean forceRefresh) throws DdErrorException {
        Lock ticketLock = this.getDdOapiConfigStorage().getTicketLock(ticketType);
        try {
            ticketLock.lock();
            if (forceRefresh) {
                this.getDdOapiConfigStorage().expireTicket(ticketType);
            }

            if (this.getDdOapiConfigStorage().isTicketExpired(ticketType)) {
                if(TicketType.JSAPI == ticketType) {
                    JsapiTicket jsapiTicket = AccessTokenUtil.getJsapiTicket(this.getAccessToken(), this.getDdOapiConfigStorage());
                    this.getDdOapiConfigStorage().updateTicket(ticketType, jsapiTicket.getTicket(), jsapiTicket.getExpiresIn().intValue());
                }
            }
        } finally {
            ticketLock.unlock();
        }
        return this.getDdOapiConfigStorage().getTicket(ticketType);
    }

    public String getJsapiTicket() throws DdErrorException {
        return this.getJsapiTicket(false);
    }

    public String getJsapiTicket(boolean forceRefresh) throws DdErrorException {
        return this.getTicket(TicketType.JSAPI, forceRefresh);
    }

    public DdOapiConfigStorage getDdOapiConfigStorage() {
        return this.configStorageMap.size() == 1 ? (DdOapiConfigStorage) this.configStorageMap.values().iterator().next() : (DdOapiConfigStorage)this.configStorageMap.get(DdOapiConfigStorageHolder.get());
    }

    public void setDdOapiConfigStorage(DdOapiConfigStorage ddConfigProvider) {
        String defaultOapiId = DdOapiConfigStorageHolder.get();
        this.setMultiConfigStorages(ImmutableMap.of(defaultOapiId, ddConfigProvider), defaultOapiId);
    }

	public DdJsapiSignature createJsapiSignature(String url) throws DdErrorException {
		return AuthHelper.createJsapiSignature(url, String.valueOf(this.getDdOapiConfigStorage().getAgentId()), this.getDdOapiConfigStorage().getCorpId(), this.getJsapiTicket(false));
	}

	/**
	 * single micro application configuration add
	 * @param appKey
	 * 		  appkey of micro application as key
	 * @param storage
	 * 		  Store the micro application information corresponding to the appkey
	 */
	public void addConfigStorage(String appKey, DdOapiConfigStorage storage) {
        synchronized(this) {
            if (this.configStorageMap.containsKey(appKey)) {
                throw new RuntimeException("The microapplication ID already exists, please replace another ID！");
            } else {
                this.configStorageMap.put(appKey, storage);
            }
        }
    }

    public void removeConfigStorage(String appKey) {
        synchronized(this) {
            this.configStorageMap.remove(appKey);
        }
    }

	/***
	 * add multiple micro applications at the same time. The first micro application is used by default.
	 * @param configStorages
	 */
	public void setMultiConfigStorages(Map<String, DdOapiConfigStorage> configStorages) {
        this.setMultiConfigStorages(configStorages, (String)configStorages.keySet().iterator().next());
    }

	/**
	 * <p> Store the read multiple micro application configurations into memory,
	 * 		and set the current micro application
	 * @param  configStorages
	 * 		   configuration information of multiple micro applications
	 * @param  appKey
	 * 		   set the current micro application according to the incoming appkey
	 */
    public void setMultiConfigStorages(Map<String, DdOapiConfigStorage> configStorages, String appKey) {
        this.configStorageMap = Maps.newHashMap(configStorages);
        DdOapiConfigStorageHolder.set(appKey);
    }

    public DdOapiService switchoverTo(String appKey) {
        if (this.configStorageMap.containsKey(appKey)) {
            DdOapiConfigStorageHolder.set(appKey);
            return this;
        } else {
            throw new RuntimeException(String.format("无法找到对应【%s】的微应用配置信息，请核实！", appKey));
        }
    }

    public boolean switchover(String appKey) {
        if (this.configStorageMap.containsKey(appKey)) {
            DdOapiConfigStorageHolder.set(appKey);
            return true;
        } else {
            logger.error("无法找到对应【{}】的微应用配置信息，请核实！", appKey);
            return false;
        }
    }

    public static void main(String[] args) {
        String test = "a";
        String numStr = "97";
        System.out.println(test.hashCode());
        System.out.println(numStr.hashCode());
    }
}
