package com.karthik.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import com.karthik.Constants.AppConstants;
import com.karthik.Handlers.RegexHandler;
import com.karthik.Handlers.RestCallHandler;
import com.karthik.Model.PullRequestModel;
import com.karthik.Model.RepositoryModel;

@Service
public class RepositoryService {
	RestCallHandler restCallHandler;

	RegexHandler regexHandler;
 
	CommentService commentService;
	
// Constructor Dependency Injection
	public RepositoryService(RestCallHandler restCallHandler, RegexHandler regexHandler,
			CommentService commentService) {
		this.restCallHandler = restCallHandler;
		this.regexHandler = regexHandler;
		this.commentService = commentService;
	}

	 /**
	  * 
      * Building URL using UriComponentsBuilder and passing the url for rest call.
      */
	public RepositoryModel getPullRequests(RepositoryModel repos) {

      
		Map<String, String> urlParams = new HashMap<String, String>();
		urlParams.put("owner", repos.getOwner());
		urlParams.put("repo", repos.getName());

		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(AppConstants.pullRequestAPIUrl)
									.queryParam("state", "open")
									.queryParam("per_page", "30")
									.queryParam("page", repos.getPageNumber());

		String url = builder.buildAndExpand(urlParams).toUriString();
		
		
		ParameterizedTypeReference responseType = new ParameterizedTypeReference<List<PullRequestModel>>() {};
		
		/**
		 * Performing a GET request to fetch github repository details from api.github.com
		 */
		
		ResponseEntity<List<PullRequestModel>> responseEntity = restCallHandler.performGetRequest(url, responseType);
		HttpHeaders responseHeaders = responseEntity.getHeaders();
		List<PullRequestModel> pullRequestResponse = responseEntity.getBody();
		
		 fetchCommentsCount(pullRequestResponse);
		 repos.setPullRequests(pullRequestResponse);	
	

		int count = 0;

		int pageNumber = 1;

		String linkHeader = responseHeaders.getFirst("Link");

		if (linkHeader != null) {
			// matches last page

			String lastPageLink = regexHandler.extractString("<([^>]*)>; rel=\"last\"", linkHeader);
		
			if (!lastPageLink.equalsIgnoreCase("")) {

				url = lastPageLink.replace("<", "").replace(">; rel=\"last\"", "").trim();
		
				pageNumber = Integer.parseInt(url.replaceAll("(.*?)&page=", ""));

				responseEntity  = restCallHandler.performGetRequest(url, responseType);

				pullRequestResponse = responseEntity.getBody();
				
				count = (pageNumber - 1) * 30;

			} else {
				
				count = (repos.getPageNumber() - 1) * 30;
			}
		}
		count += pullRequestResponse.size();
		repos.setPullRequestCount(count);
		repos.setTotalPages(pageNumber);
		return repos;
	}
	
/**
 * Iterating through list of pull requests for getting the CommentCount.
 * @param pullRequestDomainList Object of model class PullRequestDomain
 * @return pullRequestDomainList
 */

	public List<PullRequestModel> fetchCommentsCount(List<PullRequestModel> pullRequestModelList){
		
		for (PullRequestModel pulldata : pullRequestModelList) {
			commentService.getCommentsCount(pulldata);		
		}
		
		return pullRequestModelList;
	}

}
