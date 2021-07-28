package com.example.AppCompany.Company;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Long> {
	Company  findByName(String name);
}
