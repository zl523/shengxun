package com.citybrain.middleware.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Constants for ssl
 *
 * @author zl
 */
@Component
public class SSLConstants {
    public static String TRUSTSTORE_TYPE;
    public static String TRUSTSTORE_PATH;
    public static String TRUSTSTORE_PASSWORD;

    public static String KEYSTORE_TYPE;
    public static String KEYSTORE_PATH;
    public static String KEYSTORE_PASSWORD;

    @Value("${citybrain.ssl.truststore.type}")
    public void setTruststoreType(String TRUSTSTORE_TYPE) {
        SSLConstants.TRUSTSTORE_TYPE = TRUSTSTORE_TYPE;
    }

    @Value("${citybrain.ssl.truststore.path}")
    public void setTruststorePath(String TRUSTSTORE_PATH) {
        SSLConstants.TRUSTSTORE_PATH = TRUSTSTORE_PATH;
    }

    @Value("${citybrain.ssl.truststore.password}")
    public void setTruststorePassword(String TRUSTSTORE_PASSWORD) {
        SSLConstants.TRUSTSTORE_PASSWORD = TRUSTSTORE_PASSWORD;
    }

    @Value("${citybrain.ssl.keystore.type}")
    public void setKeystoreType(String KEYSTORE_TYPE) {
        SSLConstants.KEYSTORE_TYPE = KEYSTORE_TYPE;
    }

    @Value("${citybrain.ssl.keystore.path}")
    public void setKeystorePath(String KEYSTORE_PATH) {
        SSLConstants.KEYSTORE_PATH = KEYSTORE_PATH;
    }

    @Value("${citybrain.ssl.keystore.password}")
    public void setKeystorePassword(String KEYSTORE_PASSWORD) {
        SSLConstants.KEYSTORE_PASSWORD = KEYSTORE_PASSWORD;
    }
}
