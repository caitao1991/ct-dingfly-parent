package my.caijar.dingding.oapi.constant;

import lombok.NoArgsConstructor;

/**
 * @title ct-dingfly-parent
 * @Description 钉钉事件常量
 * @Author CT
 * @Date 2020/4/13 23:18
 * @Version 1.0
 */
@NoArgsConstructor
public class DdOapiEventConstants {

	/**
	 * 创建应用，验证回调URL创建有效事件（第一次保存回调URL之前）
	 */
	private static final String EVENT_CHECK_CREATE_SUITE_URL = "check_create_suite_url";
	/**
	 * 创建应用，验证回调URL变更有效事件（第一次保存回调URL之后）
	 */
	private static final String EVENT_CHECK_UPADTE_SUITE_URL = "check_update_suite_url";

	/**
	 * suite_ticket推送事件
	 */
	private static final String EVENT_SUITE_TICKET = "suite_ticket";
	/**
	 * 企业授权开通应用事件
	 */
	private static final String EVENT_TMP_AUTH_CODE = "tmp_auth_code";
	/**
	 * 相应钉钉回调时的值
	 */
    public static final String CALLBACK_RESPONSE_SUCCESS  = "success";
    /**
     * 通讯录
     *
     */
    public static class AddressBook {
        //通讯录用户增加
        public static final String USER_ADD_ORG = "user_add_org";
        //通讯录用户更改
        public static final String USER_MODIFY_ORG = "user_modify_org";
        //通讯录用户离职
        public static final String USER_LEAVE_ORG = "user_leave_org";
        //加入企业后用户激活
        public static final String USER_ACTIVE_ORG = "user_active_org";
        //通讯录用户被设为管理员
        public static final String ORG_ADMIN_ADD = "org_admin_add";
        //通讯录用户被取消设置管理员
        public static final String ORG_ADMIN_REMOVE = "org_admin_remove";
        //通讯录企业部门创建
        public static final String ORG_DEPT_CREATE = "org_dept_create";
        //通讯录企业部门修改
        public static final String ORG_DEPT_MODIFY = "org_dept_modify";
        //通讯录企业部门删除
        public static final String ORG_DEPT_REMOVE = "org_dept_remove";
        //企业信息变更; 企业被解散
        public static final String ORG_REMOVE = "org_remove";
        //企业信息发生变更
        public static final String ORG_CHANGE = "org_change";
        //员工角色信息发生变更
        public static final String LABEL_USER_CHANGE = "label_user_change";
        //增加角色或者角色组
        public static final String LABEL_CONF_ADD = "label_conf_add";
        //删除角色或者角色组
        public static final String LABEL_CONF_DEL = "label_conf_del";
        //修改角色或者角色组
        public static final String LABEL_CONF_MODIFY = "label_conf_modify";

        public AddressBook(){

        }
    }

    /**
     * 审批事件回调
     * 注册回调事件为：“bpms_task_change”，“bpms_instance_change”
     */
    public static final String CHECK_URL = "check_url";
    public static class Approval {
        /**
         * 审批任务开始，结束，转交
         */
        public static final String BPMS_TASK_CHANGE  = "bpms_task_change";

        /**
         * 审批实例开始，结束
         */
        public static final String BPMS_INSTANCE_CHANGE  = "bpms_instance_change";

        /**
         * 审批实例/任务结束，审批等type类型
         */
        public static final String FINISH  = "finish";
        /**
         * 审批实例中止，审批等type类型
         */
        public static final String TERMINATE  = "terminate";
        /**
         * 审批实例/任务开始，审批等type类型
         */
        public static final String START  = "start";

        /**
         * type = "finish", "result": "redirect"审批任务转交，审批等type类型
         */
        public static final String REDIRECT  = "redirect";

        public Approval() {

        }

    }

    /**
     * 群会话事件回调
     *
     */
    public static class GroupConversation {
        //群会话添加人员
        public static final String CHAT_ADD_MEMBER ="chat_add_member";
        //群会话删除人员
        public static final String CHAT_REMOVE_MEMBER ="chat_remove_member";
        //群会话用户主动退群
        public static final String CHAT_QUIT ="chat_quit";
        //群会话更换群主
        public static final String CHAT_UPDATE_OWNER ="chat_update_owner";
        //群会话更换群名称
        public static final String CHAT_UPDATE_TITLE ="chat_update_title";
        //群会话解散群
        public static final String CHAT_DISBAND ="chat_disband";

        public GroupConversation() {

        }
    }

    /**
     * 签到事件回调
     */
    public static final String CHECK_IN = "check_in";

    /**
     * 考勤事件回调
     *
     */
    public static class Attendance {
        //员工打卡事件
        public static final String ATTENDANCE_CHECK_RECORD ="attendance_check_record";
        //员工排班变更事件
        public static final String ATTENDANCE_SCHEDULE_CHANGE ="attendance_schedule_change";
        //员工加班事件
        public static final String ATTENDANCE_OVERTIME_DURATION ="attendance_overtime_duration";

        public Attendance() {

        }
    }

    /**
     * 会议室事件回调
     * 注册回调事件：meetingroom_book, meetingroom_room_info
     */
    public static class MeetingRoom {
        //会议室预定等事件，预定成功、取消等
        public static final String MEETINGROOM_BOOK ="meetingroom_book";

        //预定成功 eventSubType值为bookSuccess
        public static final String BOOK_SUCCESS = "bookSuccess";

        //预定取消 eventSubType值为bookCancel
        public static final String BOOK_CANCEL = "bookCancel";

        //审批预定 eventSubType值为appvaral
        public static final String BOOK_APPVARAL = "appvaral";

        //审批拒绝 eventSubType值为bookRefuse
        public static final String BOOK_REFUSE = "bookRefuse";

        //会议预定更新 eventSubType值为bookUpdate
        public static final String BOOK_UPDATE = "bookUpdate";

        //会议室创建、更新、删除等
        public static final String MEETINGROOM_ROOM_INFO ="meetingroom_room_info";

        //会议室创建。eventSubType值为create
        public static final String MEETING_ROOM_CREATE = "create";
        //会议室创建。eventSubType值为update
        public static final String MEETING_ROOM_UPDATE = "update";
        //会议室创建。eventSubType值为del
        public static final String MEETING_ROOM_DEL = "del";

        public MeetingRoom() {

        }

    }


}
