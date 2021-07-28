package com.example.AppCompany.Stock;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.example.AppCompany.Company.Company;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@NamedQuery(name ="Stockprice.findByname", query = "SELECT c FROM Company c WHERE c.name = :name")
@Table(name="StockPrice")
public class StockPrice {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String exchangename;
	private String companycode;
	private String companyname;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Company company;
	
	private Date date;
	private Time time;
	private float shareprice;
	
	
	
	public StockPrice() {
		super();
	}
	
	
	
	
	/*public StockPrice(long id, String exchangename, String companycode, Company company, Date date, Time time,
			float shareprice,String companyname) {
		super();
		this.id = id;
		this.exchangename = exchangename;
		this.companycode = companycode;
		this.company = company;
		this.date = date;
		this.time = time;
		this.shareprice = shareprice;
		this.companyname= companyname;
	}*/



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getExchangename() {
		return exchangename;
	}

	public void setExchangename(String exchangename) {
		this.exchangename = exchangename;
	}

	public String getCompanycode() {
		return companycode;
	}

	public void setCompanycode(String companycode) {
		this.companycode = companycode;
	}	

	/*public StockPrice( String exchangename, String companycode,  
			Date date, Time time, float shareprice) {
		super();
	
		this.exchangename = exchangename;
		this.companycode = companycode;
		this.date = date;
		this.time= time;
		this.shareprice = shareprice;
	}
	
	public StockPrice( String exchangename, String companycode,Company company,  
			Date date, Time time, float shareprice) {
		super();
	
		this.exchangename = exchangename;
		this.companycode = companycode;
		this.company = company;
		this.date = date;
		this.time= time;
		this.shareprice = shareprice;
	} */

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public float getShareprice() {
		return shareprice;
	}

	public void setShareprice(float shareprice) {
		this.shareprice = shareprice;
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
		
}
