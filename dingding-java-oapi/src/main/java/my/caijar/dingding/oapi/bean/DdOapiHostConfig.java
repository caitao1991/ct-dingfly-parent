package my.caijar.dingding.oapi.bean;

import lombok.*;

/**
 * @title ct-dingfly-parent
 * @Description CT的fly系列项目
 * @Author CT
 * @Date 2020/4/7 9:26
 * @Version 1.0
 */
@Getter
@Setter
@EqualsAndHashCode()
@AllArgsConstructor
@NoArgsConstructor
public class DdOapiHostConfig {

    public static final String API_DEFAULT_HOST_URL = "https://oapi.dingtalk.com";
    private String apiHost;


    public static String buildUrl(DdOapiHostConfig hostConfig, String prefix, String path) {
        if (hostConfig == null) {
            return prefix + path;
        }
        return hostConfig.getApiHost() + path;
    }

    public String getApiHost() {
        return apiHost;
    }

    public void setApiHost(String apiHost) {
        this.apiHost = apiHost;
    }

    public static DdOapiHostConfig.DdOapiHostConfigBuilder builder() {
        return new DdOapiHostConfig.DdOapiHostConfigBuilder();
    }

    /**
      * 静态内部类的妙用
     *  建造者模式 .apiHost("").build();
      */
    public static class DdOapiHostConfigBuilder {
        private String apiHost;

        DdOapiHostConfigBuilder() {
        }

        public DdOapiHostConfig.DdOapiHostConfigBuilder apiHost(String apiHost) {
            this.apiHost = apiHost;
            return this;
        }

        public DdOapiHostConfig build() {
            return new DdOapiHostConfig(this.apiHost);
        }

        public String toString() {
            return "DdOapiHostConfig.DdOapiHostConfigBuilder(apiHost=" + this.apiHost + ")";
        }
    }

    public static void main(String[] args) {
//        String url = DdOapiHostConfig.buildUrl(null, "", "");
//        new DdOapiHostConfigBuilder().apiHost("").build()
//        DdOapiHostConfig.builder().build();
    }
}
