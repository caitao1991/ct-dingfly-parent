package my.caijar.dingding.oapi.enums;

/**
 * @title ct-dingfly-parent
 * @Description
 * @Author CT
 * @Date 2020/4/20 23:38
 * @Version 1.0
 */
public enum  MeetingPreType {
    INIT,
    SUCCESS,
    APPROVALING,
    CANCEL,
    REFUSE;

    private String pre_status_type;

    public String getPre_status_type() {
        return pre_status_type;
    }

    public void setPre_status_type(String pre_status_type) {
        this.pre_status_type = pre_status_type;
    }
}
