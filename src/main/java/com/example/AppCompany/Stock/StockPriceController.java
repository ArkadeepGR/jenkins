package com.example.AppCompany.Stock;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
@CrossOrigin
public class StockPriceController {
	
	@Autowired
	StockPriceService stockpriceService;
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	@PostMapping("/stockprice")
	public StockPrice newStockPrice(@RequestBody StockPrice req)
	{
		System.out.println(req);
		return stockpriceService.addStockPrice(req);
	}
	 
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
	@GetMapping("/stockprice")
	public List<StockPrice> allStockPrice()
	{
		return stockpriceService.getAllStockprice();
	}
	
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
	@GetMapping("/stockprice/company/{name}")
	public List<StockPrice> stockPriceOfCompanyByName(@PathVariable String name)
	{
		return stockpriceService.getStockpriceOfCompanyByName(name);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
	@GetMapping("/stockprice/company/{name}/{sdate}/{stime}/{edate}/{etime}")
	public List<StockPrice> stockPriceOfCompanyByNamePeriod(@PathVariable String name,@PathVariable Date sdate,
															@PathVariable Time stime,@PathVariable Date edate,
															@PathVariable Time etime)
	{
		return stockpriceService.getStockpriceOfCompanyByNamePeriod(name,sdate,stime,edate,etime);
	}
	
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
	@GetMapping("/stockprice/companycode/{code}")
	public List<StockPrice> stockPriceOdCompanyByCode(@PathVariable String code)
	{
		return stockpriceService.getStockpriceOfCompanyByCode(code);
	}
	
	
	@PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
	@GetMapping("/stockprice/companycode/{code}/{sdate}/{stime}/{edate}/{etime}")
	public List<StockPrice> stockPriceOfCompanyByCodePeriod(@PathVariable String code,@PathVariable Date sdate,
															@PathVariable Time stime,@PathVariable Date edate,
															@PathVariable Time etime)
	{
		return stockpriceService.getStockpriceOfCompanyByCodePeriod(code,sdate,stime,edate,etime);
	}
}
