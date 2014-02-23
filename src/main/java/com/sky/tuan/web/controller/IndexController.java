package com.sky.tuan.web.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.sky.tuan.mongodb.entity.City;
import com.sky.tuan.mongodb.entity.Deal;
import com.sky.tuan.mongodb.entity.Tag;
import com.sky.tuan.mongodb.repository.ICityRepository;
import com.sky.tuan.mongodb.repository.IDealRepository;
import com.sky.tuan.mongodb.repository.ITagRepository;
import com.sky.tuan.web.model.IndexModel;
import com.sky.tuan.web.utils.CookieSupport;
import com.sky.tuan.web.utils.Pagination;


@Controller
public class IndexController extends MultiActionController {
	
	@Autowired
	private ICityRepository cityRepository;
	
	@Autowired
	private IDealRepository dealRepository;
	
	@Autowired
	private ITagRepository tagRepository;
	
	@RequestMapping("/") 
    public String all(@ModelAttribute("m")IndexModel m,Model model,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException
    {
		String curCity=URLDecoder.decode( CookieSupport.getCookieByName(request,"curCity").getValue(), "UTF-8");
		Pagination<Deal> deals=dealRepository.findBySeveral(m.getPage(),m.getSize(),false,m.getTag(),curCity,m.getKey());
		
		List<City> hotCity=cityRepository.findHotCity(15);
		
		List<Tag> hotTag=tagRepository.findHotTag(15);
    	model.addAttribute("deals",deals.getData());
    	model.addAttribute("recordCount",deals.getTotalCount());
    	model.addAttribute("pageNo",m.getPage());
    	model.addAttribute("pageSize",m.getSize());
    	model.addAttribute("curCity",curCity);
    	model.addAttribute("curTag",m.getTag());
    	model.addAttribute("hotCity",hotCity);
    	model.addAttribute("hotTag",hotTag);
    	model.addAttribute("curSearchKey",m.getKey());
		return "index.jsp";
    }
	
	@RequestMapping("/{tag:[0-9A-Za-z\u4e00-\u9fa5]+}") 
    public String tag(@ModelAttribute("m")IndexModel m,Model model,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException
    {
		String curCity=URLDecoder.decode( CookieSupport.getCookieByName(request,"curCity").getValue(), "UTF-8");
		Pagination<Deal> deals=dealRepository.findBySeveral(m.getPage(),m.getSize(),false,m.getTag(),curCity,m.getKey());
		
		List<City> hotCity=cityRepository.findHotCity(15);
		
		List<Tag> hotTag=tagRepository.findHotTag(15);
    	model.addAttribute("deals",deals.getData());
    	model.addAttribute("recordCount",deals.getTotalCount());
    	model.addAttribute("pageNo",m.getPage());
    	model.addAttribute("pageSize",m.getSize());
    	model.addAttribute("curCity",curCity);
    	model.addAttribute("curTag",m.getTag());
    	model.addAttribute("hotCity",hotCity);
    	model.addAttribute("hotTag",hotTag);
    	model.addAttribute("curSearchKey",m.getKey());
		return "index.jsp";
    }
	
	@RequestMapping("/favicon.ico") 
    public void favicon()
    {
		
    }
	
	@RequestMapping("/today") 
    public String today(Model model)
    {
		Pagination<Deal> deals=dealRepository.findBySeveral(1,48,false,"美食","广州",null);
    	model.addAttribute("deals",deals.getData());
    	model.addAttribute("recordCount",deals.getTotalCount());
    	model.addAttribute("curPage", "today");
		return "index.jsp";
    }

}
