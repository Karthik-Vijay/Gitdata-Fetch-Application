package com.karthik.Handlers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.stereotype.Component;
/**
 * 
 * RegexHandler's extractString method takes a regex and responseHeaders as input and
 * returns the string with trailing and leading white spaces removed.
 */
@Component
public class RegexHandler {

	public String extractString(String regex,String input){
		
		Pattern pattern = Pattern.compile(regex);
		
		Matcher matcher = pattern.matcher(input);
		
		String output = "";
		
		if(matcher.find()){
			
			output = matcher.group().trim();
			
		} 
		
		return output;
	}
	
}
