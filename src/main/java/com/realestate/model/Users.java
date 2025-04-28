package com.realestate.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Users 
{
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	 private String name;
	 private String email;
	 private String phone;
	 private String password;
	 private String address;
	 private String image;
	 private String status;
	 private Integer numOfPost;
	 
	public Users() 
	{
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public Users(Long id, String name, String email, String phone, String password, String address, String image,
			String status, Integer numOfPost) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.address = address;
		this.image = image;
		this.status = status;
		this.numOfPost = numOfPost;
	}

	

	public Integer getNumOfPost() 
	{
		return numOfPost;
	}



	public void setNumOfPost(Integer numOfPost)
	{
		this.numOfPost = numOfPost;
	}



	public Long getId() 
	{
		return id;
	}
	public void setId(Long id) 
	{
		this.id = id;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getEmail() 
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public String getPhone()
	{
		return phone;
	}
	public void setPhone(String phone) 
	{
		this.phone = phone;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password) 
	{
		this.password = password;
	}
	public String getImage() 
	{
		return image;
	}
	public void setImage(String image) 
	{
		this.image = image;
	}

	public String getAddress() 
	{
		return address;
	}

	public void setAddress(String address) 
	{
		this.address = address;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	 
	 
	

}
