package com.realestate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.realestate.model.Lawyer;
import com.realestate.repository.LawyerRepository;
import java.util.*;
@Service
public class LawyerService 
{
	@Autowired
	private LawyerRepository lawyerRepository;

	public List<Lawyer> findAll() 
	{
		// TODO Auto-generated method stub
		return lawyerRepository.findAll();
	}

	public Lawyer findById(Long id) 
	{
		// TODO Auto-generated method stub
		return lawyerRepository.getById(id);
	}
}
