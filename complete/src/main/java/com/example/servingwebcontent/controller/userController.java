package com.example.servingwebcontent.controller;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.service.UserService;
import com.example.service.UserService.UserDto;

@RestController  

public class userController {
	
	@Autowired
    private UserService userService;
	
   @PostMapping("/api/createUser")
   public void createUser(@RequestParam("username") String username, @RequestParam("email") String email,
		   @RequestParam("password") String password) {
	   userService.createUser(username, email, password);
   }
   
   @PostMapping("/api/updateUserProfile")
   public void updateUser(@RequestParam("username") String username, @RequestParam("email") String email,
		   @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName
		   , @RequestParam("userId") Integer userId) {
	   userService.updateUser(username, email, firstName, lastName, userId);
   }
   
   @GetMapping("/api/getUser")
   public Object getUser(@RequestParam("username") String username, 
		   @RequestParam("password") String password) {
	   try {
		   return userService.getUser(username, password);
	   } catch (Exception e) {
		   return e;
	   }
	   
   }
   
   @GetMapping("/api/getUserById")
   public Map<String,Object> getUserById(@RequestParam("userId") Integer userId) {
	   return userService.getUserById(userId);
   }
   
   @PostMapping("/api/changePassword")
   public Boolean changePassword(@RequestParam("oldPassword") String oldPassword, 
		   @RequestParam("newPassword") String newPassword, @RequestParam("userId") Integer userId) {
	   return userService.changePassword(oldPassword, newPassword, userId);
   }
   
}
