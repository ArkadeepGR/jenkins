package com.example.AppCompany.IPO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.AppCompany.Company.Company;
import com.example.AppCompany.Company.CompanyService;


@Service
public class IPODetailService {
	
	@Autowired
	IPODetailRepository ipoRepo;
	
	@Autowired
	CompanyService companyService;
	
	
	public IPODetail saveIPO(UpdateIPO req)
	  {
		IPODetail ipo=req.getIpo();
		try {
			Company c= companyService.getCompanyByName(req.getCompanyname());
			ipo.setCompany(c);
		} catch (Exception e) {
			System.out.println(e);
		}
		return ipoRepo.save(ipo);
	  }
	
	  public List<IPODetail> getAllIPO()
	  {
			return ipoRepo.findAll();
	  }

}
