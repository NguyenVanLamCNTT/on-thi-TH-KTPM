package com.onth.driverservice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestServiceUtil {
    
    private HttpHeaders setHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		headers.setContentType(MediaType.APPLICATION_JSON);

        return headers;
    }

    public CustomerDto getCustomer(int customerId) {
        
        try {
            RestTemplate restTemplate = new RestTemplate();
            List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();        
            MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();  
            converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));        
            messageConverters.add(converter);  
            restTemplate.setMessageConverters(messageConverters); 
            
            HttpEntity<Object> requestBody = new HttpEntity<>(setHeaders());

            ResponseEntity<CustomerDto> res = restTemplate.exchange("http://localhost:8090/api/customers/" + customerId, HttpMethod.GET, requestBody, CustomerDto.class);

            return res.getBody();
        }catch(Exception error) {
            error.printStackTrace();
            return null;
        }
    }


}
