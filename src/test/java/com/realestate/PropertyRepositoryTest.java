package com.realestate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import com.realestate.model.Property;
import com.realestate.model.PropertyType;
import com.realestate.repository.PropertyRepository;
import com.realestate.service.PropertyService;


@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Commit 
@Rollback(false)
class PropertyRepositoryTest 
{

    @Autowired
    private PropertyRepository propertyRepository;
     
    @Test
    void testSaveProperty_ShouldPersistInDatabase()
    {
        Property property = new Property();
        property.setTitle("Test House");
        property.setLocation("Yangon");
        property.setPrice(1234567);
        property.setDescription("Test description");

        propertyRepository.save(property);
        System.out.println();
        System.out.println("***** Save Property Test******");
        System.out.println("Saved Property ID: " + property.getId());
        System.out.println("***********");
        System.out.println();
    }

    @Test
    void testDeleteById() 
    {
        Long id = 3L; //testProperty.getId();
        
        // ✅ Confirm property exists
        Optional<Property> found = propertyRepository.findById(id);
        assertTrue(found.isPresent());

        // ✅ Delete it
        propertyRepository.deleteById(id);

        // ❌ Should not exist anymore
        Optional<Property> deleted = propertyRepository.findById(id);
        System.out.println();
        System.out.println("***** Delete by Id Test ******");
        assertFalse(deleted.isPresent());
        System.out.println();
        System.out.println("***********");   
        System.out.println();
    }
    
    @Test
    void testGetByPropertyType()
    {
    	PropertyType propertyType = new PropertyType();
    	propertyType.setId(1);
        List<Property> houses = propertyRepository.getByPropertyType(propertyType);

        assertNotNull(houses);
        assertFalse(houses.isEmpty());

        for (Property p : houses) 
        {
            assertEquals("House", p.getPropertyType().getName());
        }
        System.out.println();
        System.out.println("*****Get Property Type Test ******");
        System.out.println("Houses found: " + houses.size());
        System.out.println("***********");
        System.out.println();
    }
    
    @Test
    void testGetByOwnerId() 
    {
        Long ownerId = 1L;

        List<Property> properties = propertyRepository.getByOwnerId(ownerId);

        assertNotNull(properties);
        assertEquals(3, properties.size());

        for (Property property : properties) 
        {
            assertEquals(ownerId, property.getOwner().getId());
        }
        System.out.println();
        System.out.println("*****Get Owner Id Test ******");
        System.out.println("Properties owned by John: " + properties.size());
        System.out.println("***********");
        System.out.println();
    }
    
    @Test
    void testFindByLocationContaining() 
    {
        String keyword = "yy";

        List<Property> results = propertyRepository.findByLocationContaining(keyword);

        assertNotNull(results);
        assertFalse(results.isEmpty());

        for (Property property : results)
        {
            assertTrue(property.getLocation().toLowerCase().contains(keyword.toLowerCase()));
        }
        System.out.println();
        System.out.println("***** Search by Location Test ******");
        System.out.println("Properties matching '" + keyword + "': " + results.size());
        System.out.println("***********");
        System.out.println();
    }
}

