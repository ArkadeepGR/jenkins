package com.example.AppCompany.Stock;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StockPriceRepository extends JpaRepository<StockPrice,Long>{
	List<StockPrice> findByCompanyname(String name);
	List<StockPrice> findByCompanycode(String code);
}
