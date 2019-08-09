package com.arnab.spring.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arnab.spring.api.domain.Ingredient;
import com.arnab.spring.api.service.IngredientService;

@RestController
@RequestMapping(value = "/pizza")
public class IngredientController {

	@Autowired
	private IngredientService service;
	
	@GetMapping(value = "/findall", produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<Ingredient> findAll() {
		return service.findAll();
	}
	
	@GetMapping(value = "/findone/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public Ingredient findOne(@PathVariable int id) {
		return service.findOne(id);
	}
	
	@PostMapping(value = "/save", consumes = {MediaType.APPLICATION_JSON_VALUE})
	public Ingredient save(@RequestBody Ingredient ingredient) {
		return service.save(ingredient);
	}
	
}
