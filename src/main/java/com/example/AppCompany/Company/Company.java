package com.example.AppCompany.Company;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.example.AppCompany.IPO.IPODetail;
import com.example.AppCompany.Sector.Sector;
import com.example.AppCompany.Stock.Companystockexchangemap;
import com.example.AppCompany.Stock.StockPrice;
import com.fasterxml.jackson.annotation.JsonIgnore;

@NamedQuery(name = "Company.findByname", query = "SELECT c FROM Company c WHERE c.name = :name")
@Entity
@Table(name="Company")
public class Company {



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private Double turnover;

	@Column(nullable = false)
	private String ceo;

	@Column(nullable = false)
	@Type(type = "text")
	private String boardOfDirectors;

	@Column(nullable = false)
	@Type(type = "text")
	private String companyBrief;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "company", cascade = CascadeType.REMOVE)
	@JsonIgnore
	private IPODetail ipo;

	@OneToMany(mappedBy="company",targetEntity = Companystockexchangemap.class)
	@JsonIgnore
	private List<Companystockexchangemap> compstockmap;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Sector sector;
	
	@OneToMany(mappedBy="company",targetEntity = StockPrice.class,fetch = FetchType.LAZY)
	@JsonIgnore
	private List<StockPrice> stockPrices;

	public List<StockPrice> getStockPrices() {
		return stockPrices;
	}
	
	
	public void setStockPrices(List<StockPrice> stockPrices) {
		this.stockPrices = stockPrices;
	}

	protected Company() {
	}



	public Company(String name, double turnover, String ceo, String boardOfDirectors, String companyBrief) {
		super();
		this.name = name;
		this.turnover = turnover;
		this.ceo = ceo;
		this.boardOfDirectors = boardOfDirectors;
		this.companyBrief = companyBrief;
	}
	
	public Company(Company c) {
		super();
		this.id=c.getId();
		this.name = c.getName();
		this.turnover = c.getTurnover();
		this.ceo = c.getCeo();
		this.boardOfDirectors = c.getBoardOfDirectors();
		this.companyBrief = c.getCompanyBrief();
	}




	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public Double getTurnover() {
		return turnover;
	}



	public void setTurnover(Double turnover) {
		this.turnover = turnover;
	}



	public String getCeo() {
		return ceo;
	}



	public void setCeo(String ceo) {
		this.ceo = ceo;
	}



	public String getBoardOfDirectors() {
		return boardOfDirectors;
	}



	public void setBoardOfDirectors(String boardOfDirectors) {
		this.boardOfDirectors = boardOfDirectors;
	}



	public String getCompanyBrief() {
		return companyBrief;
	}



	public void setCompanyBrief(String companyBrief) {
		this.companyBrief = companyBrief;
	}



	public IPODetail getIpo() {
		return ipo;
	}



	public void setIpo(IPODetail ipo) {
		this.ipo = ipo;
	}



	public List<Companystockexchangemap> getCompstockmap() {
		return compstockmap;
	}



	public void setCompstockmap(List<Companystockexchangemap> compstockmap) {
		this.compstockmap = compstockmap;
	}



	public Sector getSector() {
		return sector;
	}



	public void setSector(Sector sector) {
		this.sector = sector;
	}
	
	
	
}