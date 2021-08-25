package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;
import com.example.model.Staff;
import com.example.service.StaffService;
import com.example.service.WorkScheduleService;
import com.example.entity.StaffEntity;

@Controller
@ComponentScan("com.example.service")
public class LoginController {
	
	@Autowired
	StaffService staffService;
	
	@Autowired
	HttpSession session;
	
	@Autowired
	WorkScheduleService wsService;
	
	/*
	@ModelAttribute
	public Staff setUpStaff() {
		Staff staff = new Staff();
		return staff;
	}
	*/
	
	@RequestMapping(value = "login")
	public String login() {
		return "Login";
	}
	
	
	@RequestMapping(value = "toppage", method = RequestMethod.POST)
	public String topPage(@RequestParam(name = "id") String id, @RequestParam(name = "password") String password, 
			Model model) {
		StaffEntity staffEntity = staffService.searchStaff(id, password);
		if(staffEntity == null) {
			model.addAttribute("LoginError", "ID�܂��̓p�X���[�h���Ⴂ�܂�");
			return "Login";
		}
		
		//���b�Z�[�W�o��
		Date today = new Date();
		SimpleDateFormat ysdf = new SimpleDateFormat("yyyy");
		SimpleDateFormat msdf = new SimpleDateFormat("MM");
		System.out.println("a");
		boolean result = wsService.message(staffEntity.getId(), ysdf.format(today), msdf.format(today));
		if(!result) {
			model.addAttribute("restMsg", msdf.format(today) + "���̗L�x�擾�\�����͂��Ă��������B");
		}
		
		
		//�Z�b�V������id��namekanji��o�^
		session.setAttribute("staffEntity", staffEntity);
		//model.addAttribute("nameKanji", staffEntity.getNameKanji());
		return "TopPage";
	}
	
	@RequestMapping(value = "toppage")
	public String topPageGet(Model model) {
		StaffEntity staffEntity = (StaffEntity) session.getAttribute("staffEntity");
		Date today = new Date();
		SimpleDateFormat ysdf = new SimpleDateFormat("yyyy");
		SimpleDateFormat msdf = new SimpleDateFormat("MM");
		
		//�o�Η\��ɗL�x�����f����Ă��邩�`�F�b�N
		boolean result = wsService.message(staffEntity.getId(), ysdf.format(today), msdf.format(today));
		if(!result) {
			model.addAttribute("restMsg", msdf.format(today) + "���̗L�x�擾�\�����͂��Ă��������B");
		}
		return "TopPage";
		
	}
	

}
