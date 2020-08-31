package my.caijar.dingding.oapi.api.impl;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiDepartmentGetRequest;
import com.dingtalk.api.request.OapiDepartmentListRequest;
import com.dingtalk.api.response.OapiDepartmentGetResponse;
import com.dingtalk.api.response.OapiDepartmentListResponse;
import com.taobao.api.ApiException;
import my.caijar.dingding.common.DdType;
import my.caijar.dingding.common.error.DdError;
import my.caijar.dingding.common.error.DdErrorException;
import my.caijar.dingding.oapi.api.DdOapiService;
import my.caijar.dingding.oapi.api.DepartmentHelper;
import my.caijar.dingding.oapi.enums.DdOapiApiUrl;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @title ct-dingfly-parent
 * @Description CT的fly系列项目
 * @Author CT
 * @Date 2020/5/14 9:39
 * @Version 1.0
 */
public class DepartmentHelperImpl implements DepartmentHelper {

	private final DdOapiService ddOapiService;

	public DepartmentHelperImpl(DdOapiService ddOapiService) {
		this.ddOapiService = ddOapiService;
	}

	public List<OapiDepartmentListResponse.Department> getAllDeps(String depId, Boolean fetchChild) throws ApiException {
		String accessToken = this.ddOapiService.getAccessToken();
		DingTalkClient client = new DefaultDingTalkClient(DdOapiApiUrl.Department.DEPARTMENT_LIST_URL.getUrl(this.ddOapiService.getDdOapiConfigStorage()));
		OapiDepartmentListRequest request = new OapiDepartmentListRequest();
		if(StringUtils.isNotEmpty(depId)) {
			request.setId(depId);
		}
		request.setHttpMethod("GET");
		request.setFetchChild(fetchChild);

		OapiDepartmentListResponse response = client.execute(request, accessToken);
		if(response.getErrcode().longValue() != 0L) {
			throw new DdErrorException(new DdError(response.getErrcode(), response.getErrmsg()).fromSource(DdType.DWP));
		}
		return response.getDepartment();
	}

	public OapiDepartmentGetResponse getAllDepProfiles(Long depId) throws ApiException {
		if(depId == null) {
			return null;
		}
		String accessToken = this.ddOapiService.getDdOapiConfigStorage().getAccessToken();
		DingTalkClient client = new DefaultDingTalkClient(DdOapiApiUrl.Department.DEPARTMENT_DETAIL_URL.getUrl(this.ddOapiService.getDdOapiConfigStorage()));
		OapiDepartmentGetRequest request = new OapiDepartmentGetRequest();
		request.setId(String.valueOf(depId));
		request.setHttpMethod("GET");
		OapiDepartmentGetResponse response = client.execute(request, accessToken);
		if(response.getErrcode().longValue() != 0L) {
			throw new DdErrorException(new DdError(response.getErrcode(), response.getErrmsg()).fromSource(DdType.DWP));
		}
		return response;
	}
}
