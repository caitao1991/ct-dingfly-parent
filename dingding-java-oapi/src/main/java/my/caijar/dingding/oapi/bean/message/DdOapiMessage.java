package my.caijar.dingding.oapi.bean.message;

import com.alibaba.fastjson.JSONObject;
import com.dingtalk.oapi.lib.aes.DingTalkEncryptor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import my.caijar.dingding.oapi.bean.DdOapiHostConfig;

import java.io.Serializable;
import java.util.Date;

/**
 * @title ct-dingfly-parent
 * @Description
 * @Author 12870
 * @Date 2020/4/28 1:02
 * @Version 1.0
 */
@Getter
@Setter
@NoArgsConstructor
public class DdOapiMessage implements Serializable {

	private String msgType;

	private String eventType;

	private String event;

 /*   private String processInstanceId;
    private Date finishTime;
    private String corpId;
    private String businessId;
    private String remark;
    private String title;
    private String type;
    private String result;
    private Date createTime;
    private String processCode;
    private String staffId;
    private String taskId;
    private String bizCategoryId;
    private String url;*/

	//回调返回的信息。
	private JSONObject backObject;


	public DdOapiMessage(String msgType, String eventType, String event, JSONObject backObject) {
		this.msgType = msgType;
		this.eventType = eventType;
		this.event = event;
		this.backObject = backObject;

	}


	public static class DdOapiMessageBuilder {

		private String msgType;
		private String eventType;
		private String event;
		private JSONObject backObject;

		public DdOapiMessageBuilder() {
		}

		public DdOapiMessage.DdOapiMessageBuilder msgType(String msgType) {
			this.msgType = msgType;
			return this;
		}

		public DdOapiMessage.DdOapiMessageBuilder eventType(String eventType) {
			this.eventType = eventType;
			return this;
		}

		public DdOapiMessage.DdOapiMessageBuilder event(String event) {
			this.event = event;
			return this;
		}

		public DdOapiMessage.DdOapiMessageBuilder backObject(JSONObject backObject) {
			this.backObject = backObject;
			return this;
		}


		public DdOapiMessage build() {
			return new DdOapiMessage(this.msgType, this.eventType, this.event, this.backObject);
		}

		public String toString() {
			return "DdOapiMessage.DdOapiMessageBuilder(msgType=" + this.msgType + "eventType=" + this.eventType + "event=" + this.event + "backObject=" +this.backObject.toJSONString() + ")";
		}
	}
}
