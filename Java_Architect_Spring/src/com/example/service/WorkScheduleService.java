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
	
	//�o�Η\����������郁�\�b�h�i�o�Η\����͋@�\�p�j
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
	
	//�o�Η\����X�V���郁�\�b�h
	public boolean updateStatus(String id, String date, int status) {
		boolean result = false;
		result = workScheduleDao.update(id, date, status);
		return result;
	}
	
	//�o�Η\����������郁�\�b�h�i�o�Η\�茟���@�\�p�j
	public List<WorkScheduleEntity> searchName(String nameKanji, String nameKana, String startYear, String startMonth, 
			String startDay, String finishYear, String finishMonth, String finishDay){
		//���Ɠ����ꌅ�̏ꍇ0��t��
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
		
		//���t�i�X�^�[�g�j��null�����邩�`�F�b�N
		if((startYear == null || startYear.length() == 0) && (startMonth == null || startMonth.length() == 0) && (startDay == null || startDay.length() == 0)) {
			startYear = finishYear;
			startMonth = finishMonth;
			startDay = finishDay;
		}
				
		//���t�i�I���j��null�͂Ȃ����`�F�b�N
		if((finishYear == null || finishYear.length() == 0) && (finishMonth == null || finishMonth.length() == 0) && (finishDay == null || finishDay.length() == 0)) {
			finishYear = startYear;
			finishMonth = startMonth;
			finishDay = startDay;
		}
		
		//�S�Ă̓��t�ɓ��͂��Ȃ��ꍇ
		String startDate = startYear + startMonth + startDay;
		String finishDate = finishYear + finishMonth + finishDay;
				
		if((startDate == null || startDate.length() == 0) && (finishDate == null || finishDate.length() == 0)) {
			//SimpleDateFormat
			SimpleDateFormat ysdf = new SimpleDateFormat("yyyy");
			SimpleDateFormat msdf = new SimpleDateFormat("MM");
			SimpleDateFormat dsdf = new SimpleDateFormat("dd");
					
			//���݂̔N�ƌ����擾
			Date date = new Date();
			startYear = finishYear = ysdf.format(date);
			startMonth = finishMonth = msdf.format(date);
			startDay = finishDay = dsdf.format(date);
		}
		List<WorkScheduleEntity> staffList = workScheduleDao.searchNa(nameKanji, nameKana, (startYear + startMonth + startDay), 
				(finishYear + finishMonth + finishDay));
		return staffList;
		
	}
	
	//Toppage�p���\�b�h�i�L�x�j
	public boolean message(String id, String year, String month) {
		boolean result = false;
		Date date = workScheduleDao.searchRest(id, year, month);
		if(date != null) {
			result = true;
		}
		return result;
	}

}
