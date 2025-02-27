package utils.util;


import utils.propertyManager.PropertyManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class Configs {
    private Configs configs;
    private static final Map<String, String> configDevMap = new HashMap<>();
    private static final Map<String, String> configPreprodMap = new HashMap<>();
    private static final Map<String, String> configProdMap = new HashMap<>();
    private static Map<String, String> configValid = new HashMap<>();
    private static String url;
    private static String username;
    private static String password;


    static {
        configDevMap.put("ENVIRONMENT_DOMAIN", "https://app.forceget.com/");
        configDevMap.put("ENVIRONMENT_EMAIL", "fcetinerr@gmail.com");
        configDevMap.put("ENVIRONMENT_PASSWORD", "Fer94hat++");
    }

    static {
        configPreprodMap.put("ENVIRONMENT_DOMAIN", "https://app.forceget.com/");
        configPreprodMap.put("ENVIRONMENT_EMAIL", "fcetinerr@gmail.com");
        configPreprodMap.put("ENVIRONMENT_PASSWORD", "Fer94hat++");
    }
    static {
        configProdMap.put("ENVIRONMENT_DOMAIN", "https://app.forceget.com/");
        configProdMap.put("ENVIRONMENT_EMAIL", "fcetinerr@gmail.com");
        configProdMap.put("ENVIRONMENT_PASSWORD", "Test123++");
    }

    public static Map<String, String> getValidConfig(String environment) {
        switch (environment){
            case "dev":
                configValid = configDevMap;
                break;

            case "preprod":
                configValid = configPreprodMap;
                break;

            case "prod":
                configValid = configProdMap;
                break;

            default:
                break;
        }

        return configValid;
    }
    public static Map<String, String> getAllConfigs()  {
        return Configs.getValidConfig(PropertyManager.getInstance().environmentVariable());
    }
}
