package com.karthik.Config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 
 * Initializing RestTemplate which is used to perform a Get call that takes a url ,
 * request entity, response type and HttpMethod.GET.
 *
 */
@Configuration
public class RestTemplateConfig {

	@Bean
	public RestTemplate configRestTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
}