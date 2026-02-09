package com.codingsrv.ApiCallWithRestClient.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.RestClient;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Configuration
public class RestClientConfig {

    @Value("${employeeService.base.url}")
    private  String BASE_URL;  // in BASE_URL variable, inject third-party api base url(which is defined in application.properties file)a

    @Bean
    @Qualifier("employeeRestClient")
    RestClient getEmployeeServiceRestClient(){
        return RestClient.builder()
                .baseUrl(BASE_URL)  // we could have pass base url here, but hardcoding is not recommended
                .defaultHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .defaultStatusHandler(HttpStatusCode::is5xxServerError, (req, res)->{
                    throw new RuntimeException("server error occurred");
                })
                .build();
    }


}
