package com.example.AppCompany.Stock;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.AppCompany.Company.Company;

@Entity
@Table(name = "CompanyStockexchangemap")
public class Companystockexchangemap {
	public Companystockexchangemap() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	

	private String companyCode;
	@ManyToOne(fetch = FetchType.LAZY)
	private Company company;
	@ManyToOne(fetch = FetchType.LAZY)
	private Stockexchange stockexchange;

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
       public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public Stockexchange getStockexchange() {
		return stockexchange;
	}

	public void setStockexchange(Stockexchange stockexchange) {
		this.stockexchange = stockexchange;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}

