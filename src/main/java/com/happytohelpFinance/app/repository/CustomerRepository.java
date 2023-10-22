package com.happytohelpFinance.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.happytohelpFinance.app.model.CustomerDetails;


@Repository
public interface CustomerRepository extends JpaRepository<CustomerDetails, Integer> {

public	List<CustomerDetails> findAllBycustomerFirstName(String customerFirstName);

public Iterable<CustomerDetails> findAllByCustomerLoanStatus(String custloanstatus);

}
