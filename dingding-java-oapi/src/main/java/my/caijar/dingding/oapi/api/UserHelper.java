package my.caijar.dingding.oapi.api;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.*;
import com.dingtalk.api.response.*;
import com.taobao.api.ApiException;
import my.caijar.dingding.common.error.DdErrorException;

import java.util.ArrayList;
import java.util.List;

/**
 * @title ct-dingfly-parent
 * @Description 用户管理
 * @Author CT
 * @Date 2020/5/13 15:53
 * @Version 1.0
 */
public interface UserHelper {


	/**
	 *  删除用户
	 *  @param userId (必传)员工id
	 *  @return
	 */
	boolean deleteUser(String userId) throws ApiException;

	/***
	 *  免登码获取用户 userid
	 *  @param authCode (必传)免登授权码
	 *  @return
	 *  is_sys 是否是管理员，true：是，false：不是
	 *  sys_level 级别。1是主管理员，2是子管理员，100是老板，0是其他（如普通员工）
	 *  {
	 *     "userid": "****",
	 *     "sys_level": 1,
	 *     "errmsg": "ok",
	 *     "is_sys": true,
	 *     "errcode": 0
	 * }
	 */
	OapiUserGetuserinfoResponse getUserId(String authCode) throws ApiException;

	/**
	 *  获取用户详情
	 *  @param userId (必传)员工id
	 *  @return
	 */
	OapiUserGetResponse getUserProfile(String userId) throws ApiException;

	/**
	 * 	获取部门用户userid列表.
	 * 【推荐使用】通过该接口，可以获取当前部门下的userid列表
	 *	@param deptId (必传)部门id
	 * 	@return
	 * {
	 *     "errcode": 0,
	 *     "errmsg": "ok",
	 *     "userIds": ["1","2"]
	 * }
	 */
	List<String> getDepUserIds(String deptId) throws ApiException;

	/**
	 * 	获取部门用户
	 * 	@param deptId 获取的部门id。
	 * 	@param offset (否)支持分页查询，与size参数同时设置时才生效，此参数代表偏移量,偏移量从0开始
	 * 	@param size (否)支持分页查询，与offset参数同时设置时才生效，此参数代表分页大小，最大100
	 * 	@param order (否)支持分页查询，部门成员的排序规则，默认 是按自定义排序；
	 * 		   	entry_asc：代表按照进入部门的时间升序，
	 * 		   	entry_desc：代表按照进入部门的时间降序，
	 *   		modify_asc：代表按照部门信息修改时间升序，
	 * 			modify_desc：代表按照部门信息修改时间降序，
	 * 			custom：代表用户定义(未定义时按照拼音)排序
	 * 	@return
	 * {
	 *     "errcode": 0,
	 *     "errmsg": "ok",
	 *     "hasMore": false,
	 *     "userlist": [
	 *         {
	 *             "userid": "zhangsan",
	 *             "name": "张三"
	 *         }
	 *     ]
	 * }
	 *
	 */
	OapiUserSimplelistResponse getDepUserSimpleList(Long deptId, Long offset, Long size, String order) throws ApiException;

	/**
	 *  获取部门用户详情
	 * 	@param deptId (必传)如果为根目录（1），就只能查到一个人。
	 * 	@param offset (必传)支持分页查询，与size参数同时设置时才生效，此参数代表偏移量,偏移量从0开始
	 * 	@param size (必传)支持分页查询，与offset参数同时设置时才生效，此参数代表分页大小，最大100
	 * 	@param order (否)支持分页查询，部门成员的排序规则，默认 是按自定义排序；
	 * 		   	entry_asc：代表按照进入部门的时间升序，
	 * 		   	entry_desc：代表按照进入部门的时间降序，
	 *   		modify_asc：代表按照部门信息修改时间升序，
	 * 			modify_desc：代表按照部门信息修改时间降序，
	 * 			custom：代表用户定义(未定义时按照拼音)排序
	 *  @return
	 */
	OapiUserListbypageResponse getDepUserProfiles(Long deptId, Long offset, Long size, String order) throws ApiException;

	/**
	 *	获取管理员列表
	 *  @return
	 *  sys_level: 管理员角色，1表示主管理员，2表示子管理员
	 *  {
	 *     "errcode": 0,
	 *     "errmsg": "ok",
	 *     "admin_list":[
	 *         {"sys_level":2,"userid":"userid2"},
	 *         {"sys_level":1,"userid":"userid1"}
	 *     ]
	 *  }
	 */
	List<OapiUserGetAdminResponse.AdminList> getAdminUserIds() throws ApiException;

	/**
	 *	获取管理员通讯录权限范围
	 *  @param userId (必传)员工id
	 * @return 部门员工userId集合。
	 * {
	 *     "errcode": 0,
	 *     "errmsg": "ok",
	 *     "dept_ids":[1,2]
	 * }
	 */
	List<Long> getAdminScopeByUserId(String userId) throws ApiException;

	/**
	 *	根据unionid获取userid
	 *  @param unionid 员工在当前开发者企业账号范围内的唯一标识，系统生成，固定值，不会改变
	 * @return 用户userId
	 */
	String getUserIdByUnionId(String unionid) throws ApiException;

	/**
	 *	根据手机号获取userid, 企业使用此接口可通过手机号获取其所对应员工的userid。
	 *  @param mobile (必传)员工在当前开发者企业账号范围内的唯一标识，系统生成，固定值，不会改变
	 *
	 * @return userId
	 *
	 */
	String getUserIdByMobile(String mobile) throws ApiException;

	/**
	 *	获取企业员工人数。
	 *  @param onlyActive (必传) 0：包含未激活钉钉的人员数量 1：不包含未激活钉钉的人员数量
	 * @return count
	 */
	Long getOrgUserCount(Long onlyActive) throws ApiException;

	/**
	 *	未登录钉钉的员工列表: 企业使用此接口可查询指定日期内未登录钉钉的企业员工列表 (每天9点后调用接口才能确保获取前一天数据)
	 *  @param queryDate (必传) 查询日期 示例：20190808
	 *         offset (必传) 分页数据偏移量，从0开始
	 *         size (必传) 每页大小，最大100
	 * @return
	 * {
	 *   "errcode": 0,
	 *   "errmsg":"成功",
	 *   "result": {
	 *     "has_more": false,
	 *     "list": [
	 *       "1075xxxxx",
	 *       "1075xxxxx",
	 *       "000000000"
	 *     ]
	 *   }
	 * }
	 */
	OapiInactiveUserGetResponse  getInactiveUser(String queryDate, Long offset, Long size) throws ApiException;
}
