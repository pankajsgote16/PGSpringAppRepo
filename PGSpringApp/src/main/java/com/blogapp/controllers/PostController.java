package com.blogapp.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.blogapp.payloads.ApiResponse;
import com.blogapp.payloads.PostDto;
import com.blogapp.payloads.PostResponse;
import com.blogapp.services.PostService;

@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	private PostService postService;

	// create
	@PostMapping("/user/{userId}/category/{catgId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId,
			@PathVariable Integer catgId) {
		PostDto createdPost = this.postService.createPost(postDto, userId, catgId);
		return new ResponseEntity<PostDto>(createdPost, HttpStatus.CREATED);
	}

	// getAllPosts
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> fetchAllPosts(
			@RequestParam(value ="pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value ="pageSize", defaultValue = "10", required = false) Integer pageSize,
			@RequestParam(value ="sortBy", defaultValue = "postId", required = false) String sortBy,
			@RequestParam(value = "sortDir",defaultValue = "asc",required = false)String sortDir
			) {
		PostResponse posts = this.postService.viewPosts(pageNumber,pageSize,sortBy,sortDir);
		return new ResponseEntity<PostResponse>(posts, HttpStatus.OK);
	}

	// getPost
	@GetMapping("/post/{postId}")
	public ResponseEntity<PostDto> fetchPost(@PathVariable Integer postId) {
		PostDto post = this.postService.viewPost(postId);
		return new ResponseEntity<PostDto>(post, HttpStatus.OK);
	}

	// getPostsByUser
	@GetMapping("user/{userId}/posts")
	public ResponseEntity<List<PostDto>> fetchPostByUser(@PathVariable Integer userId) {
		List<PostDto> postsByUser = this.postService.fetchPostByUser(userId);
		return new ResponseEntity<List<PostDto>>(postsByUser, HttpStatus.OK);
	}

	// getPostsByCategory
	@GetMapping("category/{catgId}/posts")
	public ResponseEntity<List<PostDto>> fetchPostByCategory(@PathVariable Integer catgId) {
		List<PostDto> postsByCategory = this.postService.fetchPostByCategory(catgId);
		return new ResponseEntity<List<PostDto>>(postsByCategory, HttpStatus.OK);
	}

	// Update
	@PutMapping("/post/{postId}")
	public ResponseEntity<PostDto> editPost(@RequestBody PostDto postDto, @PathVariable Integer postId) {
		PostDto updatedPostDto = this.postService.editPost(postDto, postId);
		return new ResponseEntity<PostDto>(updatedPostDto, HttpStatus.OK);
	}

	// DeletePost
	@DeleteMapping("post/{postId}")
	public ApiResponse removePost(@PathVariable Integer postId) {
		this.postService.deletePost(postId);
		return new ApiResponse("Post Deleted Successfullyy ! ! !", true);
	}
	
	//Search Post
	@GetMapping("/posts/search/{keywords}")
	public ResponseEntity<List<PostDto>> searchPost(@PathVariable("keywords") String keyWords) {
		List<PostDto> searchPosts = this.postService.searchPosts(keyWords);
		return new ResponseEntity<List<PostDto>>(searchPosts, HttpStatus.OK);
	}

}
