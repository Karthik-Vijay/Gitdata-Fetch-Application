package com.karthik.Model;

import java.util.List;

import lombok.Data;

@Data
public class RepositoryModel {
	
	private String owner;
	private String name;
	private int pageNumber;
	private int totalPages;
	private int pullRequestCount;
	private List<PullRequestModel> pullRequests;
	private int commentCount;
}
