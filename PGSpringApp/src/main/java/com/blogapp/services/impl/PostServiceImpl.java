package com.blogapp.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.blogapp.entities.Category;
import com.blogapp.entities.Post;
import com.blogapp.entities.User;
import com.blogapp.exceptions.ResourceNotFoundException;
import com.blogapp.payloads.PostDto;
import com.blogapp.repositories.CategoryRepo;
import com.blogapp.repositories.PostRepo;
import com.blogapp.repositories.UserRepo;
import com.blogapp.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer catgId) {
		// postDto - in this we have only fetched title and content so another fields we
		// have to set over here before saving entity to DB.
		Post post = modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		// fetching user to set in post
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User ", "userId ", userId));
		// Fetching Category to set in post
		Category category = this.categoryRepo.findById(catgId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "catgId", catgId));
		post.setUser(user);
		post.setCategory(category);
		Post newPost = this.postRepo.save(post);
		return modelMapper.map(newPost, PostDto.class);
	}

	// GetAllPosts
	@Override
	public List<PostDto> viewPosts() {
		List<Post> allPosts = this.postRepo.findAll();
		List<PostDto> postDtos = allPosts.stream().map(post -> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		return postDtos;
	}

	// GetPostById
	@Override
	public PostDto viewPost(Integer postId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
		return this.modelMapper.map(post, PostDto.class);
	}

	// updatePost
	@Override
	public PostDto editPost(PostDto postDto, Integer postId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		Post updatedPost = this.postRepo.save(post);
		return modelMapper.map(updatedPost, PostDto.class);
	}

	// deletePost
	@Override
	public void deletePost(Integer postId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
		this.postRepo.delete(post);
	}

	@Override
	public List<PostDto> fetchPostByUser(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
		List<Post> postsByUser = this.postRepo.findByUser(user);
		List<PostDto> mappedToPostDto = postsByUser.stream().map(post -> modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		return mappedToPostDto;
	}

	@Override
	public List<PostDto> fetchPostByCategory(Integer catgId) {
		Category category = this.categoryRepo.findById(catgId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "catgId", catgId));
		List<Post> postsByCategory = postRepo.findByCategory(category);
		List<PostDto> mappedToPostDto = postsByCategory.stream().map(post -> modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		return mappedToPostDto;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}
