package com.karthik.DTO;

import lombok.Data;

/**
 * number       : PullRequest number
 * title        : Title of the particular open pull request
 * commentCount : Integer value representing no of comments in a particular PR.
 *
 */
@Data
public class PullRequest {
	
	private int number;
	private String title;
	private int commentCount;
	
}
