package my.caijar.dingding.oapi.api.impl;

import com.alibaba.fastjson.JSON;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.*;
import com.dingtalk.api.response.*;
import com.taobao.api.ApiException;
import my.caijar.dingding.common.DdType;
import my.caijar.dingding.common.error.DdError;
import my.caijar.dingding.common.error.DdErrorException;
import my.caijar.dingding.oapi.api.ApprovalHelper;
import my.caijar.dingding.oapi.api.DdOapiService;
import my.caijar.dingding.oapi.bean.approval.ProcessInstanceInputVO;
import my.caijar.dingding.oapi.enums.DdOapiApiUrl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @title ct-dingfly-parent
 * @Description 智能工作流-》使用钉钉官方工作流（推荐）实现类
 * @Author Cai Tao
 * @Date 2020/6/18 15:37
 * @Version 1.0
 */
public class ApprovalHelperImpl implements ApprovalHelper {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private final DdOapiService ddOapiService;

	public ApprovalHelperImpl(DdOapiService ddOapiService) {
		this.ddOapiService = ddOapiService;
	}

	public String pIStart(ProcessInstanceInputVO processInstance, String processCode) throws ApiException {
		String accessToken = ddOapiService.getAccessToken();
		DefaultDingTalkClient client = new DefaultDingTalkClient(DdOapiApiUrl.ApprovalApi.PI_CREATE_URL.getUrl(this.ddOapiService.getDdOapiConfigStorage()));
		OapiProcessinstanceCreateRequest request = new OapiProcessinstanceCreateRequest();
		request.setProcessCode(processCode);
		request.setFormComponentValues(processInstance.generateForms());
		request.setOriginatorUserId(processInstance.getOriginatorUserId());
		request.setDeptId(Long.valueOf(processInstance.getDeptId()));

		/**=============如果想复用审批固定流程，可以不用设置下面的值 start===============*/

		//审批人userid列表，最大列表长度20。多个审批人用逗号分隔，按传入的顺序依次审批
		request.setApprovers(processInstance.getApprovers());
		/**
		 * 审批人列表。支持会签/或签，优先级高于approvers变量
		 *  类型为：List<OapiProcessinstanceCreateRequest.ProcessInstanceApproverVo>
		 *  ProcessInstanceApproverVo
		 *  	userIds 审批人userid列表。会签/或签列表长度必须大于1，非会签/或签列表长度只能为1
		 *		taskActionType 审批类型。AND表示会签，OR表示或签，NONE表示单人
		 */
		request.setApproversV2(processInstance.getApprovers_v2());
		/**
		 * 抄送人userid列表，最大列表长度：20。多个抄送人用逗号分隔。该参数需要与cc_position参数一起传，抄送人才会生效；
		 * 该参数需要与approvers或approvers_v2参数一起传，抄送人才会生效；
		 */
		request.setCcList(processInstance.getCcList());
		//抄送时间，分为（START, FINISH, START_FINISH）
		request.setCcPosition(processInstance.getCcPosition());
		/**=============如果想复用审批固定流程，可以不用设置下面的值 end===============*/

		OapiProcessinstanceCreateResponse response = client.execute(request, accessToken);

		if (response.getErrcode().longValue() != 0) {
			throw new DdErrorException(new DdError(response.getErrcode(), response.getErrmsg()).fromSource(DdType.DWP));
		}
		return response.getProcessInstanceId();
	}

	public OapiProcessinstanceListidsResponse.PageResult batchAchieveIDs(String processCode, Long startTime, Long endTime, Long size, Long cursor, String userIdList) throws ApiException {
		String accessToken = ddOapiService.getAccessToken();
		DingTalkClient client = new DefaultDingTalkClient(DdOapiApiUrl.ApprovalApi.PI_LISTIDS_URL.getUrl(this.ddOapiService.getDdOapiConfigStorage()));
		OapiProcessinstanceListidsRequest req = new OapiProcessinstanceListidsRequest();
		req.setProcessCode(processCode);
		req.setStartTime(startTime);
		req.setEndTime(endTime);
		req.setSize(size);
		req.setCursor(cursor);
		req.setUseridList(userIdList);
		OapiProcessinstanceListidsResponse response = client.execute(req, accessToken);
		if (response.getErrcode().longValue() != 0) {
			throw new DdErrorException(new DdError(response.getErrcode(), response.getErrmsg()).fromSource(DdType.DWP));
		}
		return response.getResult();
	}

