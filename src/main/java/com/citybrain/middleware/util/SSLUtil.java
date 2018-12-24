package com.citybrain.middleware.util;

import com.citybrain.middleware.common.SystemContext;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;

/**
 * @author zl
 */
@Component
public class SSLUtil {
    static public HttpClient getRequestFactory(String keystoreType, String keystorePath, String keystorePassword,
                                               String truststoreType, String truststorePath, String truststorePassword)
            throws KeyStoreException, IOException, CertificateException,
            NoSuchAlgorithmException, UnrecoverableKeyException, KeyManagementException {
        // Load KeyStore
        KeyStore keyStore = KeyStore.getInstance(keystoreType);
        keyStore.load(new FileInputStream(new File(keystorePath)),
                keystorePassword.toCharArray());

        // Load TrustStore
        KeyStore trustStore = KeyStore.getInstance(truststoreType);
        trustStore.load(new FileInputStream(new File(truststorePath)),
                truststorePassword.toCharArray());

        // Set double https connection factory
        SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(
                new SSLContextBuilder()
                        .loadTrustMaterial(new File(keystorePath),
                                truststorePassword.toCharArray())
                        .loadKeyMaterial(keyStore, keystorePassword.toCharArray()).build(),
                NoopHostnameVerifier.INSTANCE);

        HttpClient httpClient = HttpClients.custom().setSSLSocketFactory(socketFactory).build();
        return httpClient;
    }

    static public String getHttpsApiUrl(String ip, String port, String api) {
        String url = SystemContext.HTTPS_PREFIX + ip + SystemContext.SEMICOLON + port
                + SystemContext.URL_DELIM + api;
        return url;
    }
}
