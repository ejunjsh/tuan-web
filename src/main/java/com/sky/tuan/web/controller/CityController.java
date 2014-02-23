package com.sky.tuan.web.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.sky.tuan.mongodb.entity.City;
import com.sky.tuan.mongodb.repository.ICityRepository;
import com.sky.tuan.web.utils.CookieSupport;

@Controller
public class CityController extends MultiActionController{
	@Autowired
	private ICityRepository cityRepository;
	
	@RequestMapping("/ajax/getAllCity") 
    public String allCity(Model model)
    {
		List<City> cities=cityRepository.findAllWithSpell();
		model.addAttribute("cities", cities);
		List<String> letters=Arrays.asList("A","B","C","D","E","F","G","H","I","J","K","L"
			,"M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z");	
		model.addAttribute("letters", letters);
    	return "city.ftl";
    }
	
	@RequestMapping("/selectCity") 
	public String selectCity()
	{
		return "selectCity.jsp";
	}
	
	@RequestMapping("/selectCity/{name}") 
	public void selectCity(@PathVariable String name, HttpServletResponse response,HttpServletRequest request) throws IOException
	{
		CookieSupport.addCookie(response, "curCity",URLEncoder.encode(name,"UTF-8"), 10000);
		response.sendRedirect(request.getContextPath()+"/");
	}
}
