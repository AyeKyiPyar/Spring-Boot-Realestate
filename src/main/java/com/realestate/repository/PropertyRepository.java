package com.realestate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.realestate.model.Property;
import com.realestate.model.PropertyType;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> 
{
    List<Property> findByLocationContaining(String keyword);

	List<Property> getByOwnerId(Long id);

	List<Property> getByPropertyType(PropertyType propertyType);

	List<Property> findByFloorAndPropertyTypeAndPrice(Integer floor, PropertyType propertyType, Integer price);

	List<Property> findByStoryAndPropertyTypeAndPrice(Integer story, PropertyType propertyType, Integer price);

	List<Property> findByAreaAndPropertyTypeAndPrice(Integer area, PropertyType propertyType, Integer price);

	
}
