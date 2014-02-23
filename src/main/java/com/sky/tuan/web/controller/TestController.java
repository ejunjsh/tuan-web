package com.sky.tuan.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.sky.tuan.mongodb.entity.City;
import com.sky.tuan.mongodb.repository.ICityRepository;


@Controller
public class TestController extends AbstractController {
    
	@Autowired
	private ICityRepository cityRepository;
	
	@RequestMapping("/test/jsp") 
	public String jsp(@ModelAttribute("city")City city) throws Exception {
  
		String k=city.getName();
		return "test.jsp";
	}
	
	@RequestMapping("/test/ftl") 
	public String ftl() throws Exception {

		return "test.ftl";
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
