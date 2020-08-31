package com.github.bytecai.demo.dd.oapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
  * @ClassName DdOapiConfig
  * @Description read micro application information configuration class
  * @Author  Tao Cai
  * @Date   2020/6/4 17:39
  *  @Version 1.0
  */
@Configuration
public class DdOapiConfig {

	@Value("#{ddProperties.dd_weiname}")
	private String wei_name;

	@Value("#{ddProperties.dd_appkey}")
	private String app_key;

	@Value("#{ddProperties.dd_appsecret}")
	private String app_secret;

	@Value("#{ddProperties.dd_aeskey}")
	private String encoding_aes_key;

	@Value("#{ddProperties.dd_token}")
	private String encryp_token;

	@Value("#{ddProperties.dd_agentid}")
	private Long agent_id;

	@Value("#{ddProperties.dd_corpid}")
	private String corp_id;

	private String is_deleted = "0";

	@Value("#{ddProperties.dd_appdesc}")
	private String app_desc;

	@Value("#{ddProperties.dd_callback_url}")
	private String url;


	public DdOapiConfig() {}

	public String getWei_name() {
		return wei_name;
	}

	public void setWei_name(String wei_name) {
		this.wei_name = wei_name;
	}

	public String getApp_key() {
		return app_key;
	}

	public void setApp_key(String app_key) {
		this.app_key = app_key;
	}

	public String getApp_secret() {
		return app_secret;
	}

	public void setApp_secret(String app_secret) {
		this.app_secret = app_secret;
	}

	public String getEncoding_aes_key() {
		return encoding_aes_key;
	}

	public void setEncoding_aes_key(String encoding_aes_key) {
		this.encoding_aes_key = encoding_aes_key;
	}

	public String getEncryp_token() {
		return encryp_token;
	}

	public void setEncryp_token(String encryp_token) {
		this.encryp_token = encryp_token;
	}

	public Long getAgent_id() {
		return agent_id;
	}

	public void setAgent_id(Long agent_id) {
		this.agent_id = agent_id;
	}

	public String getCorp_id() {
		return corp_id;
	}

	public void setCorp_id(String corp_id) {
		this.corp_id = corp_id;
	}

	public String getIs_deleted() {
		return is_deleted;
	}

	public void setIs_deleted(String is_deleted) {
		this.is_deleted = is_deleted;
	}

	public String getApp_desc() {
		return app_desc;
	}

	public void setApp_desc(String app_desc) {
		this.app_desc = app_desc;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "DdOapiConfig{" +
			"wei_name='" + wei_name + '\'' +
			", app_key='" + app_key + '\'' +
			", app_secret='" + app_secret + '\'' +
			", encoding_aes_key='" + encoding_aes_key + '\'' +
			", encryp_token='" + encryp_token + '\'' +
			", agent_id=" + agent_id +
			", corp_id='" + corp_id + '\'' +
			", is_deleted='" + is_deleted + '\'' +
			", app_desc='" + app_desc + '\'' +
			", url='" + url + '\'' +
			'}';
	}
}
