package com.realestate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.realestate.model.Property;
import com.realestate.model.PropertyType;
import com.realestate.repository.PropertyRepository;

@Service
public class PropertyService 
{

    @Autowired
    private PropertyRepository propertyRepository;

    public List<Property> getAllProperties() 
    {
        return propertyRepository.findAll();
    }

    public Property save(Property property) 
    {
        return propertyRepository.save(property);
    }

    public Property getById(Long id)
    {
        return propertyRepository.findById(id).orElse(null);
    }

	public List<Property> getByOwnerId(Long id) 
	{
		
		return propertyRepository.getByOwnerId(id);
	}

	public Boolean deleteById(Long id) 
	{
		
		if (propertyRepository.existsById(id))
		{
			System.out.println("deleted");
			propertyRepository.deleteById(id);
			return true;
		}
		else
			return false;
	}
	
	public Property updateCategory(Property property) 
	{
		Property existingProperty = propertyRepository.findById(property.getId()).get();
		
		existingProperty.setTitle(property.getTitle());
		existingProperty.setLocation(property.getLocation());
		existingProperty.setPrice(property.getPrice());
		existingProperty.setDescription(property.getDescription());
		existingProperty.setArea(property.getArea());
		existingProperty.setNumOfBedroom(property.getNumOfBathroom());
		existingProperty.setNumOfBathroom(property.getNumOfBathroom());
		existingProperty.setImage(property.getImage());
		
		existingProperty.setPropertyType(property.getPropertyType());
		existingProperty.setOwner(property.getOwner());
		
		return propertyRepository.save(existingProperty);
	}

	public List<Property> getByPropertyType(PropertyType propertyType) 
	{
		
		return propertyRepository.getByPropertyType(propertyType);
	}

	public List<Property> findByLocation(String location) 
	{
		return propertyRepository.findByLocationContaining(location);
	}




	public List<Property> findByStoryAndPropertyTypeAndPrice(Integer story, PropertyType propertyType, Integer price) 
	{
		// TODO Auto-generated method stub
		return propertyRepository.findByStoryAndPropertyTypeAndPrice(story, propertyType, price);
	}

	public List<Property> findByFloorAndPropertyTypeAndPrice(Integer floor, PropertyType propertyType, Integer price) 
	{
		// TODO Auto-generated method stub
		return propertyRepository.findByFloorAndPropertyTypeAndPrice(floor, propertyType, price);
	}

	public List<Property> findByAreaAndPropertyTypeAndPrice(Integer area, PropertyType propertyType, Integer price) {
		// TODO Auto-generated method stub
		return propertyRepository.findByAreaAndPropertyTypeAndPrice(area, propertyType, price);
	}

	
}
