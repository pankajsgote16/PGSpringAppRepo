package com.blogapp.entities;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "posts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer postId;
	
	@Column(name = "post_title",length = 100,nullable = false)
	private String title;
	
	@Column(name = "post_content",length = 1000)
	private String content;
	
	@Column(name = "posted_image")
	private String imageName;
	
	@Column(name = "posted_date")
	private Date addedDate;
	
	@ManyToOne
	@JoinColumn(name = "user_id") // for modification of name of column here added @JoinColumn
	private User user;

	@ManyToOne
	@JoinColumn(name = "catg_id")
	private Category category;

}
