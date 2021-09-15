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
import com.example.model.WorkResult;
import com.example.service.WorkResultService;
import com.example.entity.WorkResultEntity;
import com.example.entity.StaffEntity;

@Controller
@ComponentScan("com.example.service")
public class WorkResultController {
	@Autowired
	WorkResultService wrService;
	
	@Autowired
	HttpSession session;
	
	@RequestMapping(value = "resultInput")
	public String resultInput(Model model) {
		StaffEntity staffEntity = (StaffEntity) session.getAttribute("staffEntity");
		Date date = new Date();
		SimpleDateFormat ysdf = new SimpleDateFormat("yyyy");
		SimpleDateFormat msdf = new SimpleDateFormat("MM");
		List<WorkResultEntity> wrList = wrService.searchResult(staffEntity.getId(), ysdf.format(date), msdf.format(date));
		session.setAttribute("WorkResult", wrList);
		return "WorkRecord";
	}
	
	@RequestMapping(value = "resultInput", method = RequestMethod.POST)
	public String resultInputPost(@RequestParam(name = "year") String year, @RequestParam(name = "month") String month, 
			@RequestParam(name = "page") String page, @RequestParam(name = "status") List<String> status, 
			@RequestParam(name = "timeStart") List<String> timeStartList, @RequestParam(name = "timeFinish") List<String> timeFinishList) {
		StaffEntity staffEntity = (StaffEntity) session.getAttribute("staffEntity");
		if(page.equals("change")) {
			List<WorkResultEntity> wrList = wrService.searchResult(staffEntity.getId(), year, month);
			session.setAttribute("WorkResult", wrList);
		}
		if(page.equals("register")) {
			List<WorkResultEntity> wrList = (List<WorkResultEntity>) session.getAttribute("WorkResult");
			String timeStart = null;
			String timeFinish = null;
			for(int i = 0; i < status.size(); i++) {
				WorkResultEntity wrEntity = wrList.get(i);
				if((wrEntity.getStatus() != Integer.parseInt(status.get(i))) ||
						(wrEntity.getTimeStart() != Integer.parseInt(timeStartList.get(i))) ||
						(wrEntity.getTimeFinish() != Integer.parseInt(timeFinishList.get(i)))) {
					//timeStartListの先頭文字のチェック
					if(timeStartList.get(i).startsWith("0")) {
						timeStart = timeStartList.get(i).substring(1,4);
					}else {
						timeStart = timeStartList.get(i);
					}
					
					//timeFinishListの先頭文字のチェック
					if(timeFinishList.get(i).startsWith("0")) {
						timeFinish = timeFinishList.get(i).substring(1,4);
					}else {
						timeFinish = timeFinishList.get(i);
					}
					
					//勤務実績の変更
					wrService.updateResult(staffEntity.getId(), wrEntity.getDate().toString(), Integer.parseInt(status.get(i)), 
							Integer.parseInt(timeStart), Integer.parseInt(timeFinish));
				}
			}
			WorkResultEntity wr = wrList.get(0);
			SimpleDateFormat ysdf = new SimpleDateFormat("yyyy");
			SimpleDateFormat msdf = new SimpleDateFormat("MM");
			List<WorkResultEntity> wrEntityList = wrService.searchResult(staffEntity.getId(), ysdf.format(wr.getDate()), msdf.format(wr.getDate()));
			session.setAttribute("WorkResult", wrEntityList);
		}
		return "WorkRecord";
	}

}
