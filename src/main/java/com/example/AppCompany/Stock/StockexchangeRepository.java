package com.example.AppCompany.Stock;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StockexchangeRepository extends JpaRepository<Stockexchange,Long>{
	
	Stockexchange findByName(String name);

}
