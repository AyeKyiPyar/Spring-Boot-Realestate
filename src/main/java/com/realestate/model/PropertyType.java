package com.realestate.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PropertyType 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String description;
	
	public PropertyType()
	{
		
		super();
		// TODO Auto-generated constructor stub
	}

	public PropertyType(Integer id, String name, String description) 
	{
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public Integer getId() 
	{
		return id;
	}

	public void setId(Integer id)
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

	public String getDescription() 
	{
		return description;
	}

	public void setDescription(String description) 
	{
		this.description = description;
	}
	
	
}
