package com.example.AppCompany.Controller;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.AppCompany.Company.Company;
import com.example.AppCompany.Stock.Companystockexchangemap;
import com.example.AppCompany.Stock.Stockexchange;

public class AllDTO {
	Company company;
	Stockexchange stockExcahnge;
	String CompanyCode;
	
	
	
	public Company getCompany() {
		return company;
	}



	public void setCompany(Company company) {
		this.company = company;
	}



	public Stockexchange getStockExcahnge() {
		return stockExcahnge;
	}



	public void setStockExcahnge(Stockexchange stockExcahnge) {
		this.stockExcahnge = stockExcahnge;
	}



	public String getCompanyCode() {
		return CompanyCode;
	}



	public void setCompanyCode(String companyCode) {
		CompanyCode = companyCode;
	}



	public AllDTO() {	}
	
	public AllDTO(Companystockexchangemap c) {
	this.company=new Company(c.getCompany());
	this.stockExcahnge=new Stockexchange(c.getStockexchange());
	this.CompanyCode=c.getCompanyCode();
	}


}
