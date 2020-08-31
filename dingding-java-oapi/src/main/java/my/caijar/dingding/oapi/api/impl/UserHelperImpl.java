package my.caijar.dingding.oapi.api.impl;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.*;
import com.dingtalk.api.response.*;
import com.taobao.api.ApiException;
import my.caijar.dingding.common.DdType;
import my.caijar.dingding.common.error.DdError;
import my.caijar.dingding.common.error.DdErrorException;
import my.caijar.dingding.oapi.api.DdOapiService;
import my.caijar.dingding.oapi.api.UserHelper;
import my.caijar.dingding.oapi.enums.DdOapiApiUrl;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @title ct-dingfly-parent
 * @Description CT的fly系列项目
 * @Author CT
 * @Date 2020/6/17 16:33
 * @Version 1.0
 */
public class UserHelperImpl implements UserHelper {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private final DdOapiService ddOapiService;

	public UserHelperImpl(DdOapiService ddOapiService) {
		this.ddOapiService = ddOapiService;
	}

	public boolean deleteUser(String userId) throws ApiException {
		String accessToken = this.ddOapiService.getAccessToken();
		DingTalkClient client = new DefaultDingTalkClient(DdOapiApiUrl.User.USER_DELETE_URL.getUrl(this.ddOapiService.getDdOapiConfigStorage()));
		OapiUserDeleteRequest request = new OapiUserDeleteRequest();
		request.setUserid(userId);
		request.setHttpMethod("GET");
		OapiUserDeleteResponse response = client.execute(request, accessToken);
		if(response.getErrcode().longValue() != 0L) {
			return false;
		}
		return true;
	}

	public OapiUserGetuserinfoResponse getUserId(String authCode) throws ApiException{

		String accessToken = this.ddOapiService.getAccessToken();
		DingTalkClient client = new DefaultDingTalkClient(DdOapiApiUrl.IdentityVerify.IDENTITY_GETUSERINFO_URL.getUrl(this.ddOapiService.getDdOapiConfigStorage())); //
		OapiUserGetuserinfoRequest request = new OapiUserGetuserinfoRequest();
		request.setCode(authCode);
		request.setHttpMethod("GET");
		OapiUserGetuserinfoResponse response = client.execute(request, accessToken);
		if(response.getErrcode().longValue() != 0L) {
			throw new DdErrorException(new DdError(response.getErrcode(), response.getErrmsg()).fromSource(DdType.DWP));
		}
		return response;
	}

	public OapiUserGetResponse getUserProfile(String userId) throws ApiException {

		String accessToken = this.ddOapiService.getAccessToken();
		DingTalkClient client = new DefaultDingTalkClient(DdOapiApiUrl.User.USER_INFO_GET_URL.getUrl(this.ddOapiService.getDdOapiConfigStorage()));
		OapiUserGetRequest request = new OapiUserGetRequest();
		request.setUserid(userId);
		request.setHttpMethod("GET");
		OapiUserGetResponse response = client.execute(request, accessToken);
		if(response.getErrcode().longValue() != 0L) {
			throw new DdErrorException(new DdError(response.getErrcode(), response.getErrmsg()).fromSource(DdType.DWP));
		}
		return response;
	}

	public List<String> getDepUserIds(String deptId) throws ApiException {
		String accessToken = this.ddOapiService.getAccessToken();
		DingTalkClient client = new DefaultDingTalkClient(DdOapiApiUrl.User.USER_GET_DEPT_MEMBER_URL.getUrl(this.ddOapiService.getDdOapiConfigStorage()));
		OapiUserGetDeptMemberRequest req = new OapiUserGetDeptMemberRequest();
		req.setDeptId(deptId);
		req.setHttpMethod("GET");
		OapiUserGetDeptMemberResponse response = client.execute(req, accessToken);
		if(response.getErrcode().longValue() != 0L) {
			throw new DdErrorException(new DdError(response.getErrcode(), response.getErrmsg()).fromSource(DdType.DWP));
		}
		return response.getUserIds();
	}

	public OapiUserSimplelistResponse getDepUserSimpleList(Long deptId, Long offset, Long size, String order) throws ApiException {
		String accessToken = this.ddOapiService.getAccessToken();
		DingTalkClient client = new DefaultDingTalkClient(DdOapiApiUrl.User.USER_GET_DEPT_SIMPLELIST_URL.getUrl(this.ddOapiService.getDdOapiConfigStorage()));
		OapiUserSimplelistRequest request = new OapiUserSimplelistRequest();
		request.setDepartmentId(deptId);
		request.setOffset(offset);
		request.setSize(size);
		request.setOrder(order);
		request.setHttpMethod("GET");
		OapiUserSimplelistResponse response = client.execute(request, accessToken);
		if(response.getErrcode().longValue() != 0L) {
			throw new DdErrorException(new DdError(response.getErrcode(), response.getErrmsg()).fromSource(DdType.DWP));
		}
		return response;
	}

