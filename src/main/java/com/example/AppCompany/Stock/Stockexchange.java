package com.example.AppCompany.Stock;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "StockExchange")
public class Stockexchange{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String brief;
	private String address;
	private String remark;
	
	@OneToMany(mappedBy="stockexchange",targetEntity = Companystockexchangemap.class)
	@JsonIgnore
	private List<Companystockexchangemap> compstockmap;

	public List<Companystockexchangemap> getCompstockmap() {
		return compstockmap;
	}

	public void setCompstockmap(List<Companystockexchangemap> compstockmap) {
		this.compstockmap = compstockmap;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public Stockexchange() {
		// TODO Auto-generated constructor stub
	}
	
	public Stockexchange(Stockexchange s) {
		this.id=s.getId();
		this.address=s.getAddress();
		this.brief=s.getBrief();
		this.name=s.getName();
		this.remark=s.getRemark();
	}
}
