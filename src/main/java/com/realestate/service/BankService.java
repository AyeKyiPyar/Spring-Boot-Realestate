package com.realestate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.realestate.model.Bank;
import com.realestate.repository.BankRepository;

@Service
public class BankService 
{
	@Autowired
	private BankRepository bankReporitory;

	public Boolean payment(Bank bank)
	{
		Bank existBank = bankReporitory.findByAccountNumber(bank.getAccountNumber());
		if (existBank != null)
		{
			existBank.setBalance(existBank.getBalance() - 50000);
			bankReporitory.save(existBank);
			return true;
		}
		return false;
	}

}
