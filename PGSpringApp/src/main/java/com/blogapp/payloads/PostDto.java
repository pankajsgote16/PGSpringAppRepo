package com.blogapp.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.blogapp.entities.Comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
	
	private int postId;
	
	private String title;
	
	private String content;
	
	private String imageName="default.png";
	
	private Date addedDate;
	
	private UserDto user;

	private CategoryDto category;
	
	private Set<CommentDto> comments= new HashSet<>();
}
