package com.realestate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.realestate.repository.PropertyTypeRepository;

@Service
public class PropertyTypeService 
{
	@Autowired
	private PropertyTypeRepository propertyTypeRepository;
}
