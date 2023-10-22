package com.happytohelpFinance.app.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.happytohelpFinance.app.model.CustomerDetails;
import com.happytohelpFinance.app.repository.CustomerRepository;
import com.happytohelpFinance.app.service.CustomerService;
@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository cr;

	@Override
	public CustomerDetails addForm(CustomerDetails cust) {
		
		return cr.save(cust);
	}

	@Override
	public List<CustomerDetails> displayAllData() {
		
		return (List<CustomerDetails>)cr.findAll();
	}

	@Override
	public CustomerDetails DocumentUpdate(CustomerDetails cu, Integer customerId) {
		
		return cr.save(cu);
	}

	@Override
	public List<CustomerDetails> searchCustomer(String customerFirstName) {
		
	
		List<CustomerDetails> findAllBycustomerFirstName = cr.findAllBycustomerFirstName(customerFirstName);
		//if (findAllBycustomerFirstName != null) {
			return findAllBycustomerFirstName;

//		} else {
//			throw new ApplicatinFormDetailsNotFound("plese enter valid details");
//
//		}

	}

	@Override
	public Iterable<CustomerDetails> getCustomerbyStatus(String custloanstatus) {
		Iterable<CustomerDetails> get = cr.findAllByCustomerLoanStatus(custloanstatus);
		return get;
	}

	@Override
	public void deleteData(Integer customerId) {
		cr.deleteById(customerId);
		
	}

	@Override
	public Optional<CustomerDetails> findById(Integer customerId) {
		Optional<CustomerDetails> findById= cr.findById(customerId);
		return findById;
	}



	
	
}
