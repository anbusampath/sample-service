package com.example.demo;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class SortController {

	private static final String USER_SERVICE = "http://localhost:8080/users";
	private final RestTemplate restTemplate;

	public SortController(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@GetMapping("sorted")
	public List<User> getSorted()
	{
		ResponseEntity<List<User>> responseEntity = 
				  restTemplate.exchange(
						  USER_SERVICE,
				    HttpMethod.GET,
				    null,
				    new ParameterizedTypeReference<List<User>>() {
					}
				  );
		
		List<User> users = responseEntity.getBody();
		return users.stream()
									.sorted(Comparator.comparing(User::getFirstName))
									.collect(Collectors.toList());
	}
	
}
