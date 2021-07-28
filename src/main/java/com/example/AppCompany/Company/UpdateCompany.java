package com.example.AppCompany.Company;

import com.example.AppCompany.IPO.IPODetail;
import com.example.AppCompany.Sector.Sector;

public class UpdateCompany {
	Company company;
	long sectorId;
	
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public long getSectorId() {
		return sectorId;
	}
	public void setSectorId(long sectorId) {
		this.sectorId = sectorId;
	}
}
