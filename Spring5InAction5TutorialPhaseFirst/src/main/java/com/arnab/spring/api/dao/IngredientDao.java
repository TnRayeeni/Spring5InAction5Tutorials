package com.arnab.spring.api.dao;

import com.arnab.spring.api.domain.Ingredient;

public interface IngredientDao {

	Iterable<Ingredient> findAll();
	
	Ingredient findOne(int id);
	
	Ingredient save(Ingredient ingredient);
	
}
