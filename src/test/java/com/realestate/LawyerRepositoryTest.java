package com.realestate;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;

import com.realestate.model.Lawyer;
import com.realestate.repository.LawyerRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)  // This allows instance methods for @BeforeAll and @AfterAll
@Transactional
public class LawyerRepositoryTest 
{
	@Autowired
    private LawyerRepository lawyerRepository;

    private Lawyer lawyer;

    @BeforeAll
    void setUp() 
    {
        // Create and save a Lawyer instance for testing
        lawyer = new Lawyer();
        lawyer.setName("John Doe");
       
        lawyer = lawyerRepository.save(lawyer);  // Persist the lawyer
    }

    @Test
    void testFindAll() 
    {
        List<Lawyer> lawyers = lawyerRepository.findAll();

        // Assertions to check if the list contains the saved lawyer
        Assertions.assertNotNull(lawyers);
        Assertions.assertFalse(lawyers.isEmpty());
        Assertions.assertEquals(1, lawyers.size());  // Since we inserted only 1 lawyer
        Assertions.assertEquals("Laywer1", lawyers.get(0).getName());
    }

    @Test
    void testFindById()
    {
        Lawyer foundLawyer = lawyerRepository.getById(lawyer.getId());

        // Assertions to check if the correct lawyer is found
        Assertions.assertNotNull(foundLawyer);
        Assertions.assertEquals(lawyer.getId(), foundLawyer.getId());
        Assertions.assertEquals("John Doe", foundLawyer.getName());
        
    }
}
