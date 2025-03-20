package com.blogapplication.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blogapplication.entities.Category;
import com.blogapplication.entities.Post;
import com.blogapplication.entities.User;
import com.blogapplication.exceptions.ResourceNotFoundException;
import com.blogapplication.payload.PostDto;
import com.blogapplication.payload.PostResponse;
import com.blogapplication.repositories.CategoryRepository;
import com.blogapplication.repositories.PostRepository;
import com.blogapplication.repositories.UserRepository;
import com.blogapplication.services.PostService;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		
		User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "user id", userId));
		
		Category category = categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "category id", categoryId));
		
		Post post = dtoToPost(postDto);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		Post newPost = postRepository.save(post);
		return postToDto(newPost);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "post id", postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		
		Post updatedPost = postRepository.save(post);
		
		return postToDto(updatedPost);
	}

	@Override
	public void deletePost(Integer postId) {
		postRepository.deleteById(postId);
	}

	@Override
	public PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
		
		Sort sort = (sortDir.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		
		Pageable p = PageRequest.of(pageNumber, pageSize, sort);
		Page<Post> pagePost = postRepository.findAll(p);
		List<Post> allPosts = pagePost.getContent();
		List<PostDto> postDtos = allPosts.stream().map(post->postToDto(post)).collect(Collectors.toList());
		
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		
		return postResponse;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "post id", postId));
		return postToDto(post);
	}

	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {
		Category category = categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "category id", categoryId));
		List<Post> savedPosts = postRepository.findAByCategory(category);
		return savedPosts.stream().map(post->postToDto(post)).collect(Collectors.toList());
	}

	@Override
	public List<PostDto> getPostsByuser(Integer userId) {
		User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "user id", userId));
		List<Post> savedPosts = postRepository.findAByUser(user);
		return savedPosts.stream().map(post->postToDto(post)).collect(Collectors.toList());
	}

	@Override
	public List<PostDto> searchByPost(String keyword) {
		List<Post> posts = postRepository.findByTitleContaining(keyword);
		return posts.stream().map(post->postToDto(post)).collect(Collectors.toList());
	}
	
	public Post dtoToPost(PostDto postDto) {
		return modelMapper.map(postDto, Post.class);
	}
	
	public PostDto postToDto(Post post) {
		return modelMapper.map(post, PostDto.class);
	}

}
