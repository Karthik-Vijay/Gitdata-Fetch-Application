package com.karthik.DTO;

import java.util.List;

import lombok.Data;

/**
 * DTO          : Data transfer objects here referred as the view value objects,which will be known to the controller.
 * owner        : Owner of the particular repository
 * name         : Name of the particular repository
 * pageNumber   : Current Page number of response from rest api.
 * totalPages   : TotalPages of response from rest api
 * pullRequests : List of pullrequests of type PullRequest(number,title and commentcount)
 *
 */
@Data
public class Repository {

	private String owner;
	private String name;
	private int pageNumber;
	private int totalPages;
	private int pullRequestCount;
	private List<PullRequest> pullRequests;
}
