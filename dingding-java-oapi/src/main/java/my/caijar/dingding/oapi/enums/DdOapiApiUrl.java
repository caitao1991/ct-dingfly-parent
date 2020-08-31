package my.caijar.dingding.oapi.enums;

import my.caijar.dingding.oapi.bean.DdOapiHostConfig;
import my.caijar.dingding.oapi.config.DdOapiConfigStorage;

/**
 * @title ct-dingfly-parent
 * @Description CT的fly系列项目
 * @Author CT
 * @Date 2020/4/8 9:21
 * @Version 1.0
 */
public interface DdOapiApiUrl {
    String getUrl(DdOapiConfigStorage configStorage);

    //身份验证
    public static enum IdentityVerify implements DdOapiApiUrl {
        //获取用户userid,通过免登授权码和access_token获取用户的userid
        IDENTITY_GETUSERINFO_URL("https://oapi.dingtalk.com", "/user/getuserinfo"),
        //获取accesstoken
        IDENTITY_GET_TOKEN_URL("https://oapi.dingtalk.com", "/gettoken"),
        //服务端通过临时授权码获取授权用户的个人信息
        IDENTITY_GET_BYCODE_URL("https://oapi.dingtalk.com", "/sns/getuserinfo_bycode"),
        //获取jsapi_ticket
        GET_JSAPI_TICKET_URL("https://oapi.dingtalk.com", "/get_jsapi_ticket");

        private String prefix;
        private String path;

        public String getUrl(DdOapiConfigStorage config) {
            return DdOapiHostConfig.buildUrl(config.getHostConfig(), this.prefix, this.path);
        }

        private IdentityVerify(String prefix, String path) {
            this.prefix = prefix;
            this.path = path;
        }
    }

    //回调管理
    public static enum CbManagement implements DdOapiApiUrl {
        //注册业务事件回调接口
        CB_REGISTER_URL("https://oapi.dingtalk.com", "/call_back/register_call_back"),
        //查询事件回调接口
        CB_GET_URL("https://oapi.dingtalk.com", "/call_back/get_call_back"),
        //更新事件回调接口
        CB_UPDATE_URL("https://oapi.dingtalk.com", "/call_back/update_call_back"),
        //删除事件回调接口
        CB_DELETE_URL("https://oapi.dingtalk.com", "/call_back/delete_call_back"),
        //获取回调失败的结果
        CB_FAILED_RESULT_URL("https://oapi.dingtalk.com", "/call_back/get_call_back_failed_result");

        private String prefix;
        private String path;

        public String getUrl(DdOapiConfigStorage config) {
            return DdOapiHostConfig.buildUrl(config.getHostConfig(), this.prefix, this.path);
        }

        private CbManagement(String prefix, String path) {
            this.prefix = prefix;
            this.path = path;
        }
    }

    //审批相关api
    public static enum ApprovalApi implements DdOapiApiUrl {
        //发起审批实例
        PI_CREATE_URL("https://oapi.dingtalk.com", "/topapi/processinstance/create"),
        //批量获取审批实例ID
        PI_LISTIDS_URL("https://oapi.dingtalk.com", "/topapi/processinstance/listids"),
        //获取审批实例详情
        PI_GET_URL("https://oapi.dingtalk.com", "/topapi/processinstance/get"),
        //获取用户待审批数量，可以通过链接跳转到待我审批页
        P_GETTODONUM_URL("https://oapi.dingtalk.com", "/topapi/process/gettodonum"),
        //获取用户可见的审批模板
        P_LISTBYUSERID_URL("https://oapi.dingtalk.com", "/topapi/process/listbyuserid"),
        //获取审批钉盘空间信息接口
        PI_CSPACE_INFO_URL("https://oapi.dingtalk.com", "/topapi/processinstance/cspace/info"),
        //预览审批附件
        PI_CSPACE_PREVIEW_URL("https://oapi.dingtalk.com", "/topapi/processinstance/cspace/preview");


        private String prefix;
        private String path;

        public String getUrl(DdOapiConfigStorage config) {
            return DdOapiHostConfig.buildUrl(config.getHostConfig(), this.prefix, this.path);
        }

        private ApprovalApi(String prefix, String path) {
            this.prefix = prefix;
            this.path = path;
        }
    }

    //考勤
    public static enum AttendanceWork implements DdOapiApiUrl {
        //获取打卡详情
        ATTENDANCE_LISTRECORD_URL("https://oapi.dingtalk.com", "/attendance/listRecord"),
        //获取打卡结果
        ATTENDANCE_LIST_URL("https://oapi.dingtalk.com", "/attendance/list"),
        //查询企业考勤排班详情
        ATTENDANCE_LISTSCHEDULE_URL("https://oapi.dingtalk.com", "/topapi/attendance/listschedule");


        private String prefix;
        private String path;

        public String getUrl(DdOapiConfigStorage config) {
            return DdOapiHostConfig.buildUrl(config.getHostConfig(), this.prefix, this.path);
        }

        private AttendanceWork(String prefix, String path) {
            this.prefix = prefix;
            this.path = path;
        }
    }

