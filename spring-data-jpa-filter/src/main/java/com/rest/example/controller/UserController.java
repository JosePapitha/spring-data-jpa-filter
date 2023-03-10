package com.rest.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.rest.example.model.UserModel;
import com.rest.example.service.UserService;


@RestController
@RequestMapping("/api/v1")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	/**
	 * Comment @jsonfilter in UserModel class to call this method
	 * @return
	 */
	
	@GetMapping("/users")
	public UserModel[] getAllUsers() {
		UserModel[] response = userService.getAllUsers();
		LOGGER.info("[UserController] - Inside user method");
		return response;
	}
	
	@GetMapping("/userdetails")
	public MappingJacksonValue filter1() {
		UserModel[] response = userService.getAllUsers();
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("name","username","email");
		FilterProvider filters = new SimpleFilterProvider().addFilter("UserDetails", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(response);
		mapping.setFilters(filters);
		return mapping;
	}
	
	@GetMapping("/useraddress")
	public MappingJacksonValue filter2() {
		UserModel[] response = userService.getAllUsers();
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("address");
		FilterProvider filters = new SimpleFilterProvider().addFilter("UserDetails", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(response);
		mapping.setFilters(filters);
		return mapping;
	}
	
		
}

