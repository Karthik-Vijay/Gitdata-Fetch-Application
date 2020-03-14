	OpenPRandCommentsApplication
	
	How to run the application:
	
	> Download the project as zip file.
	> Import as existing Maven application to the IDE and the Front end code in visual studio code.
	> Run the file OpenPRandCommentsApplication as a spring boot application
	> In a browser or postman, hit the URL http://localhost:8080/getPullRequests/{githubOwnername}/{githubRepositoryname}/1
	Example : http://localhost:8080/getPullRequests/angular/angular/1
	
	Outputs Expected:
	> A JSON response is obtained and example for the JSON schema is shown in documentation section.
	> The number of open pull request count as well as the details of each pull request 
	(i.e number associated with particular PullRequest, title and commentCount)  will be 
	displayed in the browser with number associated with pull request,title and comment count 
	in each PR.
	> Each time 30 Open PR details are shown and if the particular repository have more than 30 open PR's then pagination is enabled to view next 30 PR's in F.E or else navigate to 
	 Example : http://localhost:8080/getPullRequests/angular/angular/2
	> If the entered inputs are not valid, it will show a message "Repository for this owner is not found"
	
	
	Assumptions:
	
	> The github owner name and repository name entering should be a valid one.
	> Import the angular project and in terminal, type npm install which will install all modules listed as dependencies in package.json and type ng serve which will builds and serves the application.
	
	Documentation :
	
	JSON SCHEMA :
	
	The output will be displayed as a json and it can be similar to the following example.
	
	{
    "owner": "example",
    "name": "example",
    "pageNumber": 1,
    "totalPages": 1,
    "pullRequestCount": 2,
    "pullRequests": [
        {
            "number": 33784,
            "title": "fix(ivy): support for #id bootstrap selectors",
            "commentCount": 0
        },
        {
            "number": 33782,
            "title": "fix(language-service): Function alias should be callable",
            "commentCount": 1
        }
       ]
       }
