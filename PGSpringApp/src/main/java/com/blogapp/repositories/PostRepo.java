package com.blogapp.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.blogapp.entities.Category;
import com.blogapp.entities.Post;
import com.blogapp.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer>{
	
	//Custom Finder Methods(findBy should be compulsory used at prefix)
	List<Post> findByUser(User user);
	
	List<Post> findByCategory(Category category);
	
	//Search Post
	List<Post> findByTitleContaining(String title);
}
