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
import java.util.Date;
import java.text.SimpleDateFormat;
import com.example.model.WorkSchedule;
import com.example.service.WorkScheduleService;
import com.example.entity.WorkScheduleEntity;
import com.example.entity.StaffEntity;

@Controller
@ComponentScan("com.example.service")
public class WorkScheduleController {
	@Autowired
	WorkScheduleService wsService;
	
	@Autowired
	HttpSession session;
	
	@RequestMapping(value = "scheduleInput")
	public String scheduleInput(Model model) {
		StaffEntity staffEntity = (StaffEntity) session.getAttribute("staffEntity");
		Date date = new Date();
		SimpleDateFormat ysdf = new SimpleDateFormat("yyyy");
		SimpleDateFormat msdf = new SimpleDateFormat("MM");
		List<WorkScheduleEntity> wsList = wsService.searchStatus(staffEntity.getId(), ysdf.format(date), msdf.format(date));
		session.setAttribute("WorkSchedule", wsList);
		return "ScheduleInput";
	}
	
	@RequestMapping(value = "scheduleInput", method = RequestMethod.POST)
	public String scheduleInputChange(@RequestParam(name = "year") String year, @RequestParam(name = "month") String month, 
			@RequestParam(name = "page") String page, @RequestParam(name = "status") List<String> status) {
		StaffEntity staffEntity = (StaffEntity) session.getAttribute("staffEntity");
		if(page.equals("change")) {
			List<WorkScheduleEntity> wsList = wsService.searchStatus(staffEntity.getId(), year, month);
			session.setAttribute("WorkSchedule", wsList);
		}
		if(page.equals("register")) {
			List<WorkScheduleEntity> wsList = (List<WorkScheduleEntity>) session.getAttribute("WorkSchedule");
			for(int i = 0; i < status.size(); i++) {
				WorkScheduleEntity wsEntity = wsList.get(i);
				if(wsEntity.getStatus() != Integer.parseInt(status.get(i))) {
					wsService.updateStatus(staffEntity.getId(), wsEntity.getDate().toString(), Integer.parseInt(status.get(i)));
				}
			}
			WorkScheduleEntity ws = wsList.get(0);
			SimpleDateFormat ysdf = new SimpleDateFormat("yyyy");
			SimpleDateFormat msdf = new SimpleDateFormat("MM");
			List<WorkScheduleEntity> wsEntityList = wsService.searchStatus(staffEntity.getId(), ysdf.format(ws.getDate()), msdf.format(ws.getDate()));
			session.setAttribute("WorkSchedule", wsEntityList);
			/*
			System.out.println("status[0]" + status.get(0));
			System.out.println("status[1]" + status.get(1));
			System.out.println("status[0]" + status.size());
			*/
		}
		return "ScheduleInput";
	}
	
	/*
	@RequestMapping(value = "scheduleInput", method = RequestMethod.POST)
	public String scheduleInputRegister(@RequestParam(name = "page") String page, @RequestParam(name = "status") String status) {
		System.out.println("page" + page);
		System.out.println("status" + status);
		return "ScheduleInput";
	}
	*/

}
