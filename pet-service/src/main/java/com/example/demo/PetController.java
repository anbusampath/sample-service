package com.example.demo;

import java.util.ArrayList;
import java.util.Comparator;

import static java.util.Comparator.*;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PetController {
	
	List<Pet> pets = new ArrayList<>();
	
	@PostMapping("pets")
	public Pet savePet(@RequestBody Pet pet) {
		pet.setId(UUID.randomUUID().toString());
		pets.add(pet);
		return pet;
	}
	
	@GetMapping("pets")
	public List<Pet> allPets() {
		return pets.stream()
				.sorted(comparing(Pet::getOwnerLastName))//, nullsFirst(naturalOrder())))
				.collect(Collectors.toList());
			
	}
	
	@GetMapping("pet/{id}")
	public Pet getPet(@PathVariable String id) {
		return pets.stream()
				.filter( pet -> pet.getId().equals(id))
				.findFirst()
				.orElseThrow();
	}

}
