package com.codingdojo.dojoninja.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codingdojo.dojoninja.models.Dojo;
import com.codingdojo.dojoninja.models.Ninja;
import com.codingdojo.dojoninja.services.DojoService;
import com.codingdojo.dojoninja.services.NinjaService;

@Controller
public class HomeController {
	
	@Autowired
	DojoService dojoService;
	
	@Autowired
	NinjaService ninjaService;
	
	@RequestMapping("/dojos/new")
	public String Dojoform(Model Model, @ModelAttribute("dojos") Dojo dojo) {
	return "newdojo.jsp"; 
	}
	
	@PostMapping("/newdojo")
	public String createDojo(@Valid @ModelAttribute("dojos") Dojo dojo,
			BindingResult result) {
		if(result.hasErrors()) {
			return "newdojo.jsp";
		} else {
			dojoService.createDojo(dojo);
			return "redirect:/ninjas/new";
			
		}
		
	}
	
	@RequestMapping("/ninjas/new")
	public String Ninjaform(Model model, @ModelAttribute("ninjas") Ninja ninja) {
		model.addAttribute("dojos", dojoService.allDojos());
	return "newninja.jsp"; 
	}
	
	
	@PostMapping("/newninja")
	public String createNinja(Model model, @Valid @ModelAttribute("ninjas") Ninja ninja,
			BindingResult result) {
		if(result.hasErrors()) {
			model.addAttribute("dojos", dojoService.allDojos());		
			return "newninja.jsp";
		} else { 
			
			ninjaService.createNinja(ninja);
			Dojo dojo = ninja.getDojo();
			Long id = dojo.getId();
			return "redirect:/dojos/" + id;
			
		}
	}
	
	
	@RequestMapping("/dojos/{id}")
	public String DojoNinjas(Model model, @PathVariable Long id) {
		Dojo dojo = dojoService.findDojoById(id);
		model.addAttribute("dojo", dojo);
		return "dojoninjas.jsp"; 
	}
		
}