	public OapiProcessinstanceGetResponse.ProcessInstanceTopVo getPIDetail(String instanceId) throws ApiException {
		String accessToken = this.ddOapiService.getAccessToken();
		DingTalkClient client = new DefaultDingTalkClient(DdOapiApiUrl.ApprovalApi.PI_GET_URL.getUrl(this.ddOapiService.getDdOapiConfigStorage()));
		OapiProcessinstanceGetRequest request = new OapiProcessinstanceGetRequest();
		request.setProcessInstanceId(instanceId);
		OapiProcessinstanceGetResponse response = client.execute(request, accessToken);
		if (response.getErrcode().longValue() != 0) {
			throw new DdErrorException(new DdError(response.getErrcode(), response.getErrmsg()).fromSource(DdType.DWP));
		}
		return response.getProcessInstance();
	}

	public Long getPITodoNum(String userId) throws ApiException {
		String accessToken = this.ddOapiService.getAccessToken();
		DingTalkClient client = new DefaultDingTalkClient(DdOapiApiUrl.ApprovalApi.P_GETTODONUM_URL.getUrl(this.ddOapiService.getDdOapiConfigStorage()));
		OapiProcessGettodonumRequest req = new OapiProcessGettodonumRequest();
		req.setUserid(userId);
		OapiProcessGettodonumResponse response = client.execute(req, accessToken);
		if (response.getErrcode().longValue() != 0) {
			throw new DdErrorException(new DdError(response.getErrcode(), response.getErrmsg()).fromSource(DdType.DWP));
		}
		return response.getCount();
	}

	public OapiProcessListbyuseridResponse.HomePageProcessTemplateVo getProcessTemplateList(String userId, Long offset, Long size) throws ApiException {
		String accessToken = this.ddOapiService.getAccessToken();
		DingTalkClient client = new DefaultDingTalkClient(DdOapiApiUrl.ApprovalApi.P_LISTBYUSERID_URL.getUrl(this.ddOapiService.getDdOapiConfigStorage()));
		OapiProcessListbyuseridRequest request = new OapiProcessListbyuseridRequest();
		request.setUserid(userId);
		request.setOffset(offset);
		request.setSize(size);
		OapiProcessListbyuseridResponse response = client.execute(request, accessToken);
		if (response.getErrcode().longValue() != 0) {
			throw new DdErrorException(new DdError(response.getErrcode(), response.getErrmsg()).fromSource(DdType.DWP));
		}
		return response.getResult();
	}

	public Long getSpaceInfo(String userId) throws ApiException {
		String accessToken = this.ddOapiService.getAccessToken();
		DingTalkClient client = new DefaultDingTalkClient(DdOapiApiUrl.ApprovalApi.PI_CSPACE_INFO_URL.getUrl(this.ddOapiService.getDdOapiConfigStorage()));
		OapiProcessinstanceCspaceInfoRequest req = new OapiProcessinstanceCspaceInfoRequest();
		req.setUserId(userId);
		OapiProcessinstanceCspaceInfoResponse response = client.execute(req, accessToken);
		if (response.getErrcode().longValue() != 0) {
			throw new DdErrorException(new DdError(response.getErrcode(), response.getErrmsg()).fromSource(DdType.DWP));
		}
		return response.getResult().getSpaceId();
	}

	public Long grantPreview(String processInstanceId, String fileId, String userId, List<String> fileidList) throws ApiException {
		DingTalkClient client = new DefaultDingTalkClient(DdOapiApiUrl.ApprovalApi.PI_CSPACE_PREVIEW_URL.getUrl(this.ddOapiService.getDdOapiConfigStorage()));
		OapiProcessinstanceCspacePreviewRequest req = new OapiProcessinstanceCspacePreviewRequest();
		OapiProcessinstanceCspacePreviewRequest.GrantCspaceRequest obj1 = new OapiProcessinstanceCspacePreviewRequest.GrantCspaceRequest();
		obj1.setAgentid(this.ddOapiService.getDdOapiConfigStorage().getAgentId());
		obj1.setProcessInstanceId(processInstanceId);
		obj1.setFileidList(fileidList);
		obj1.setUserid(userId);
		req.setRequest(obj1);
		OapiProcessinstanceCspacePreviewResponse response = client.execute(req, this.ddOapiService.getAccessToken());
		if (response.getErrcode().longValue() != 0) {
			throw new DdErrorException(new DdError(response.getErrcode(), response.getErrmsg()).fromSource(DdType.DWP));
		}
		return response.getResult().getSpaceId();
	}
}
