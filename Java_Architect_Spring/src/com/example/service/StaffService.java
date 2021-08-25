package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.entity.StaffEntity;
import com.example.dao.StaffDao;
import java.util.List;

@Service
public class StaffService {
	
	@Autowired
	private StaffDao staffDao;
	
	public StaffEntity searchStaff(String id, String password) {
		List<StaffEntity> staffList = staffDao.search(id, password);
		StaffEntity staff = new StaffEntity();
		if(staffList.size() == 1) {
			staff = staffList.get(0);
		}else {
			staff = null;
		}
		return staff;
	}
}
