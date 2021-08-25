package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.entity.WorkScheduleEntity;
import com.example.dao.WorkScheduleDao;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;

@Service
public class WorkScheduleService {
	@Autowired
	private WorkScheduleDao workScheduleDao;
	
	//出勤予定を検索するメソッド（出勤予定入力機能用）
	public List<WorkScheduleEntity> searchStatus(String id, String year, String month){
		/*
		if((year == null || year.length() == 0) && (month == null || month.length() == 0)) {
			SimpleDateFormat ysdf = new SimpleDateFormat("yyyy");
			SimpleDateFormat msdf = new SimpleDateFormat("MM");
			Date date = new Date();
			year = ysdf.format(date);
			month = msdf.format(date);
		}
		*/
		
		List<WorkScheduleEntity> wsList = workScheduleDao.searchSt(id, year, month);
		return wsList;
	}
	
	//出勤予定を更新するメソッド
	public boolean updateStatus(String id, String date, int status) {
		boolean result = false;
		result = workScheduleDao.update(id, date, status);
		return result;
	}
	
	//出勤予定を検索するメソッド（出勤予定検索機能用）
	public List<WorkScheduleEntity> searchName(String nameKanji, String nameKana, String startYear, String startMonth, 
			String startDay, String finishYear, String finishMonth, String finishDay){
		//月と日が一桁の場合0を付加
		if(startMonth.length() == 1) {
			startMonth = "0" + startMonth;
		}
		if(startDay.length() == 1) {
			startDay = "0" + startDay;
		}
		if(finishMonth.length() == 1) {
			finishMonth = "0" + finishMonth;
		}
		if(finishDay.length() == 1) {
			finishDay = "0" + finishDay;
		}
		
		//日付（スタート）にnullがあるかチェック
		if((startYear == null || startYear.length() == 0) && (startMonth == null || startMonth.length() == 0) && (startDay == null || startDay.length() == 0)) {
			startYear = finishYear;
			startMonth = finishMonth;
			startDay = finishDay;
		}
				
		//日付（終わり）にnullはないかチェック
		if((finishYear == null || finishYear.length() == 0) && (finishMonth == null || finishMonth.length() == 0) && (finishDay == null || finishDay.length() == 0)) {
			finishYear = startYear;
			finishMonth = startMonth;
			finishDay = startDay;
		}
		
		//全ての日付に入力がない場合
		String startDate = startYear + startMonth + startDay;
		String finishDate = finishYear + finishMonth + finishDay;
				
		if((startDate == null || startDate.length() == 0) && (finishDate == null || finishDate.length() == 0)) {
			//SimpleDateFormat
			SimpleDateFormat ysdf = new SimpleDateFormat("yyyy");
			SimpleDateFormat msdf = new SimpleDateFormat("MM");
			SimpleDateFormat dsdf = new SimpleDateFormat("dd");
					
			//現在の年と月を取得
			Date date = new Date();
			startYear = finishYear = ysdf.format(date);
			startMonth = finishMonth = msdf.format(date);
			startDay = finishDay = dsdf.format(date);
		}
		List<WorkScheduleEntity> staffList = workScheduleDao.searchNa(nameKanji, nameKana, (startYear + startMonth + startDay), 
				(finishYear + finishMonth + finishDay));
		return staffList;
		
	}
	
	//Toppage用メソッド（有休）
	public boolean message(String id, String year, String month) {
		boolean result = false;
		Date date = workScheduleDao.searchRest(id, year, month);
		if(date != null) {
			result = true;
		}
		return result;
	}

}
