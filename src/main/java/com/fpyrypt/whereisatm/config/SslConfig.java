package com.fpyrypt.whereisatm.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.HttpClient;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

@Slf4j
@Configuration
public class SslConfig {
    // Keystores
    @Value("${application.pathToKeystore}")
    private Resource pathToKeystore;
    @Value("${application.keystore-password}")
    private String keystorePassword;
    // Connection
    @Value("${application.enable-https}")
    private boolean enableHttps;
    // Timeouts
    @Value("${application.connection-timeout}")
    private Integer connectionTimeout;
    @Value("${application.read-timeout}")
    private Integer readTimeout;
    @Value("${application.socket-timeout}")
    private Integer socketTimeout;

    @Bean
    public RestTemplate sslRestTemplate() {
        RestTemplate restTemplate = new RestTemplate(getRequestFactory());
        // remove remove charsets from accept-charset header
        for (HttpMessageConverter converter : restTemplate.getMessageConverters()) {
            if (converter instanceof StringHttpMessageConverter) {
                ((StringHttpMessageConverter) converter).setWriteAcceptCharset(false);
            }
        }
        return restTemplate;
    }

    private HttpComponentsClientHttpRequestFactory getRequestFactory() {
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(getHttpClient());
        requestFactory.setConnectTimeout(connectionTimeout);
        requestFactory.setReadTimeout(readTimeout);
        return requestFactory;
    }

    private HttpClient getHttpClient() {
        HttpClientBuilder clientBuilder = HttpClients.custom()
                .setDefaultSocketConfig(getSocketConfig());
        if (enableHttps) {
            clientBuilder.setSSLSocketFactory(getSslConnectionSocketFactory());
        }
        return clientBuilder.build();
    }

    private SocketConfig getSocketConfig() {
        return SocketConfig.custom()
                .setSoTimeout(socketTimeout)
                .build();
    }

    private SSLConnectionSocketFactory getSslConnectionSocketFactory() {
        return new SSLConnectionSocketFactory(getSslContext(), NoopHostnameVerifier.INSTANCE);
    }

    private SSLContext getSslContext() {
        try {
            return new SSLContextBuilder()
                    .setProtocol("TLSv1.2")
                    .setKeyManagerFactoryAlgorithm(KeyManagerFactory.getDefaultAlgorithm())
                    .loadKeyMaterial(getClientStore(), keystorePassword.toCharArray())
                    .build();
        } catch (Exception e) {
            log.error("Ssl context building failed!", e);
            throw new RuntimeException(e);
        }
    }

    private KeyStore getClientStore() throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException {
        KeyStore clientStore = KeyStore.getInstance("PKCS12");
        clientStore.load(pathToKeystore.getInputStream(), keystorePassword.toCharArray());
        return clientStore;
    }
}
