package com.blogapp.entities;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "categories")
@NoArgsConstructor
@Getter
@Setter
public class Category {

	@Id
	@Column(name = "catg_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer categoryId;
	
	@Column(name = "catg_title",length =100,nullable = false)
	private String categoryTitle;
	
	@Column(name = "catg_description")
	private String categoryDescription;
	
	//One Category can have multiple Posts therefore we taken List<Post>
	//Cascade.All -> if child is deleted/saved/updated so automatically it will operate on its parent too and viseversa
	@OneToMany(mappedBy = "category",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	//@OneToMany
	List<Post> posts = new ArrayList<>();
}
