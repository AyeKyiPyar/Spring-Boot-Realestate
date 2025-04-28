package com.realestate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.realestate.model.Users;

public interface UserRepository extends JpaRepository<Users, Long>
{

	Users findByEmail(String email);

}