    public static enum Department implements DdOapiApiUrl {
		//获取部门列表
		DEPARTMENT_LIST_URL("https://oapi.dingtalk.com", "/department/list"),
		DEPARTMENT_DETAIL_URL("https://oapi.dingtalk.com", "/department/get");
		private String prefix;
		private String path;
		public String getUrl(DdOapiConfigStorage config) {
			return DdOapiHostConfig.buildUrl(config.getHostConfig(), this.prefix, this.path);
		}
		private Department(String prefix, String path) {
			this.prefix = prefix;
			this.path = path;
		}
	}

    //用户管理
    public static enum User implements DdOapiApiUrl {

        //创建用户
        USER_CREATE_URL("https://oapi.dingtalk.com", "/user/create"),
        //更新用户
        USER_UPDATE_URL("https://oapi.dingtalk.com", "/user/update"),
        //删除用户
        USER_DELETE_URL("https://oapi.dingtalk.com", "/user/delete"),
        //获取用户详情
        USER_INFO_GET_URL("https://oapi.dingtalk.com", "/user/get"),
        //获取部门用户userid列表
        USER_GET_DEPT_MEMBER_URL("https://oapi.dingtalk.com", "/user/getDeptMember"),
        //获取部门用户
        USER_GET_DEPT_SIMPLELIST_URL("https://oapi.dingtalk.com", "/user/simplelist"),
        //获取部门用户详情
        USER_GET_DEPT_LISTBYPAGE_URL("https://oapi.dingtalk.com", "/user/listbypage"),
        //获取管理员列表
        USER_GET_ADMIN_URL("https://oapi.dingtalk.com", "/user/get_admin"),
        //获取管理员通讯录权限范围
        USER_GET_ADMIN_SCOPE_URL("https://oapi.dingtalk.com", "/topapi/user/get_admin_scope"),
        //根据unionid获取userid
        USER_GET_USERID_BY_UNIONID_URL("https://oapi.dingtalk.com", "/user/getUseridByUnionid"),
        //根据手机号获取userid
        USER_GET_BY_MOBILE_URL("https://oapi.dingtalk.com", "/user/get_by_mobile"),
        //获取企业员工人数
        USER_GET_ORG_USER_COUNT_URL("https://oapi.dingtalk.com", "/user/get_org_user_count"),
        //未登录钉钉的员工列表
        USER_GET_INACTIVE_URL("https://oapi.dingtalk.com", "/topapi/inactive/user/get");

        private String prefix;
        private String path;

        public String getUrl(DdOapiConfigStorage config) {
            return DdOapiHostConfig.buildUrl(config.getHostConfig(), this.prefix, this.path);
        }

        private User(String prefix, String path) {
            this.prefix = prefix;
            this.path = path;
        }
    }

    //签到
    public static enum SignInApi implements DdOapiApiUrl {
        //获取部门用户签到记录
        SI_CHECKIN_RECORD_URL("https://oapi.dingtalk.com", "/checkin/record"),
        //获取用户签到记录
        SI_USER_CHECKIN_URL("https://oapi.dingtalk.com", "/topapi/checkin/record/get");

        private String prefix;
        private String path;

        public String getUrl(DdOapiConfigStorage config) {
            return DdOapiHostConfig.buildUrl(config.getHostConfig(), this.prefix, this.path);
        }

        private SignInApi(String prefix, String path) {
            this.prefix = prefix;
            this.path = path;
        }
    }

    //文件存储
    public static enum FileStorage implements DdOapiApiUrl {
        //上传媒体文件
        FS_MEDIA_UPLOAD_URL("https://oapi.dingtalk.com", "/media/upload"),
        //发送钉盘文件给指定用户
        FS_CS_SINGLE_CHAT_URL("https://oapi.dingtalk.com", "/cspace/add_to_single_chat"),
        //新增文件到用户自定义空间
        FS_CS_ADD_URL("https://oapi.dingtalk.com", "/cspace/add"),
        //获取企业下的自定义空间
        FS_CS_CUSTOM_SPACE_URL("https://oapi.dingtalk.com", "/cspace/get_custom_space"),
        //获取应用自定义空间使用详情
        FS_CS_USER_INFO_URL("https://oapi.dingtalk.com", "/cspace/used_info"),
        //授权用户访问企业自定义空间
        FS_CS_GRANT_CUSTOM_URL("https://oapi.dingtalk.com", "/cspace/grant_custom_space"),
        //单步上传文件
        CS_FILE_UPLOAD_SINGLE_URL("https://oapi.dingtalk.com", "/file/upload/single"),
        //开启分块上传事务
        CS_FILE_UPLOAD_TRANSACTION_URL("https://oapi.dingtalk.com", "/file/upload/transaction"),
        //上传文件块
        CS_FILE_UPLOAD_CHUNK_URL("https://oapi.dingtalk.com", "/file/upload/chunk");

        private String prefix;
        private String path;

        public String getUrl(DdOapiConfigStorage config) {
            return DdOapiHostConfig.buildUrl(config.getHostConfig(), this.prefix, this.path);
        }

