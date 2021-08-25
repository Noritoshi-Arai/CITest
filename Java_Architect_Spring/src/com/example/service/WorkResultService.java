package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.entity.WorkResultEntity;
import com.example.dao.WorkResultDao;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;

@Service
public class WorkResultService {
	@Autowired
	private WorkResultDao workResultDao;
	
	//勤務実績を検索するメソッド
	public List<WorkResultEntity> searchResult(String id, String year, String month){
		List<WorkResultEntity> wrList = workResultDao.search(id, year, month);
		return wrList;
	}
	
	//勤務実績を更新するメソッド
	public boolean updateResult(String id, String date, int status, int timeStart, int timeFinish) {
		boolean result = false;
		result = workResultDao.update(id, date, status, timeStart, timeFinish);
		return result;
	}

}
