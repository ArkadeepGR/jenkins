package com.example.AppCompany.Stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.AppCompany.Company.Company;
import com.example.AppCompany.Company.CompanyRepository;
import com.example.AppCompany.Company.CompanyService;

@RestController
@CrossOrigin
public class CompanystockexchangemapController {
	
	@Autowired
	CompanyService companyServcie;
	
	@Autowired
	StockexchangeService stockexchangeService;
	
	@Autowired
	CompanystockexchangemapRepository companystockexchangemapRepository;
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@PostMapping("/map")
	public Companystockexchangemap mapCompanyStockexchange(@RequestBody MapData map){
		Company c= companyServcie.getCompanyByName(map.getCompanyname()); 
		Stockexchange s= stockexchangeService.getStockexchangeByName(map.getStockexchangename()); 
		Companystockexchangemap csm=new Companystockexchangemap();
		csm.setCompany(c);
		csm.setStockexchange(s);
		csm.setCompanyCode(map.getCompanyCode());
		return companystockexchangemapRepository.save(csm);
	}
}
