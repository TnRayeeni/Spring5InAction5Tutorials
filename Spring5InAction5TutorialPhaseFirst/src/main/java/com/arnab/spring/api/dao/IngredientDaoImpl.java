package com.arnab.spring.api.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.arnab.spring.api.domain.Ingredient;

@Repository
public class IngredientDaoImpl implements IngredientDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static final String FIND_ALL_SQL = "SELECT ID, NAME, TYPE FROM INGREDIENTS";
	private static final String FIND_ONE_SQL = "SELECT ID, NAME, TYPE FROM INGREDIENTS WHERE ID=";
	private static final String SAVE_SQL = "INSERT INTO INGREDIENTS (ID, NAME, TYPE) VALUES (?, ?, ?)";
	
	@Override
	public Iterable<Ingredient> findAll() {
		return jdbcTemplate.query(FIND_ALL_SQL, (rs, num) -> new Ingredient(rs.getInt("ID"), rs.getString("NAME"), rs.getString("TYPE")));
	}

	@Override
	public Ingredient findOne(int id) {
		return jdbcTemplate.queryForObject(FIND_ONE_SQL + id, (rs, num) -> new Ingredient(rs.getInt("ID"), rs.getString("NAME"), rs.getString("TYPE")));
	}

	@Override
	public Ingredient save(Ingredient ingredient) {
		jdbcTemplate.update(SAVE_SQL, ingredient.getId(), ingredient.getName(), ingredient.getType());
		return ingredient;
	}

}
