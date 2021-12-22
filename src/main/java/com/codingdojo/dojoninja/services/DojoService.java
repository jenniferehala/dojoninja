package com.codingdojo.dojoninja.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codingdojo.dojoninja.models.Dojo;
import com.codingdojo.dojoninja.repositories.DojoRepo;

@Service
public class DojoService {
	
	private final DojoRepo dojoRepo;
	
	public DojoService(DojoRepo dojoRepo) {
		this.dojoRepo = dojoRepo;
	}
	
	public List<Dojo> allDojos() {
		return dojoRepo.findAll();
		
	}
	

	public Dojo createDojo(Dojo d) {
		return dojoRepo.save(d);
		
	}
	
	public Dojo findDojoById(Long id) {
		Optional<Dojo> optionaldojo = dojoRepo.findById(id);
		if(optionaldojo.isPresent()) {
			return optionaldojo.get();
		} else {
			return null;
		}
	}


}
