package com.codingdojo.dojoninja.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.codingdojo.dojoninja.models.Ninja;
import com.codingdojo.dojoninja.repositories.NinjaRepo;

@Service
public class NinjaService {

	
	private final NinjaRepo ninjaRepo;
	
	public NinjaService(NinjaRepo ninjaRepo) {
		this.ninjaRepo = ninjaRepo;
	}
	
	public List<Ninja> allNinjas() {
		return ninjaRepo.findAll();
		
	}
	
	public Ninja createNinja(Ninja n) {
		return ninjaRepo.save(n);
		
	}
}
