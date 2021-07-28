package com.example.AppCompany.Controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.AppCompany.Stock.Companystockexchangemap;
import com.example.AppCompany.Stock.CompanystockexchangemapRepository;

@RestController
public class MainController {

	@Autowired
	CompanystockexchangemapRepository mapRepo;
	
	@GetMapping("/complete")
	public List<AllDTO> allCompanyStock() {
		List<Companystockexchangemap> all = mapRepo.findAll();
		List<AllDTO> comp = new ArrayList<AllDTO>();
		for (Companystockexchangemap cse : all) {
			comp.add(new AllDTO(cse));
		}
		return comp;
	}
	
}
