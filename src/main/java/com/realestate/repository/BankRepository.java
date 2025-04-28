package com.realestate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.realestate.model.Bank;

public interface BankRepository extends JpaRepository<Bank, Integer>
{

	Bank findByAccountNumber(Integer accountNumber);

}
