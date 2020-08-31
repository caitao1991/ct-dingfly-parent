package my.caijar.dingding.common.bean;

import lombok.*;

/**
 * @title ct-dingfly-parent
 * @Description
 * @Author 12870
 * @Date 2020/4/22 22:47
 * @Version 1.0
 */
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class JsapiTicket {
    private String ticket;
    private Long expiresIn = -1L;

    public static JsapiTicket build(String ticket, Long expiresIn) {
        return new JsapiTicket(ticket, expiresIn);
    }
}
