package com.blogapplication.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogapplication.entities.Comment;
import com.blogapplication.entities.Post;
import com.blogapplication.exceptions.ResourceNotFoundException;
import com.blogapplication.payload.CommentDto;
import com.blogapplication.repositories.CommentRepository;
import com.blogapplication.repositories.PostRepository;
import com.blogapplication.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		Post post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "post id", postId));
		Comment comment = dtoToComment(commentDto);
		comment.setPost(post);
		Comment savedComment = commentRepository.save(comment);
		return CommentToDto(savedComment);
	}

	@Override
	public void deleteComment(Integer commentId) {
		Comment comment = commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment", "comment id", commentId));
		commentRepository.delete(comment);
	}
	
	public Comment dtoToComment(CommentDto commentDto) {
		return modelMapper.map(commentDto, Comment.class);
	}
	
	public CommentDto CommentToDto(Comment comment) {
		return modelMapper.map(comment, CommentDto.class);
	}

}
