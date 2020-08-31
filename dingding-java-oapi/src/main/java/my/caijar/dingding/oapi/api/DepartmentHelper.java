package my.caijar.dingding.oapi.api;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiDepartmentGetRequest;
import com.dingtalk.api.request.OapiDepartmentListRequest;
import com.dingtalk.api.response.OapiDepartmentGetResponse;
import com.dingtalk.api.response.OapiDepartmentListResponse;
import com.taobao.api.ApiException;

import java.util.List;

/**
 * @title ct-dingfly-parent
 * @Description 部门管理接口
 * @Author CT
 * @Date 2020/5/13 15:53
 * @Version 1.0
 */
public interface DepartmentHelper {

	/***
	 * 获取所有部门列表;
	 * @param depId 父部门id（如果不传，默认部门为根部门，根部门ID为1）
	 * @param fetchChild 是否递归部门的全部子部门，ISV微应用固定传递false
	 */
	List<OapiDepartmentListResponse.Department> getAllDeps(String depId, Boolean fetchChild) throws ApiException;

	/***
	 * 获取部门详情列表;
	 * @return  OapiDepartmentGetResponse response
	 */
	OapiDepartmentGetResponse getAllDepProfiles(Long depId) throws ApiException;

}
