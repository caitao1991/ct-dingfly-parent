package my.caijar.dingding.oapi.api;

import com.alibaba.fastjson.JSON;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.*;
import com.dingtalk.api.response.*;
import com.taobao.api.ApiException;
import my.caijar.dingding.oapi.bean.approval.ProcessInstanceInputVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @title ct-dingfly-parent
 * @Description 智能工作流-》使用钉钉官方工作流（推荐）接口类
 * @Author CT
 * @Date 2020/6/18 11:43
 * @Version 1.0
 */
public interface ApprovalHelper {

	/**
	 * 发起审批实例
	 * @param processInstance 按照固定格式以json方式上传
	 *             let paramObj = {
	 *                     jsonVo: JSON.stringify(row), 			// 业务数据
	 *                     routUrl: this.$route.path,				// vue前端路由路径，用于根据配置表获取processCode
	 *                     params: JSON.stringify(params),			// 其他配置参数，如钉钉附件上传授权码，由前端获取并在后台调用接口上传。
	 *                     originatorUserId: this.$EMP.dd_user_id,	//
	 *                     deptId: this.$EMP.dd_maindept_id,
	 *                     textForms: [{
	 *                         name: "姓名",
	 *                         value: row.emp_name
	 *                     }, {
	 *                         name: "工号",
	 *                         value: row.job_number
	 *                     }],
	 *                     detailForms: [
	 *                           {
	 *                              name: "调整前",
	 *                              textForms: [{
	 *                              	name: "基本工资(元)",
	 *                              	value: row.before_base_salary
	 *                               }, {
	 *                                  name: "职等",
	 *                                  value: row.before_headship_str
	 *                                }]
	 *                            },
	 *                            {
	 *                               name: "调整后",
	 *                               textForms: [{
	 *                                   name: "基本工资(元)",
	 *                                   value: row.after_base_salary
	 *                               }, {
	 *                                   name: "职等",
	 *                                   value: row.after_headship_str
	 *                                }]
	 *                     }],
	 *                     appendixForms:[{
	 *                        name: "附件类型1",
	 *                        attachmentArray:[{
	 *							   "spaceId": "" //空间id
	 *                             "fileId": "", //文件id
	 *                             "fileName": "", //文件名
	 *                     		   "fileSize": 111111, //文件大小
	 *                     		   "fileType": "", //文件类型
	 *                        }]
	 *                     }]
	 *                 };
	 * @param processCode    审批模板编码。后期通过传过来的路由从后台获取。
	 * @return OapiProcessinstanceCreateResponse —》process_instance_id 实例ID
	 */
	String pIStart(ProcessInstanceInputVO processInstance, String processCode) throws ApiException;

	/**
	 * 批量获取审批实例id;
	 *  企业使用此接口审批单发起时间在某时间段内的审批实例id列表。
	 *  企业可以再根据审批实例id，调用获取审批实例详情接口，获取实例详情信息。
	 * @param processCode (必须) 流程模板唯一标识.可在OA管理后台编辑审批表单部分查询
	 * @param startTime (必须) 开始时间。Unix时间戳 单位毫秒
	 * @param endTime （可选）结束时间，不传该参数则默认取当前时间。Unix时间戳 单位毫秒
	 * @param size （可选） 分页参数，每页大小，最多传20，默认值：20
	 * @param cursor （可选） 分页查询的游标，最开始传0，后续传返回参数中的next_cursor值，默认值：0
	 * @param userIdList （可选）发起人用户id列表，用逗号分隔，最大列表长度：10
	 * @return
	 *  {
	 *     "result":{
	 *         "list":[
	 *                 "85bf4b7c-fc71-4489-aaab-65428d6e2176",
	 *                 "4dbc813f-c90b-4938-88ae-2786ee9a9cda"
	 *                ],
	 *         "next_cursor":1
	 *     },
	 *     "errcode":0,
	 *     "errmsg":"ok"
	 * }
	 *	next_cursor 表示下次查询的游标，当返回结果没有该字段时表示没有更多数据了
	 */
	OapiProcessinstanceListidsResponse.PageResult batchAchieveIDs(String processCode, Long startTime, Long endTime, Long size, Long cursor, String userIdList) throws ApiException;

