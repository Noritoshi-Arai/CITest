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
import java.util.Date;
import java.text.SimpleDateFormat;
import com.example.model.Rest;
import com.example.service.RestService;
import com.example.entity.StaffEntity;
import com.example.entity.RestEntity;

@Controller
@ComponentScan("com.example.service")
public class RestController {
	@Autowired
	RestService restService;
	
	@Autowired
	HttpSession session;
	
	@ModelAttribute
	public Rest setUpRest() {
		Rest rest = new Rest();
		return rest;
	}
	
	//リンクからアクセスされる場合
	@RequestMapping(value = "rest")
	public String rest(Model model) {
		Date date = new Date();
		SimpleDateFormat ysdf = new SimpleDateFormat("yyyy");
		
		StaffEntity staffEntity = (StaffEntity) session.getAttribute("staffEntity");
		RestEntity restEntity = restService.searchRest(staffEntity.getId(), Integer.parseInt(ysdf.format(date)));
		model.addAttribute("rest", restEntity);
		return "Rest";
	}
	
	//日付変更からアクセスされる場合
	@RequestMapping(value = "rest", method = RequestMethod.POST)
	public String restPost(@RequestParam(name = "year") String year, Model model) {
		StaffEntity staffEntity = (StaffEntity) session.getAttribute("staffEntity");
		RestEntity restEntity = restService.searchRest(staffEntity.getId(), Integer.parseInt(year));
		model.addAttribute("rest", restEntity);
		return "Rest";
	}

}
