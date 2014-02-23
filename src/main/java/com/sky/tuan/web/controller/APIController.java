package com.sky.tuan.web.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sky.tuan.mongodb.entity.City;
import com.sky.tuan.mongodb.entity.Deal;
import com.sky.tuan.mongodb.entity.Tag;
import com.sky.tuan.mongodb.repository.ICityRepository;
import com.sky.tuan.mongodb.repository.IDealRepository;
import com.sky.tuan.mongodb.repository.ITagRepository;
import com.sky.tuan.web.model.IndexModel;
import com.sky.tuan.web.utils.CookieSupport;
import com.sky.tuan.web.utils.Pagination;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

@Controller
public class APIController {


	@Autowired
	private ICityRepository cityRepository;
	
	@Autowired
	private IDealRepository dealRepository;
	
	@Autowired
	private ITagRepository tagRepository;
	
	@RequestMapping(value="/api/deals.xml",produces = "application/xml; charset=utf-8") 
	@ResponseBody
    public String deals(@ModelAttribute("m")IndexModel m,Model model,HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException
    {
		Pagination<Deal> deals=dealRepository.findBySeveral(m.getPage(),m.getSize(),false,m.getTag(),m.getCity(),m.getKey());
		
		XStream xstream = new XStream(new DomDriver());  
		xstream.alias("Deal", Deal.class);
		xstream.alias("City", City.class);
		xstream.alias("Tag", Tag.class);
		xstream.autodetectAnnotations(true);
		return xstream.toXML(deals.getData());
    }
	
	@RequestMapping(value="/api/cities.xml",produces = "application/xml; charset=utf-8") 
	@ResponseBody
    public String cities(Model model)
    {
		List<City> cities=cityRepository.findAllWithSpell();
		XStream xstream = new XStream(new DomDriver());  
		xstream.alias("Deal", Deal.class);
		xstream.alias("City", City.class);
		xstream.alias("Tag", Tag.class);
		xstream.autodetectAnnotations(true);
		return xstream.toXML(cities);
    }
	
	@RequestMapping(value="/api/tags.xml",produces = "application/xml; charset=utf-8") 
	@ResponseBody
    public String tags(Model model)
    {
		List<Tag> tags=tagRepository.findAllWithSpell();
		XStream xstream = new XStream(new DomDriver());  
		xstream.alias("Deal", Deal.class);
		xstream.alias("City", City.class);
		xstream.alias("Tag", Tag.class);
		xstream.autodetectAnnotations(true);
		return xstream.toXML(tags);
    }
	
	@RequestMapping(value="/api/hotTags.xml",produces = "application/xml; charset=utf-8") 
	@ResponseBody
    public String hotTags(Model model)
    {
		List<Tag> tags=tagRepository.findHotTag(10);
		XStream xstream = new XStream(new DomDriver());  
		xstream.alias("Deal", Deal.class);
		xstream.alias("City", City.class);
		xstream.alias("Tag", Tag.class);
		xstream.autodetectAnnotations(true);
		return xstream.toXML(tags);
    }
	
	@RequestMapping(value="/api/hotCities.xml",produces = "application/xml; charset=utf-8") 
	@ResponseBody
    public String hotCities(Model model)
    {
		List<City> cities=cityRepository.findHotCity(10);
		XStream xstream = new XStream(new DomDriver());  
		xstream.alias("Deal", Deal.class);
		xstream.alias("City", City.class);
		xstream.alias("Tag", Tag.class);
		xstream.autodetectAnnotations(true);
		return xstream.toXML(cities);
    }
}
