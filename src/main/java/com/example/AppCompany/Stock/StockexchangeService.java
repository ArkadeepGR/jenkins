package com.example.AppCompany.Stock;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.AppCompany.Company.Company;

@Service
public class StockexchangeService {
	
	@Autowired
	StockexchangeRepository stockexchangeRepo;
	
	public Stockexchange saveStockexchange(Stockexchange req) {
		return stockexchangeRepo.save(req);
	}
	
	public List<Stockexchange> getAllStockexchanges(){
		return stockexchangeRepo.findAll();
	}
	
	public Stockexchange getStockexchangeById(long id) {
		return stockexchangeRepo.findById(id).get();
	}
	
	public Stockexchange getStockexchangeByName(String name) {
		return stockexchangeRepo.findByName(name);
	}
	
	
	public List<Company> getStockexchangeCompaniesById(long id){
		Stockexchange s= stockexchangeRepo.findById(id).get();
		List<Company> cl = new ArrayList<Company>();
		List<Companystockexchangemap> map = s.getCompstockmap();
		for (Companystockexchangemap companystockexchangemap : map) {
			cl.add(new Company(companystockexchangemap.getCompany()));
		}
		return cl;
	}
	
	
	
	public List<Company> getStockexchangeCompaniesByName(String name){
		Stockexchange s= stockexchangeRepo.findByName(name);
		List<Company> cl = new ArrayList<Company>();
		List<Companystockexchangemap> map = s.getCompstockmap();
		for (Companystockexchangemap companystockexchangemap : map) {
			cl.add(new Company(companystockexchangemap.getCompany()));
		}
		return cl;
	}
	
}
