package com.realestate.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Lawyer 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String graduate;
	private String image;
	private String email;
	private String phone;
	
	public Lawyer() 
	{
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Lawyer(Long id, String name, String graduate, String image, String email, String phone)
	{
		super();
		this.id = id;
		this.name = name;
		this.graduate = graduate;
		this.image = image;
		this.email = email;
		this.phone = phone;
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

	public String getGraduate()
	{
		return graduate;
	}

	public void setGraduate(String graduate)
	{
		this.graduate = graduate;
	}



	public String getImage() 
	{
		return image;
	}



	public void setImage(String image) 
	{
		this.image = image;
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
	
	

}
