package com.karthik.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;


@JsonIgnoreProperties(ignoreUnknown=true)
@Data
public class PullRequestModel {
	private String title;
	private int number;
	private String comments_url;
	private int commentCount;
}
