package com.example.AppCompany.Stock;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.AppCompany.Company.Company;

@RestController
@CrossOrigin
public class StockexchangeController {
	
	@Autowired
	StockexchangeService stockexchangeService;
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@PostMapping("/stockexchanges")
	public Stockexchange addNewStockexchnange(@RequestBody Stockexchange req) {
		return stockexchangeService.saveStockexchange(req);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@PatchMapping("/stockexchanges")
	public Stockexchange updateStockexchnange(@RequestBody Stockexchange req) {
		return stockexchangeService.saveStockexchange(req);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
	@GetMapping("/stockexchanges")
	public List<Stockexchange> allStockexchanges(){
		return stockexchangeService.getAllStockexchanges();
	}
	
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
	@GetMapping("/stockexchanges/{id}")
	public Stockexchange stockexchangeeById(@PathVariable long id){
		return stockexchangeService.getStockexchangeById(id);
	}
	
	@GetMapping("/stockexchanges/name/{name}")
	public Stockexchange stockexchangeeById(@PathVariable String name){
		return stockexchangeService.getStockexchangeByName(name);
	}
	
	
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
	@GetMapping("/stockexchanges/companies/{id}")
	public List<Company> stockexchangeCompaniesById(@PathVariable long id){
		return stockexchangeService.getStockexchangeCompaniesById(id);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
	@GetMapping("/stockexchanges/companies/name/{name}")
	public List<Company> stockexchangeCompaniesById(@PathVariable String name){
		return stockexchangeService.getStockexchangeCompaniesByName(name);
	}
	
}
