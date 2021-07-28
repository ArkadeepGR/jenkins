package com.example.AppCompany.Sector;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.AppCompany.Company.Company;
import com.example.AppCompany.Stock.StockPrice;
import com.example.AppCompany.Stock.StockPriceService;


@Service
public class SectorService {
	 @Autowired
	 SectorRepository sectorRepo;
	 
	 @Autowired
	 StockPriceService stockPriceService;
	 
	 public Sector saveSector(Sector req)
	  {
		return sectorRepo.save(req); 
	  }
	 
	 public List<Sector> getAllSectors() {
		  return sectorRepo.findAll();
	  }
	 
	 public Sector getSectorById(long id) {
		   Optional<Sector> optional = sectorRepo.findById(id);
		   Sector c = optional.get();
		   return c;
	   }
	 
	 public List<Company> getAllComapniesInSector(long id) {
			Optional<Sector> optional = sectorRepo.findById(id);
			Sector c = optional.get();
			return c.getCompanies();
		}
	 
	 public float sumPrice(List<StockPrice> stks) {
		 float summ=0;
		 for (StockPrice stockPrice : stks) {
			summ=summ+stockPrice.getShareprice();
		}
		 return summ;
	 }
	 
	 public List<ChartData>  getSectorAnalyses(long id,Date sdate,Time  stime,Date edate,Time etime) {
			Optional<Sector> optional = sectorRepo.findById(id);
			Sector s = optional.get();
			List<Company> c=s.getCompanies();
			Calendar current=Calendar.getInstance();
			Calendar last=Calendar.getInstance();
			List<ChartData> data=new ArrayList<ChartData>();
			
				
				current.setTime(sdate);	
				last.setTime(edate);
				 while(!current.after(last)){
					 
					 Date date=new java.sql.Date(current.getTimeInMillis());
					 //System.out.println(date.toString());
					 float sum=0;
					 int count=0;
					for (Company company : c) {
						List<StockPrice> stks=stockPriceService.getStockpriceOfCompanyByNamePeriod(company.getName(),date,stime,date,etime);
						//System.out.println(company.getName());
						//System.out.println(stks.size());
						count=count+stks.size();
						sum=sum+sumPrice(stks);
						
						
					}
					 float avg=sum/count;
					 if(avg>=0)
					 {
						 data.add(new ChartData(date,avg));
					 }
					 
	
				        current.add(Calendar.DATE, 1);
				    }
				
				for (ChartData chartData : data) {
					System.out.println("Lable:"+chartData.getLabel()+" Value:"+chartData.getValue());
				}
			
			
				return data;
	 }

}
