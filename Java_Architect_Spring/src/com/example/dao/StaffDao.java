package com.example.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.example.entity.StaffEntity;

@Repository
public class StaffDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<StaffEntity> search(String id, String password) {
		String sql = "SELECT id, name_kanji, name_kana, password, flag FROM staff WHERE id = ? AND password = ?";
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql, id, password);
		List<StaffEntity> staffList = new ArrayList<StaffEntity>();
		for(Map<String, Object> result: resultList) {
			StaffEntity staffEntity = new StaffEntity();
			staffEntity.setId((String) result.get("id"));
			staffEntity.setNameKanji((String) result.get("name_kanji"));
			staffEntity.setNameKana((String) result.get("name_kana"));
			staffEntity.setPassword((String) result.get("password"));
			staffEntity.setFlag((boolean) result.get("flag"));
			staffList.add(staffEntity);
		}
		return staffList;
	}

}
