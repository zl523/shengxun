package com.citybrain.middleware.service;

import com.citybrain.middleware.common.APIConstants;
import com.citybrain.middleware.common.NetworkConstants;
import com.citybrain.middleware.common.SSLConstants;
import com.citybrain.middleware.util.SSLUtil;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

/**
 * Implementation class for middleware service
 *
 * @author zl
 */
@Service
public class MiddlewareServiceImpl implements MiddlewareService {

    static String JWT_TOKEN = "";

    @Override
    public String getToken() {
        return "test";
    }

    @Override
    public String getHttpsToken() throws CertificateException, UnrecoverableKeyException, NoSuchAlgorithmException,
            KeyStoreException, KeyManagementException, IOException {
        ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(
                SSLUtil.getRequestFactory(
                        SSLConstants.KEYSTORE_TYPE, SSLConstants.KEYSTORE_PATH, SSLConstants.KEYSTORE_PASSWORD,
                        SSLConstants.TRUSTSTORE_TYPE, SSLConstants.TRUSTSTORE_PATH, SSLConstants.TRUSTSTORE_PASSWORD)
        );
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("username", "admin");
        map.add("password", "admin");

        HttpEntity<MultiValueMap<String, String>> request_data = new HttpEntity<>(map,
                headers);

        RestTemplate restTemplate = new RestTemplate(requestFactory);
        String request_url = SSLUtil.getHttpsApiUrl(
                NetworkConstants.IP, NetworkConstants.PORT, APIConstants.LOGIN_API);
        ResponseEntity<String> response = restTemplate.postForEntity(request_url, request_data, String.class);

        JWT_TOKEN = response.getBody();
        return response.getBody();
    }

    @Override
    public String getHttpsData() throws CertificateException, UnrecoverableKeyException, NoSuchAlgorithmException,
            KeyStoreException, KeyManagementException, IOException {
        ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(
                SSLUtil.getRequestFactory(
                        SSLConstants.KEYSTORE_TYPE, SSLConstants.KEYSTORE_PATH, SSLConstants.KEYSTORE_PASSWORD,
                        SSLConstants.TRUSTSTORE_TYPE, SSLConstants.TRUSTSTORE_PATH, SSLConstants.TRUSTSTORE_PASSWORD)
        );
        HttpHeaders headers = new HttpHeaders();
        System.out.println("JWT token:" + JWT_TOKEN);
        headers.setBearerAuth(JWT_TOKEN);

        HttpEntity<String> request_data = new HttpEntity<>(
                headers);

        RestTemplate restTemplate = new RestTemplate(requestFactory);
        String request_url = SSLUtil.getHttpsApiUrl(
                NetworkConstants.IP, NetworkConstants.PORT, APIConstants.DATA_API);
        System.out.println(request_url);
        ResponseEntity<String> response = restTemplate.exchange(
                request_url, HttpMethod.GET, request_data, String.class);

        return response.getBody();
    }
}
