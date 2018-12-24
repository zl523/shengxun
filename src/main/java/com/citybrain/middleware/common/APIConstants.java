package com.citybrain.middleware.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Constants for APIs
 *
 * @author zl
 */
@Component
public class APIConstants {
    public static String LOGIN_API;
    public static String DATA_API;

    @Value("${citybrain.api.login}")
    public void setLoginApi(String LOGIN_API) {
        APIConstants.LOGIN_API = LOGIN_API;
    }

    @Value("${citybrain.api.data}")
    public void setDataApi(String DATA_API) {
        APIConstants.DATA_API = DATA_API;
    }
}
