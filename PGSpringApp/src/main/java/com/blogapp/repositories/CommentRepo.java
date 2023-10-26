package com.blogapp.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.blogapp.entities.Comment;
@Repository
public interface CommentRepo extends JpaRepository<Comment, Integer> {

	
}