        private FileStorage(String prefix, String path) {
            this.prefix = prefix;
            this.path = path;
        }
    }

    //日志
    public static enum Report implements DdOapiApiUrl {
        //获取企业用户日志数据
        REPORT_LIST_URL("https://oapi.dingtalk.com", "/topapi/report/list"),
        //获取用户可见的日志模板
        REPORT_TEMPLATE_LISTBYUSERID_URL("https://oapi.dingtalk.com", "/topapi/report/template/listbyuserid");

        private String prefix;
        private String path;

        public String getUrl(DdOapiConfigStorage config) {
            return DdOapiHostConfig.buildUrl(config.getHostConfig(), this.prefix, this.path);
        }

        private Report(String prefix, String path) {
            this.prefix = prefix;
            this.path = path;
        }
    }

    //智能人事
    public static enum EmpHrm implements DdOapiApiUrl {
        //获取员工花名册字段信息
        HRM_EMP_LIST_URL("https://oapi.dingtalk.com", "/topapi/smartwork/hrm/employee/list"),
        //更新员工花名册
        HRM_EMP_UPDATE_URL("https://oapi.dingtalk.com", "/topapi/smartwork/hrm/employee/update"),
        //查询花名册元数据
        HRM_EMP_FIELD_GROUPLIST_URL("https://oapi.dingtalk.com", "/topapi/smartwork/hrm/employee/field/grouplist"),
        //查询企业待入职员工列表
        HRM_EMP_QUERYPREENTRY_URL("https://oapi.dingtalk.com", "/topapi/smartwork/hrm/employee/querypreentry"),
        //查询企业在职员工列表
        HRM_EMP_QUERYONJOB_URL("https://oapi.dingtalk.com", "/topapi/smartwork/hrm/employee/queryonjob"),
        //添加企业待入职员工
        HRM_EMP_ADDPREENTRY_URL("https://oapi.dingtalk.com", "/topapi/smartwork/hrm/employee/addpreentry");


        private String prefix;
        private String path;

        public String getUrl(DdOapiConfigStorage config) {
            return DdOapiHostConfig.buildUrl(config.getHostConfig(), this.prefix, this.path);
        }

        private EmpHrm(String prefix, String path) {
            this.prefix = prefix;
            this.path = path;
        }
    }

    //消息通知
    public static enum MsgNotify implements DdOapiApiUrl {
        //发送工作通知消息
        MSG_ASYNCSEND_V2_URL("https://oapi.dingtalk.com", "/topapi/message/corpconversation/asyncsend_v2"),
        //查询工作通知消息的发送进度
        MSG_GET_SENDPROGRESS_URL("https://oapi.dingtalk.com", "/topapi/message/corpconversation/getsendprogress"),
        //查询工作通知消息的发送结果
        MSG_GET_SENDRESULT_URL("https://oapi.dingtalk.com", "/topapi/message/corpconversation/getsendresult"),
        //工作通知消息撤回
        MSG_GET_RECALL_URL("https://oapi.dingtalk.com", "/topapi/message/corpconversation/recall"),

        //发送群消息
        CHAT_SEND_URL("https://oapi.dingtalk.com", "/chat/send"),
        //查询群消息已读人员列表
        CHAT_GET_READLIST_URL("https://oapi.dingtalk.com", "/chat/getReadList"),
        //创建会话
        CHAT_CREATE_URL("https://oapi.dingtalk.com", "/chat/create"),
        //修改会话
        CHAT_UPDATE_URL("https://oapi.dingtalk.com", "/chat/update"),
        //获取会话
        CHAT_GET_URL("https://oapi.dingtalk.com", "/chat/get"),

        //发送普通消息
        CHAT_SEND_TO_CONVERSATION_URL("https://oapi.dingtalk.com", "/message/send_to_conversation");

        private String prefix;
        private String path;

        public String getUrl(DdOapiConfigStorage config) {
            return DdOapiHostConfig.buildUrl(config.getHostConfig(), this.prefix, this.path);
        }

        private MsgNotify(String prefix, String path) {
            this.prefix = prefix;
            this.path = path;
        }
    }

    //应用管理
    public static enum AppManagerApi implements DdOapiApiUrl {
        //获取应用列表
        AM_MICROAPP_LIST_URL("https://oapi.dingtalk.com", "/microapp/list"),
        //获取员工可见的应用列表
        AM_LIST_BY_USERID_URL("https://oapi.dingtalk.com", "/microapp/list_by_userid"),
        //获取应用的可见范围
        AM_VISIBLE_SCOPES_URL("https://oapi.dingtalk.com", "/microapp/visible_scopes"),
        //设置应用的可见范围
        AM_SET_VISIBLE_SCOPES_URL("https://oapi.dingtalk.com", "/microapp/set_visible_scopes");

        private String prefix;
        private String path;

        public String getUrl(DdOapiConfigStorage config) {
            return DdOapiHostConfig.buildUrl(config.getHostConfig(), this.prefix, this.path);
        }

        private AppManagerApi(String prefix, String path) {
            this.prefix = prefix;
            this.path = path;
        }
    }
}