	public OapiUserListbypageResponse getDepUserProfiles(Long deptId, Long offset, Long size, String order) throws ApiException {
		String accessToken = this.ddOapiService.getAccessToken();
		DingTalkClient client = new DefaultDingTalkClient(DdOapiApiUrl.User.USER_GET_DEPT_LISTBYPAGE_URL.getUrl(this.ddOapiService.getDdOapiConfigStorage()));
		OapiUserListbypageRequest request = new OapiUserListbypageRequest();
		request.setDepartmentId(deptId);
		request.setOffset(offset);
		request.setSize(size);
		request.setOrder(order);
		request.setHttpMethod("GET");
		OapiUserListbypageResponse response = client.execute(request,accessToken);
		if(response.getErrcode().longValue() != 0L) {
			throw new DdErrorException(new DdError(response.getErrcode(), response.getErrmsg()).fromSource(DdType.DWP));
		}
		return response;
	}

	public List<OapiUserGetAdminResponse.AdminList> getAdminUserIds() throws ApiException {
		String accessToken = this.ddOapiService.getAccessToken();
		DingTalkClient client = new DefaultDingTalkClient(DdOapiApiUrl.User.USER_GET_ADMIN_URL.getUrl(this.ddOapiService.getDdOapiConfigStorage()));
		OapiUserGetAdminRequest request = new OapiUserGetAdminRequest();
		request.setHttpMethod("GET");
		OapiUserGetAdminResponse response = client.execute(request, accessToken);
		if(response.getErrcode().longValue() != 0L) {
			throw new DdErrorException(new DdError(response.getErrcode(), response.getErrmsg()).fromSource(DdType.DWP));
		}
		return response.getAdminList();
	}

	public List<Long> getAdminScopeByUserId(String userId) throws ApiException {
		String accessToken = this.ddOapiService.getAccessToken();
		DingTalkClient client = new DefaultDingTalkClient(DdOapiApiUrl.User.USER_GET_ADMIN_SCOPE_URL.getUrl(this.ddOapiService.getDdOapiConfigStorage()));
		OapiUserGetAdminScopeRequest req = new OapiUserGetAdminScopeRequest();
		req.setUserid(userId);
		OapiUserGetAdminScopeResponse response = client.execute(req, accessToken);
		if(response.getErrcode().longValue() != 0L) {
			throw new DdErrorException(new DdError(response.getErrcode(), response.getErrmsg()).fromSource(DdType.DWP));
		}
		return response.getDeptIds();
	}

	public String getUserIdByUnionId(String unionid) throws ApiException {
		String accessToken = this.ddOapiService.getAccessToken();
		DingTalkClient client = new DefaultDingTalkClient(DdOapiApiUrl.User.USER_GET_USERID_BY_UNIONID_URL.getUrl(this.ddOapiService.getDdOapiConfigStorage()));
		OapiUserGetUseridByUnionidRequest request = new OapiUserGetUseridByUnionidRequest();
		request.setUnionid(unionid);
		request.setHttpMethod("GET");
		OapiUserGetUseridByUnionidResponse response = client.execute(request, accessToken);
		if(response.getErrcode().longValue() != 0L) {
			throw new DdErrorException(new DdError(response.getErrcode(), response.getErrmsg()).fromSource(DdType.DWP));
		}
		return response.getUserid();
	}

	public String getUserIdByMobile(String mobile) throws ApiException {
		String accessToken = this.ddOapiService.getAccessToken();
		DingTalkClient client = new DefaultDingTalkClient(DdOapiApiUrl.User.USER_GET_BY_MOBILE_URL.getUrl(this.ddOapiService.getDdOapiConfigStorage()));
		OapiUserGetByMobileRequest request = new OapiUserGetByMobileRequest();
		request.setMobile(mobile);
		OapiUserGetByMobileResponse response = client.execute(request, accessToken);
		if(response.getErrcode().longValue() != 0L) {
			throw new DdErrorException(new DdError(response.getErrcode(), response.getErrmsg()).fromSource(DdType.DWP));
		}
		return response.getUserid();
	}

	public Long getOrgUserCount(Long onlyActive) throws ApiException {
		String accessToken = this.ddOapiService.getAccessToken();
		DingTalkClient client = new DefaultDingTalkClient(DdOapiApiUrl.User.USER_GET_ORG_USER_COUNT_URL.getUrl(this.ddOapiService.getDdOapiConfigStorage()));
		OapiUserGetOrgUserCountRequest request = new OapiUserGetOrgUserCountRequest();
		request.setOnlyActive(onlyActive);
		request.setHttpMethod("GET");
		OapiUserGetOrgUserCountResponse response = client.execute(request, accessToken);
		if(response.getErrcode().longValue() != 0L) {
			throw new DdErrorException(new DdError(response.getErrcode(), response.getErrmsg()).fromSource(DdType.DWP));
		}
		return response.getCount();
	}

	public OapiInactiveUserGetResponse getInactiveUser(String queryDate, Long offset, Long size) throws ApiException {
		String accessToken = this.ddOapiService.getAccessToken();
		DingTalkClient client = new DefaultDingTalkClient(DdOapiApiUrl.User.USER_GET_INACTIVE_URL.getUrl(this.ddOapiService.getDdOapiConfigStorage()));
		OapiInactiveUserGetRequest request = new OapiInactiveUserGetRequest();
		request.setOffset(offset);
		request.setSize(size);
		request.setQueryDate(queryDate);
		OapiInactiveUserGetResponse response = client.execute(request, accessToken);
		if(response.getErrcode().longValue() != 0L) {
			throw new DdErrorException(new DdError(response.getErrcode(), response.getErrmsg()).fromSource(DdType.DWP));
		}
		return response;
	}
}
