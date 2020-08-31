package my.caijar.dingding.oapi.enums;

/**
 * @title ct-dingfly-parent
 * @Description CT的fly系列项目
 * @Author CT
 * @Date 2020/4/8 8:56
 * @Version 1.0
 */
public enum TicketType {
    JSAPI("jsapi"),
    SDK("sdk");

    private String code;

    private TicketType(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}

