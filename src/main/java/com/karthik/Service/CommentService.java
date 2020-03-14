package com.karthik.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.karthik.Handlers.RegexHandler;
import com.karthik.Handlers.RestCallHandler;
import com.karthik.Model.CommentModel;
import com.karthik.Model.PullRequestModel;

@Service
public class CommentService {
	
	@Autowired
	RestCallHandler restCallHandler;

	@Autowired
	RegexHandler regexHandler;
	
	/**
	 * 
	 * @param pr Model object of PullRequestDomain
	 * Performs a restTemplate call to fetch the comments using comments_url.
	 * @return pr
	 */

	public PullRequestModel getCommentsCount(PullRequestModel pr) {

		ParameterizedTypeReference responseType = new ParameterizedTypeReference<List<CommentModel>>() {};
		
	
		ResponseEntity<List<CommentModel>> responseEntity = restCallHandler.performGetRequest(pr.getComments_url(),responseType);

		HttpHeaders headers = responseEntity.getHeaders();

		int count = 0;
		
		int pageNumber = 1;

		String linkHeader = headers.getFirst("Link");

		if (linkHeader != null) {

			String lastPageLink = regexHandler.extractString("<([^>]*)>; rel=\"last\"", linkHeader);

			if (!lastPageLink.equalsIgnoreCase("")) {

				String url = lastPageLink.replace("<", "").replace(">; rel=\"last\"", "").trim();

				pageNumber = Integer.parseInt(url.replaceAll("(.*?)&page=", ""));

				count = (pageNumber - 1) * 30;

				responseEntity = restCallHandler.performGetRequest(pr.getComments_url(),responseType);
			}
		}

		/**
		 * Retrieving the last page comments and adding it to previous comments count.
		 */
		List<CommentModel> comments = responseEntity.getBody();

		count += comments.size();
        pr.setCommentCount(count);
		return pr;

	}

}
