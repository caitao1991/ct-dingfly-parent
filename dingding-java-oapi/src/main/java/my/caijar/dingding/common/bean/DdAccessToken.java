package my.caijar.dingding.common.bean;


import lombok.*;

import java.io.Serializable;

/**
 * @title ct-dingfly-parent
 * @Description CT的fly系列项目
 * @Author CT
 * @Date 2020/4/7 10:16
 * @Version 1.0
 */

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DdAccessToken implements Serializable {
    private String accessToken;
    private int expiresIn = -1;

    public static DdAccessToken build(String accessToken) {
        return new DdAccessToken(accessToken, 7200);
    }
}
