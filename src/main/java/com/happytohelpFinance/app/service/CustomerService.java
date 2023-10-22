package com.happytohelpFinance.app.service;

import java.util.List;
import java.util.Optional;

import com.happytohelpFinance.app.model.CustomerDetails;

public interface CustomerService {

public	CustomerDetails addForm(CustomerDetails cust);

public List<CustomerDetails> displayAllData();

public CustomerDetails DocumentUpdate(CustomerDetails cu, Integer customerId);

public List<CustomerDetails> searchCustomer(String customerFirstName);

public Iterable<CustomerDetails> getCustomerbyStatus(String custloanstatus);

public void deleteData(Integer customerId);

public Optional<CustomerDetails> findById(Integer customerId);



	
}
