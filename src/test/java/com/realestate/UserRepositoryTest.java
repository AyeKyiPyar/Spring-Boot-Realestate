package com.realestate;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;

import com.realestate.model.Users;
import com.realestate.repository.UserRepository;
import com.realestate.service.UserService;

import jakarta.transaction.Transactional;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)  // This allows instance methods for @BeforeAll and @AfterAll
@Transactional
public class UserRepositoryTest 
{
	 @Autowired
	    private UserService userService;

	    @Autowired
	    private UserRepository userRepository;

	    private Users user;

	    @BeforeAll
	    void setUp()
	    {
	        user = new Users();
	        user.setEmail("test@example.com");
	        user.setName("Test User");
	        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String hashedPassword = encoder.encode("123");
			user.setPassword(hashedPassword);
	        user.setStatus("basic");
	        user.setNumOfPost(5);
	        user = userService.save(user);
	    }

	    @Test
	    void testFindByEmail() 
	    {
	        Users found = userService.findByEmail("test@example.com");
	        Assertions.assertNotNull(found);
	        Assertions.assertEquals("test@example.com", found.getEmail());
	    }

	    @Test
	    void testSaveUser() 
	    {
	        Users newUser = new Users();
	        newUser.setEmail("new@example.com");
	        newUser.setName("New User");
	        newUser.setNumOfPost(0);
	        newUser.setStatus("basic");
	        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String hashedPassword = encoder.encode("123");
			user.setPassword(hashedPassword);
	        Users saved = userService.save(newUser);
	        Assertions.assertNotNull(saved.getId());
	        Assertions.assertEquals("new@example.com", saved.getEmail());
	    }

	    @Test
	    void testUpdateUserStatus() 
	    {
	        user.setStatus("basic");
	        Users updated = userService.updateUser(user);
	        Assertions.assertEquals("premium", updated.getStatus());
	    }

	    @Test
	    void testUpdateNumOfPost()
	    {
	        int previous = user.getNumOfPost();
	        Users updated = userService.updateNumOfPost(user);
	        Assertions.assertEquals(previous + 1, updated.getNumOfPost());
	    }

	    @Test
	    void testDecreaseNumOfPost()
	    {
	        int previous = user.getNumOfPost();
	        Users updated = userService.decreaseNumOfPost(user);
	        Assertions.assertEquals(previous - 1, updated.getNumOfPost());
	    }

	    @Test
	    void testGetById()
	    {
	        Users found = userService.getById(user.getId());
	        Assertions.assertEquals(user.getEmail(), found.getEmail());
	    }
}
