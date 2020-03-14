package com.karthik.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.karthik.DTO.Repository;
import com.karthik.Mapper.ModelToDtoMapper;
import com.karthik.Model.RepositoryModel;
import com.karthik.Service.RepositoryService;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
public class DashController {

	@Autowired
	RepositoryService repositoryService;

	@Autowired
	ModelToDtoMapper mapper;

	/**
	 * Annotation @GetMapping maps HTTP requests to handler methods of REST controllers.
	 * @ResponseStatus declares the status code of the HTTP response.
	 * @param owner : Github owner name
	 * @param repo  : Github repository name
	 * @param pageNumber : PageNumber (Each page holds 30 Open PR Details. )
	 * @return view value objects.
	 */

	@GetMapping(value = "getPullRequests/{owner}/{name}/{pageNumber}")
	@ResponseStatus(HttpStatus.OK)
	public Repository getPullRequests(@PathVariable("owner") String owner, @PathVariable("name") String repo,
			@PathVariable("pageNumber") int pageNumber) {

		RepositoryModel repos = new RepositoryModel();
		repos.setName(repo);
		repos.setOwner(owner);
		repos.setPageNumber(pageNumber);
		RepositoryModel result = repositoryService.getPullRequests(repos);
        
		return mapper.convertToDto(result);
	}

}
