package com.citybrain.middleware.controller;


import com.citybrain.middleware.service.MiddlewareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

/**
 * Controller for middleware
 *
 * @author zl
 */
@RestController
public class MiddlewareController {

    @Autowired
    private MiddlewareService middlewareService;

    @RequestMapping(value = "/api/v1/token", method = RequestMethod.GET)
    public String getToken() {
        return middlewareService.getToken();
    }

    @RequestMapping(value = "/api/v1/https_token", method = RequestMethod.GET)
    public String getHttpsToken() throws CertificateException, UnrecoverableKeyException, NoSuchAlgorithmException,
            KeyStoreException, KeyManagementException, IOException {
        return middlewareService.getHttpsToken();
    }

    @RequestMapping(value = "/api/v1/https_data", method = RequestMethod.GET)
    public String getHttpsData() throws CertificateException, UnrecoverableKeyException, NoSuchAlgorithmException,
            KeyStoreException, KeyManagementException, IOException {
        return middlewareService.getHttpsData();
    }


}