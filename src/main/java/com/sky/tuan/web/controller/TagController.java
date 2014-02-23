package com.sky.tuan.web.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.sky.tuan.mongodb.entity.Tag;
import com.sky.tuan.mongodb.repository.ITagRepository;

@Controller
public class TagController extends MultiActionController{
	@Autowired
	private ITagRepository tagRepository;
	
	@RequestMapping("/ajax/getAllTag") 
    public String allTag(Model model)
    {
		List<Tag> tags=tagRepository.findAllWithSpell();
		model.addAttribute("tags", tags);
		List<String> letters=Arrays.asList("A","B","C","D","E","F","G","H","I","J","K","L"
			,"M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z");	
		model.addAttribute("letters", letters);
    	return "tag.ftl";
    }
}
