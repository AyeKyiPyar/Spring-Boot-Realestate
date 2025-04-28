package com.realestate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.realestate.model.Lawyer;

public interface LawyerRepository extends JpaRepository<Lawyer, Long>
{

}
