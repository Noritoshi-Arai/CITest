package com.example.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.example.entity.WorkResultEntity;

@Repository
public class WorkResultDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//勤務実績の検索
	public List<WorkResultEntity> search(String id, String year, String month){
		String sql = "SELECT * FROM work_result LEFT OUTER JOIN work_status ON "
				+ "work_result.status=work_status.status WHERE work_result.id=? AND date LIKE ?";
		
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql, id, generateDate(year, month));
		List<WorkResultEntity> wrList = new ArrayList<WorkResultEntity>();
		for(Map<String, Object> result: resultList) {
			WorkResultEntity wrEntity = new WorkResultEntity();
			wrEntity.setId((String) result.get("id"));
			wrEntity.setDate((Date) result.get("date"));
			if(result.get("status") != null) {
				wrEntity.setStatus((int) result.get("status"));
			}
			wrEntity.setStatusName((String) result.get("status_name"));
			if(result.get("time_start") != null) {
				wrEntity.setTimeStart((int) result.get("time_start"));
			}
			if(result.get("time_finish") != null) {
				wrEntity.setTimeFinish((int) result.get("time_finish"));
			}
			wrList.add(wrEntity);
		}
		return wrList;
	}
	
	//勤務実績の更新
	public boolean update(String id, String date, int status, int timeStart, int timeFinish) {
		boolean result = false;
		String sql = "UPDATE work_result SET status=?, time_start=?, time_finish=? WHERE id=? and date=?";
		jdbcTemplate.update(sql, status, timeStart, timeFinish, id, date);
		result = true;
		return result;
	}
	
	//Date型文字列の生成メソッド
	public String generateDate(String year, String month) {
		return year + "-" + month + "-%";
	}

}
