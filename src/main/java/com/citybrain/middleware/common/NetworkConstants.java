package com.citybrain.middleware.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Constants for network
 *
 * @author zl
 */
@Component
public class NetworkConstants {
    public static String IP;
    public static String PORT;
    public static String API;

    @Value("${citybrain.network.ip}")
    public void setIP(String IP) {
        NetworkConstants.IP = IP;
    }

    @Value("${citybrain.network.port}")
    public void setPORT(String PORT) {
        NetworkConstants.PORT = PORT;
    }

    @Value("${citybrain.network.port}")
    public void setAPI(String API) {
        NetworkConstants.API = API;
    }
}
