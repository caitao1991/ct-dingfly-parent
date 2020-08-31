package my.caijar.dingding.oapi.util;

public class DdOapiConfigStorageHolder {
    private static final ThreadLocal<String> DD_OAPI_CONFIG_STORAGE_CHOSE = new ThreadLocal<String>() {
        protected String initialValue() {
            return "default";
        }
    };

    public DdOapiConfigStorageHolder() {
    }

    public static String get() {
        return (String)DD_OAPI_CONFIG_STORAGE_CHOSE.get();
    }

    public static void set(String label) {
        DD_OAPI_CONFIG_STORAGE_CHOSE.set(label);
    }
}