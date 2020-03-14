package com.karthik.Config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * Initiliazing ModelMapper
 * ModelMapper is used to map model objects to DTO(view value objects)
 *
 */
@Configuration
public class ModelMapConfig {
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}

}
