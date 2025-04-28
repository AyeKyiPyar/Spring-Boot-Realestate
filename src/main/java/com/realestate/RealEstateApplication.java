package com.realestate;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.realestate.controller.RealEstateController;

@SpringBootApplication
public class RealEstateApplication
{

	public static void main(String[] args) 
	{
		//new File(AdminController.uploadDirectory).mkdir();
				new File(RealEstateController.uploadDirectory).mkdir();
		SpringApplication.run(RealEstateApplication.class, args);
	}

}
