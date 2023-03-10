package com.rest.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.rest.example.model.UserModel;

@Service
public class UserServiceImpl implements UserService{
	
	@Value("${external.api.url}")
	private String postApiBaseUrl;

	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public UserModel[] getAllUsers() {
		UserModel[] result = restTemplate.getForObject(postApiBaseUrl+"/users", UserModel[].class);
		System.out.println(result);
		
		return result;
	}
	
}
