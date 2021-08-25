package com.example.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.example.entity.RestEntity;
import java.util.Map;

@Repository
public class RestDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public RestEntity search(String id, int year) {
		String sql = "SELECT id, year, possible_rest, must_rest FROM rest WHERE id = ? AND year = ?";
		Map<String, Object> result = jdbcTemplate.queryForMap(sql, id, year);
		RestEntity restEntity = new RestEntity();
		restEntity.setId((String) result.get("id"));
		restEntity.setYear((int) result.get("year"));
		restEntity.setPossibleRest((int) result.get("possible_rest"));
		restEntity.setMustRest((int) result.get("must_rest"));
		return restEntity;
	}

}
