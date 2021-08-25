package com.example.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.example.entity.WorkScheduleEntity;

@Repository
public class WorkScheduleDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//出勤予定の検索
	public List<WorkScheduleEntity> searchSt(String id, String year, String month){
		String sql = "SELECT * FROM work_schedule LEFT OUTER JOIN work_status ON work_schedule.status=work_status.status "
				+ "WHERE work_schedule.id=? AND date LIKE ?";
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql, id, generateDate(year, month));
		List<WorkScheduleEntity> wsList = new ArrayList<WorkScheduleEntity>();
		for(Map<String, Object> result: resultList) {
			WorkScheduleEntity wsEntity = new WorkScheduleEntity();
			wsEntity.setId((String) result.get("id"));
			wsEntity.setDate((Date) result.get("date"));
			if(result.get("status") != null) {
				wsEntity.setStatus((int) result.get("status"));
			}
			//wsEntity.setStatus((int) result.get("status"));
			wsEntity.setStatusName((String) result.get("status_name"));
			wsList.add(wsEntity);
		}
		return wsList;
	}
	
	//出勤予定の更新
	public boolean update(String id, String date, int status) {
		boolean result = false;
		String sql = "UPDATE work_schedule SET status=? WHERE id=? and date=?";
		jdbcTemplate.update(sql, status, id, date);
		result = true;
		return result;
	}
	
	//出勤予定検索機能用メソッド
	public List<WorkScheduleEntity> searchNa(String nameKanji, String nameKana, String startDate, String finishDate){
		String sql = "Select date, name_kanji, name_kana, status_name FROM work_schedule "
				+ "LEFT OUTER JOIN staff ON work_schedule.id=staff.id "
				+ "LEFT OUTER JOIN work_status ON work_schedule.status=work_status.status "
				+ "WHERE name_kanji LIKE ? AND name_kana LIKE ? AND date BETWEEN ? AND ?";
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql, nameKanji, nameKana, startDate, finishDate);
		List<WorkScheduleEntity> staffList = new ArrayList<WorkScheduleEntity>();
		for(Map<String, Object> result: resultList) {
			WorkScheduleEntity wsEntity = new WorkScheduleEntity();
			wsEntity.setDate((Date) result.get("date"));
			wsEntity.setNameKanji((String) result.get("name_kanji"));
			wsEntity.setNameKana((String)result.get("name_kana"));
			wsEntity.setStatusName((String) result.get("status_name"));
			staffList.add(wsEntity);
		}
		return staffList;
	}
	
	//Toppage出力用メソッド（有休）
	public Date searchRest(String id, String year, String month) {
		String sql = "SELECT date FROM work_schedule WHERE id=? and date LIKE ? AND status=5";
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql, id, generateDate(year, month));
		Date date = null;
		if(resultList.size() != 0) {
			Map<String, Object> result = resultList.get(0);
			date = (Date) result.get("date");
		}
		/*
		Map<String, Object> result = resultList.get(0);
		date = (Date) result.get("date");
		*/
		return date;
	}
	
	//Date型文字列の生成メソッド
		public String generateDate(String year, String month) {
			return year + "-" + month + "-%";
		}

}
