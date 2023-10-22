package com.happytohelpFinance.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.happytohelpFinance.app.model.Enquirymodel;


@Repository
public interface EnquiryRepository extends JpaRepository<Enquirymodel,Integer> {
	 public Iterable<Enquirymodel> findAllByCibilStatus(String cibilStatus);	
	}

