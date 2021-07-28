package com.example.AppCompany.Company;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.AppCompany.IPO.IPODetail;
import com.example.AppCompany.Sector.Sector;
import com.example.AppCompany.Sector.SectorService;
import com.example.AppCompany.Stock.Companystockexchangemap;
import com.example.AppCompany.Stock.Stockexchange;

@Service
public class CompanyService {
	
	@Autowired
	CompanyRepository companyRepo;
	
	@Autowired
	SectorService sectorService;
	
	public Company saveCompany(UpdateCompany req) {
		Company c = new Company(req.getCompany());
		try {
			Sector s = sectorService.getSectorById(req.getSectorId());
			c.setSector(s);
		} catch (Exception e) {
			System.out.println(e);
		}
		return companyRepo.save(c);
	}
	
	public List<Company> getAllCompanies() {
		return companyRepo.findAll();
	}
	
	public Company getCompanyByName(String name) {
		return companyRepo.findByName(name);
	}
	
	public Sector getCompanySectorByName(String name) {
		Company c = companyRepo.findByName(name);
		return new Sector(c.getSector());
	}
	
	public List<Stockexchange> getCompanyStockExchangeByName(String name) {
		Company c = companyRepo.findByName(name);
		List<Stockexchange> sel = new ArrayList<Stockexchange>();
		List<Companystockexchangemap> map = c.getCompstockmap();
		for (Companystockexchangemap companystockexchangemap : map) {
			sel.add(new Stockexchange(companystockexchangemap.getStockexchange()));
		}
		return sel;
	}
	
	public IPODetail getCompanyIPOByName(String name) {
		Company c = companyRepo.findByName(name);
		return c.getIpo();
	}


	
}
