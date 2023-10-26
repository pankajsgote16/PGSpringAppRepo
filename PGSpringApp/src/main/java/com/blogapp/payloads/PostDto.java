package com.blogapp.payloads;

import java.util.Date;

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
}
