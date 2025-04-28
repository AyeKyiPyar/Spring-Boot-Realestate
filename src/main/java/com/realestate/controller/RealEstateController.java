package com.realestate.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.realestate.model.*;
import com.realestate.service.*;


import ch.qos.logback.core.joran.spi.NoAutoStart;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;




@Controller
public class RealEstateController 
{
	@Autowired
    private PropertyService propertyService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private LawyerService lawyerService;
	
	@Autowired
	private BankService bankService;
	
	@Autowired
	private PropertyTypeService propertyTypeService;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
    @GetMapping("/login")
    public String home(Model model)
    {
        model.addAttribute("user", new Users());
       
        return "login";
    }
    
    @PostMapping("/login/process")
    public String login(@ModelAttribute Users user, Model model, RedirectAttributes redirectAttributes)
    {
        Users loginUser = userService.findByEmail(user.getEmail());
        
        if (loginUser != null && passwordEncoder.matches(user.getPassword(), loginUser.getPassword())) 
        {
        	PropertyType propertyType = new PropertyType();
      	   	propertyType.setId(1);
      	   	List<Property> properties = propertyService.getByPropertyType(propertyType);
      	   	
      	   	model.addAttribute("properties", properties);
        	//model.addAttribute("properties", propertyService.getAllProperties());
      	   	
      	  // 👇 Authenticate in Spring Security context
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(loginUser, null, List.of());

            SecurityContextHolder.getContext().setAuthentication(authToken);
        	
      	   	session.setAttribute("loginUser", loginUser);
      	   	
        	return "index";
        }
        
        redirectAttributes.addFlashAttribute("error", "Invalid username or password.");
        return "redirect:/login?error";
    }
    
    @GetMapping("/index")
    public String index(Model model)
    {
    	Users user = (Users) session.getAttribute("loginUser");
    	if ( user != null)
    	{
    		PropertyType propertyType = new PropertyType();
      	   	propertyType.setId(1);
      	   	List<Property> properties = propertyService.getByPropertyType(propertyType);
      	   	model.addAttribute("properties", properties);
        	//model.addAttribute("properties", propertyService.getAllProperties());
    		return "view-house";
    	}
    	else
    	{
    	
    		return "redirect:/";
    	}
    }
    public static String uploadDirectory= System.getProperty("user.dir") + "/src/main/resources/static/images";

    @GetMapping("/register")
	public String registerForm(Model model)
	{
    	 model.addAttribute("user", new Users());

		
		return "register";
	}
    
   @PostMapping("/register/process")
   public String register(@ModelAttribute Users user, @RequestParam MultipartFile file) throws IOException
   {
	 
	   // upload image file to project
		String fileName = file.getOriginalFilename();
			
		Path filePath = Paths.get(uploadDirectory, fileName);
		Files.write(filePath, file.getBytes());
		user.setImage(fileName);
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String hashedPassword = encoder.encode(user.getPassword());
		user.setPassword(hashedPassword);
		System.out.println(hashedPassword);
		user.setStatus("basic");
		user.setNumOfPost(0);
		Users registerUser = userService.save(user);
		
		if (registerUser != null)
		{
			return "redirect:/login";
		}
		else
		{
			return "redirect:/register";
		}
   }
   
   @GetMapping("/search")
   public String search(@RequestParam String location, Model model)
   {
	   List<Property> properties = propertyService.findByLocation(location);
	   System.out.println(properties.size());
	   
	   model.addAttribute("properties",properties);
	   
	   return "index";
   }
   
   @GetMapping("/filter/house")
   public String filterHouse(@RequestParam Integer story,@RequestParam Integer type,@RequestParam Integer price, Model model)
   {
	   PropertyType propertyType = new PropertyType();
	   propertyType.setId(type);
	   List<Property> properties = propertyService.findByStoryAndPropertyTypeAndPrice(story,propertyType,price);
	   System.out.println(properties.size());
	   
	   model.addAttribute("properties",properties);
	   
	  return "view-house";
   }
   
   @GetMapping("/filter/flat")
   public String filterFlat(@RequestParam Integer floor,@RequestParam Integer type,@RequestParam Integer price, Model model)
   {
	   PropertyType propertyType = new PropertyType();
	   propertyType.setId(type);
	   List<Property> properties = propertyService.findByFloorAndPropertyTypeAndPrice(floor,propertyType,price);
	   System.out.println(properties.size());
	   
	   model.addAttribute("properties",properties);
	   
	  return "view-flat";
   }
   
   @GetMapping("/filter/land")
   public String filterLand(@RequestParam Integer area,@RequestParam Integer type,@RequestParam Integer price, Model model)
   {
	   PropertyType propertyType = new PropertyType();
	   propertyType.setId(type);
	   List<Property> properties = propertyService.findByAreaAndPropertyTypeAndPrice(area,propertyType,price);
	   System.out.println(properties.size());
	   
	   model.addAttribute("properties",properties);
	   
	  return "view-land";
   }
   
   @GetMapping("/premium")
   private String premium(Model model)
   {
	   model.addAttribute("bank", new Bank());
	   return "payment";
   }
   
