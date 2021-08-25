package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpSession;
import java.util.List;
import com.example.model.WorkSchedule;
import com.example.service.WorkScheduleService;
import com.example.entity.WorkScheduleEntity;

@Controller
@ComponentScan("com.example.service")
public class ScheduleSearchController {
	@Autowired
	WorkScheduleService wsService;
	
	@Autowired
	HttpSession session;
	
	@RequestMapping(value = "scheduleSearch")
	public String scheduleSearch() {
		return "ScheduleSearch";
	}
	
	@RequestMapping(value = "scheduleResult", method = RequestMethod.POST)
	public String scheduleResult(@RequestParam(name = "nameKanji") String nameKanji, @RequestParam(name = "nameKana") String nameKana, 
			@RequestParam(name = "startYear") String startYear, @RequestParam(name = "startMonth") String startMonth, 
			@RequestParam(name = "startDay") String startDay, @RequestParam(name = "finishYear") String finishYear, 
			@RequestParam(name = "finishMonth") String finishMonth, @RequestParam(name = "finishDay") String finishDay, Model model) {
		List<WorkScheduleEntity> wsList = wsService.searchName(nameKanji, nameKanji, startYear, startMonth, startDay, 
				finishYear, finishMonth, finishDay);
		model.addAttribute("searchResult", wsList);
		return "ScheduleResult";
	}

}
