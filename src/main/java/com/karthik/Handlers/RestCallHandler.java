package com.karthik.Handlers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestCallHandler {

	@Autowired
	RestTemplate restTemplate;


	@Value("${githubToken}")
	private String githubToken;
	
	
	/*
	 * method to perform GET requests
	 * url - requested url in our case : PullRequestAPIUrl and comments_url.
	 * responseType - return type of response
	 * Setting github token in authorization header and performing get operation
	 * ResponseEntity is meant to represent the entire HTTP response.
	 * We can control anything that goes into it: status code, headers, and body.
	 */
	public ResponseEntity performGetRequest(String url, ParameterizedTypeReference responseType){
		
		HttpHeaders reqheaders = new HttpHeaders();
		reqheaders.add("Authorization", "Bearer " + githubToken);
		HttpEntity requestEntity = new HttpEntity<>("parameters", reqheaders);
		return restTemplate.exchange(url, HttpMethod.GET, requestEntity, responseType);
		
	}
	
}
