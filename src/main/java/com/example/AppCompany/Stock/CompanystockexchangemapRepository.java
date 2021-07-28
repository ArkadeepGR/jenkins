package com.example.AppCompany.Stock;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface CompanystockexchangemapRepository  extends JpaRepository<Companystockexchangemap,Long> {
	List<Companystockexchangemap> findByCompanyCode(String code);
}
