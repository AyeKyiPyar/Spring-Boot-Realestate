package com.realestate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.realestate.model.PropertyType;

public interface PropertyTypeRepository extends JpaRepository<PropertyType, Integer>
{

}