   @GetMapping("/lawyer")
   private String lawyer(Model model)
   {
	   List<Lawyer> lawyers = lawyerService.findAll();
	   model.addAttribute("lawyers", lawyers);
	   return "lawyer";
   }
   
   @GetMapping("/lawyer/book")
   private String bookLawyer(Model model)
   {
	   
	   return "check-calendar";
   }
   
   @GetMapping("/check-land")
   private String checkLandOwnership()
   {
	   
	   return "check-land-ownership";
   }
   
   @GetMapping("/house")
   private String addHouse(Model model)
   {
	   model.addAttribute("property", new Property());
	   return "post";
   }
   
   @GetMapping("/post/house")
   private String addPost(Model model)
   {
	   model.addAttribute("property", new Property());
	   return "house";
   }
   
  
   @GetMapping("/post/flat")
   private String postFlat(Model model)
   {
	   model.addAttribute("property", new Property());
	   return "flat";
   }
   
   @GetMapping("/post/land")
   private String viewLand(Model model)
   {
	   model.addAttribute("property", new Property());
	   return "land";
   }
   
   @GetMapping("/post/edit/{id}")
   private String editPost(Model model, @PathVariable Long id)
   {
	  Property property = propertyService.getById(id);
	  model.addAttribute("property", property);
	   return "edit-property";
   }
   
   @PostMapping("/post/edit/process")
   private String editPostProcess(Model model, @ModelAttribute Property property, @RequestParam MultipartFile file) throws IOException
   {
	   // upload image file to project
		String fileName = file.getOriginalFilename();
			
		Path filePath = Paths.get(uploadDirectory, fileName);
		Files.write(filePath, file.getBytes());
		property.setImage(fileName);
	   Property updatedProperty = propertyService.updateCategory(property);
	   return "redirect:/profile";
   }
   
   
   @PostMapping("/post/add/house")
   public String addPost(@ModelAttribute Property property, @RequestParam MultipartFile file, Model model) throws IOException
   {
	   
	   	Users user = (Users) session.getAttribute("loginUser");
	   	if (user.getStatus().equalsIgnoreCase("premium"))
	   	{
	   	// upload image file to project
			String fileName = file.getOriginalFilename();
				
			Path filePath = Paths.get(uploadDirectory, fileName);
			Files.write(filePath, file.getBytes());
			
			property.setOwner(user);
			property.setImage(fileName);
			PropertyType propertytype = new PropertyType();
			propertytype.setId(1);
			property.setPropertyType(propertytype);
			
			Property saveProperty = propertyService.save(property);
			userService.updateNumOfPost(user);
			
			if (saveProperty != null)
			{
				return "redirect:/profile";
			}
			else
			{
				return "redirect:/post/house";
			}
	   	}
	   	else 
	   	{
	   		if (user.getNumOfPost() == 5)
	   		{
	   			model.addAttribute("error", "Your post have not allowed");
	   			return "/house";
	   		}
	   		else
	   		{
	   		// upload image file to project
				String fileName = file.getOriginalFilename();
					
				Path filePath = Paths.get(uploadDirectory, fileName);
				Files.write(filePath, file.getBytes());
				
				property.setOwner(user);
				property.setImage(fileName);
				PropertyType propertytype = new PropertyType();
				propertytype.setId(1);
				property.setPropertyType(propertytype);
				
				Property saveProperty = propertyService.save(property);
				userService.updateNumOfPost(user);
				
				if (saveProperty != null)
				{
					return "redirect:/profile";
				}
				else
				{
					return "redirect:/post/house";
				}
	   		}
	   	}
	  
   }
   
   @PostMapping("/post/add/flat")
   public String addPostFlat(@ModelAttribute Property property, @RequestParam MultipartFile file, Model model) throws IOException
   {
	   
	   	Users user = (Users) session.getAttribute("loginUser");
	   	if (user.getStatus().equalsIgnoreCase("premium"))
	   	{
	   	// upload image file to project
			String fileName = file.getOriginalFilename();
				
			Path filePath = Paths.get(uploadDirectory, fileName);
			Files.write(filePath, file.getBytes());
			
			property.setOwner(user);
			property.setImage(fileName);
			PropertyType propertytype = new PropertyType();
			propertytype.setId(2);
			property.setPropertyType(propertytype);
			
			Property saveProperty = propertyService.save(property);
			userService.updateNumOfPost(user);
			
			if (saveProperty != null)
			{
				return "redirect:/profile";
			}
			else
			{
				return "redirect:/post/flat";
			}
	   	}
	   	else 
	   	{
	   		if (user.getNumOfPost() == 5)
	   		{
	   			model.addAttribute("error", "Your post have not allowed");
	   			return "/flat";
	   		}
	   		else
	   		{
	   		// upload image file to project
				String fileName = file.getOriginalFilename();
					
				Path filePath = Paths.get(uploadDirectory, fileName);
				Files.write(filePath, file.getBytes());
				
				property.setOwner(user);
				property.setImage(fileName);
				PropertyType propertytype = new PropertyType();
				propertytype.setId(2);
				property.setPropertyType(propertytype);
				
				Property saveProperty = propertyService.save(property);
				userService.updateNumOfPost(user);
				
				if (saveProperty != null)
				{
					return "redirect:/profile";
				}
				else
				{
					return "redirect:/post/flat";
				}
	   		}
	   	}
	  
   }
   
