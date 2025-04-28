package com.realestate.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Property 
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String location;
    private Integer price;
    private String description;
    private Integer area;
    private Integer numOfBedroom;
    private Integer numOfBathroom;
    private String image;
    private String address;
    private Integer story;
    private Integer floor;
    
    
    
    @ManyToOne
    private PropertyType propertyType;
    
    
    @ManyToOne
    private Users owner;
    
	public Property() 
	{
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Integer getFloor() 
	{
		return floor;
	}


	public void setFloor(Integer floor) 
	{
		this.floor = floor;
	}


	public Integer getStory() 
	{
		return story;
	}


	public void setStory(Integer story) 
	{
		this.story = story;
	}






	public String getImage() 
	{
		return image;
	}

	

	public String getAddress() 
	{
		return address;
	}



	public void setAddress(String address)
	{
		this.address = address;
	}



	public void setImage(String image) 
	{
		this.image = image;
	}

	public Long getId() 
	{
		return id;
	}
	public void setId(Long id) 
	{
		this.id = id;
	}
	public String getTitle() 
	{
		return title;
	}
	public void setTitle(String title) 
	{
		this.title = title;
	}
	public String getLocation() 
	{
		return location;
	}
	public void setLocation(String location)
	{
		this.location = location;
	}
	public Integer getPrice() 
	{
		return price;
	}
	public void setPrice(Integer price)
	{
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Users getOwner() {
		return owner;
	}
	public void setOwner(Users owner) {
		this.owner = owner;
	}

	public PropertyType getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(PropertyType propertyType) {
		this.propertyType = propertyType;
	}



	public Integer getArea() {
		return area;
	}



	public void setArea(Integer area) {
		this.area = area;
	}



	public Integer getNumOfBedroom() {
		return numOfBedroom;
	}



	public void setNumOfBedroom(Integer numOfBedroom) {
		this.numOfBedroom = numOfBedroom;
	}



	public Integer getNumOfBathroom() {
		return numOfBathroom;
	}



	public void setNumOfBathroom(Integer numOfBathroom) {
		this.numOfBathroom = numOfBathroom;
	}
	
	
}
