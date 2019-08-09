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
	
	@GetMapping(value = "/findall", produces = {MediaType.TEXT_HTML_VALUE})
	public String findAll() {
		StringBuilder html = new StringBuilder("<!DOCTYPE html><html><head><title>Pizza World</title></head><body>");
		html.append("<table><tr><td>ID</td><td>NAME</td><td>TYPE</td></tr>");
		List<Ingredient> list =  service.findAll();
		for(Ingredient ingredient: list) {
			html.append("<tr><td>" + ingredient.getId() + "</td><td>" + ingredient.getName() + "</td><td>" + ingredient.getType() + "</td></tr>");
		}
		html.append("</table><form method=\"POST\" th:action=\"@{/logout}\"><input type=\"submit\" value=\"Logout\"></form><br /><a href=\"/saveingredient\">Save Ingredient</a></body></html>");
		return html.toString();
	}
	
	@GetMapping(value = "/findone/{id}", produces = {MediaType.TEXT_HTML_VALUE})
	public String findOne(@PathVariable int id) {
		StringBuilder html = new StringBuilder("<!DOCTYPE html><html><head><title>Pizza World</title></head><body>");
		html.append("<table><tr><td>ID</td><td>NAME</td><td>TYPE</td></tr>");
		Ingredient ingredient = service.findOne(id);
		html.append("<tr><td>" + ingredient.getId() + "</td><td>" + ingredient.getName() + "</td><td>" + ingredient.getType() + "</td></tr>");
		html.append("</table><form method=\"POST\" th:action=\"@{/logout}\"><input type=\"submit\" value=\"Logout\"></form><br /><a href=\"/saveingredient\">Save Ingredient</a></body></html>");
		return html.toString();
	}
	
	@PostMapping(value = "/save", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public Ingredient save(@RequestBody Ingredient ingredient) {
		return service.save(ingredient);
	}
	
}
