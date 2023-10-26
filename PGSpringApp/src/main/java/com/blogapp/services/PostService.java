package com.blogapp.services;

import java.util.List;
import com.blogapp.payloads.PostDto;
import com.blogapp.payloads.PostResponse;

public interface PostService {

	// create
	PostDto createPost(PostDto Post, Integer userId, Integer catgId);

	// update
	PostDto editPost(PostDto Post, Integer postId);

	// delete
	void deletePost(Integer postId);

	// fetchall
	PostResponse viewPosts(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);

	// fetchById
	PostDto viewPost(Integer postId);

	List<PostDto> fetchPostByUser(Integer userId);

	List<PostDto> fetchPostByCategory(Integer catgId);

	List<PostDto> searchPosts(String keyword);
}
