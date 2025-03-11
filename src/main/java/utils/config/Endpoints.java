package utils.config;

public class Endpoints {
    public static String getBaseUrl() {
        return PropertyManager.getInstance().getProperty("baseUrl");
    }

    public static String getLoginUrl() {
        return getBaseUrl() + "account/login";
    }

    public static String getSignupUrl() {
        return getBaseUrl() + "account/signup";
    }
}
