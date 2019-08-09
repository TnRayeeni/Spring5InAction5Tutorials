package com.arnab.spring.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arnab.spring.api.dao.IngredientDao;
import com.arnab.spring.api.domain.Ingredient;

@Service
public class IngredientService {

	@Autowired
	private IngredientDao dao;
	
	public List<Ingredient> findAll() {
		List<Ingredient> list = new ArrayList<>();
		dao.findAll().forEach(element -> list.add(element));
		return list;
	}
	
	public Ingredient findOne(int id) {
		return dao.findOne(id);
	}
	
	public Ingredient save(Ingredient ingredient) {
		return dao.save(ingredient);
	}
	
}
