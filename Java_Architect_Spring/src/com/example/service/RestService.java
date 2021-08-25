package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import com.example.entity.RestEntity;
import com.example.dao.RestDao;

@Service
@ComponentScan("com.example.dao")
public class RestService {
	@Autowired
	private RestDao restDao;
	
	public RestEntity searchRest(String id, int year) {
		return restDao.search(id, year);
	}

}
