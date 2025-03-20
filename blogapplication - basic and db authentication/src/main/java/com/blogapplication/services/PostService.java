package com.blogapplication.services;

import java.util.List;

import com.blogapplication.entities.Post;
import com.blogapplication.payload.PostDto;
import com.blogapplication.payload.PostResponse;

public interface PostService {

	//create
	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
	
	//update
	PostDto updatePost(PostDto postDto, Integer postId);
	
	//delete
	void deletePost(Integer postId);
	
	//get all posts
	PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
	
	//get single post
	PostDto getPostById(Integer postId);
	
	//get all posts by category
	List<PostDto> getPostsByCategory(Integer categoryId);
	
	//get all posts by user
	List<PostDto> getPostsByuser(Integer userId);
	
	//search posts
	List<PostDto> searchByPost(String keyword);
}
