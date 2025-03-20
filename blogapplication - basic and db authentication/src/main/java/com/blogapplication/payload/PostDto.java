package com.blogapplication.payload;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.blogapplication.entities.Comment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostDto {

	private Integer postId;
	private String title;
	private String content;
	
	//imageName take directly into the service
	//date is automatically will come into db
	//category and user will take in url
	//to return, we need all fields to return
	
	private String imageName;
	
	private Date addedDate;
	
	private CategoryDto category;
	
	private UserDto user;
	
	private Set<CommentDto> comments = new HashSet<>();
}