   @PostMapping("/post/add/land")
   public String addPostLand(@ModelAttribute Property property, @RequestParam MultipartFile file, Model model) throws IOException
   {
	   
	   	Users user = (Users) session.getAttribute("loginUser");
	   	if (user.getStatus().equalsIgnoreCase("premium"))
	   	{
	   	// upload image file to project
			String fileName = file.getOriginalFilename();
				
			Path filePath = Paths.get(uploadDirectory, fileName);
			Files.write(filePath, file.getBytes());
			
			property.setOwner(user);
			property.setImage(fileName);
			PropertyType propertytype = new PropertyType();
			propertytype.setId(3);
			property.setPropertyType(propertytype);
			
			Property saveProperty = propertyService.save(property);
			userService.updateNumOfPost(user);
			
			if (saveProperty != null)
			{
				return "redirect:/profile";
			}
			else
			{
				return "redirect:/post/land";
			}
	   	}
	   	else 
	   	{
	   		if (user.getNumOfPost() == 5)
	   		{
	   			model.addAttribute("error", "Your post have not allowed");
	   			return "/land";
	   		}
	   		else
	   		{
	   		// upload image file to project
				String fileName = file.getOriginalFilename();
					
				Path filePath = Paths.get(uploadDirectory, fileName);
				Files.write(filePath, file.getBytes());
				
				property.setOwner(user);
				property.setImage(fileName);
				PropertyType propertytype = new PropertyType();
				propertytype.setId(3);
				property.setPropertyType(propertytype);
				
				Property saveProperty = propertyService.save(property);
				userService.updateNumOfPost(user);
				
				if (saveProperty != null)
				{
					return "redirect:/profile";
				}
				else
				{
					return "redirect:/post/land";
				}
	   		}
	   	}
	  
   }
   
   @GetMapping("/property/delete/{id}")
   public String deleteProperty(@PathVariable Long id)
   {
	   Users user = (Users) session.getAttribute("loginUser");
	   Boolean isDeleted =propertyService.deleteById(id);
	   userService.decreaseNumOfPost(user);
	   return "redirect:/profile";
   }
   
   @GetMapping("/view/house")
   private String viewHouse(Model model)
   {
	   PropertyType propertyType = new PropertyType();
	   propertyType.setId(1);
	   List<Property> properties = propertyService.getByPropertyType(propertyType);
	   model.addAttribute("properties", properties);
	   return "view-house";
   }
   
   @GetMapping("/view-detail/{id}")
   private String viewDetail(Model model, @PathVariable Long id)
   {
	   Property property = propertyService.getById(id);
	   model.addAttribute("property", property);
	   return "view-detail";
   }
   
   @GetMapping("/view/flat")
   private String viewFlat(Model model)
   {
	   PropertyType propertyType = new PropertyType();
	   propertyType.setId(2);
	   List<Property> properties = propertyService.getByPropertyType(propertyType);
	   model.addAttribute("properties", properties);
	   return "view-flat";
   }
   @GetMapping("/view/land")
   private String addLand(Model model)
   {
	   PropertyType propertyType = new PropertyType();
	   propertyType.setId(3);
	   List<Property> properties = propertyService.getByPropertyType(propertyType);
	   model.addAttribute("properties", properties);
	   return "view-land";
   }
   
   @GetMapping("/profile")
   private String viewProfile(Model model)
   {
	   Users user = (Users) session.getAttribute("loginUser");
	   Users updateUser = userService.getById(user.getId());
	   
	   List<Property> properties = propertyService.getByOwnerId(user.getId());
	   session.setAttribute("loginUser", updateUser);
	   model.addAttribute("user", updateUser);
	   model.addAttribute("properties", properties);
	   
	   return "profile";
   }
    
    @GetMapping("/property/{id}")
    public String viewProperty(@PathVariable Long id, Model model)
    {
        model.addAttribute("property", propertyService.getById(id));
        return "property-detail";
    }
    
    @PostMapping("/payment")
    public String payment(@ModelAttribute Bank bank, Model model,RedirectAttributes redirectAttributes)
    {
    	Users user = (Users) session.getAttribute("loginUser");
    	Boolean isPayment = bankService.payment(bank);
   
    	
    	if (isPayment)
    	{
    		userService.updateUser(user);
    		return "redirect:/profile";
    	}
    	 redirectAttributes.addFlashAttribute("error", "Invalid Card");
    	return "redirect:/premium";
    }
	
    @GetMapping("/confirm-lawyer")
    public String getMethodName(@RequestParam String date, @RequestParam String timeSlot, Model model)
    {
    	model.addAttribute("date",date);
    	model.addAttribute("timeSlot", timeSlot);
        return "booking-confirm";
    }
    
}
