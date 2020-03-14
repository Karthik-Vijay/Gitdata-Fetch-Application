package com.karthik.Mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.karthik.DTO.Repository;
import com.karthik.Model.RepositoryModel;

@Component
public class ModelToDtoMapper {

	@Autowired
	private ModelMapper modelMapper;
    /**
     * 
     * Mapping model objects of type RepositoryModel to DTO Repository
     * @return repository (view value objects)
     */
	public Repository convertToDto(RepositoryModel result)
	{
		Repository repository = modelMapper.map(result, Repository.class);

		return repository;
	}
	

}