	/**
	 * 根据审批实例详情
	 *	根据审批实例id调用此接口获取审批实例详情，包括审批表单信息、操作记录列表、操作人、抄送人、审批任务列表等。
	 * @param （必传）instanceId
	 * @return OapiProcessinstanceGetResponse.ProcessInstanceTopVo
	 */
	OapiProcessinstanceGetResponse.ProcessInstanceTopVo getPIDetail(String instanceId) throws ApiException;

	/**
	 * 获取用户待审批数量
	 *	调用此接口可以获取用户待处理的审批数量。开发者可以通过以下链接，使用H5微应用JSAPI-在新窗口上打开链接跳转到钉钉审批移动端微应用
	 *	（暂不支持PC端）的待我审批页面：https://aflow.dingtalk.com/dingtalk/mobile/homepage.htm?showmenu=false&dd_share=false&corpid=$CORPID#/upcoming?swfrom=work_homepage
	 * @param （必传）userId
	 * @return OapiProcessinstanceGetResponse.ProcessInstanceTopVo
	 */
	Long getPITodoNum(String userId) throws ApiException ;

	/***
	 *  获取用户可见的审批模板;
	 * @param userId (可选)，不传的话表示获取企业的所有模板
	 * @param offset (必传)，分页游标，从0开始。根据返回结果里的next_cursor是否为空来判断是否还有下一页，且再次调用时offset设置成next_cursor的值
	 * @param size (必传)，	分页大小，最大可设置成100
	 */
	OapiProcessListbyuseridResponse.HomePageProcessTemplateVo getProcessTemplateList(String userId, Long offset, Long size) throws ApiException ;


	/**
	 * 	获取审批钉盘空间信息
	 * 	获取审批钉盘空间信息接口，需结合前端API“上传附件到钉盘”使用，使用方法如下：
	 *
	 * （1）调用本文介绍的“获取审批钉盘空间信息”接口，获取钉盘控件的上传权限，并获取space_id
	 *
	 * （2）使用参数space_id，调用“H5微应用-JSAPI-上传附件到钉盘”或“小程序-上传附件到钉盘”后，获取钉盘附件的信息
	 *
	 * （3）调用发起审批实例接口，传递附件信息
	 *
	 * 	以上是官方常规操作，还有直接后台上传附件的流程，如果不清楚 请咨询QQ 1287032373
	 * @param userId (必传)
	 * @return
	 *   space_id 审批钉盘空间id
	 */
	Long getSpaceInfo(String userId) throws ApiException ;

	/**
	 *  预览审批附件
	 *  此接口需配合钉盘JSAPI使用，实现在企业应用内预览、下载审批附件的功能。使用方法如下：
	 *
	 * （1）调用本文接口，授权用户预览、下载审批附件，获取到space_id。每一次预览审批附件前，都需要调用该接口进行授权。
	 *
	 * （2）使用参数space_id，调用“H5微应用-JSAPI-预览钉盘文件”或“小程序-钉盘文件预览”后，获取钉盘附件的信息。
	 *
	 * @param processInstanceId (必传)，实例id
	 * @param userId (必传)，授权允许预览附件的用户id
	 * @param fileId (可选)，分页游标，从0开始。根据返回结果里的next_cursor是否为空来判断是否还有下一页，且再次调用时offset设置成next_cursor的值
	 * @param fileidList (可选)，附件id列表，支持批量授权
	 *
	 * @return
	 * 	space_id 审批在钉盘里的spaceId
	 */
	Long grantPreview(String processInstanceId, String fileId, String userId, List<String> fileidList) throws ApiException;

}
