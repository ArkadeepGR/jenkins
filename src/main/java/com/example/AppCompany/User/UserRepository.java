package com.example.AppCompany.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User1,Long>{
	User1 findByName(String name);
}
