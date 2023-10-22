 package com.happytohelpFinance.app.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.happytohelpFinance.app.exception.CustomerNotFound;
import com.happytohelpFinance.app.model.CustomerDetails;
import com.happytohelpFinance.app.model.CustomerDocuments;
import com.happytohelpFinance.app.model.SanctionLetter;
//import com.happytohelpFinance.app.model.SanctionedLetter;
import com.happytohelpFinance.app.repsonse.BaseResponse;
import com.happytohelpFinance.app.service.CustomerService;

@RestController
@CrossOrigin("*")
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	CustomerService cs;
	
	@PostMapping("/save")
	public String postCustomer(@RequestParam(value="panCard" ,required=false)MultipartFile file1,
			@RequestParam(value="aadharCard",required = false)MultipartFile file2,@RequestParam(value="photo",required = false)MultipartFile file3,
			@RequestParam(value="salarySlips",required = false)MultipartFile file4,@RequestParam(value="bankStatement",required = false) MultipartFile file5,
//			@RequestParam(value="sanctionLetter" ,required=false)MultipartFile file6,
			@RequestPart("allData")String customer) {
		System.out.println(customer);
		ObjectMapper om=new ObjectMapper();
		CustomerDetails cust;
		System.out.println("Its work");
		try {
			cust=om.readValue(customer,CustomerDetails.class);
			
			CustomerDocuments cd=new CustomerDocuments();
			cd.setPanCard(file1.getBytes());
			cd.setAadharCard(file2.getBytes());
			cd.setPhoto(file3.getBytes());
			cd.setSalarySlips(file4.getBytes());     
			cd.setBankStatement(file5.getBytes());
			
			
			//cust.getCustomerSanctionLetter().setSanctionLetter(file6.getBytes());
			cust.setCustomerdocuments(cd);
			
		  cs.addForm(cust);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return "Data Successfully Submited";
	}
	
	@GetMapping("/getAllData")
	public ResponseEntity<List<CustomerDetails>> displayAllData() {
		List<CustomerDetails> displayAllData = cs.displayAllData();
		return new ResponseEntity<List<CustomerDetails>>(displayAllData, HttpStatus.OK);
	}
	
	@PutMapping(value = "/update/{customerId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<CustomerDetails> DocumentUpdate(@RequestParam("panCard")MultipartFile file1,
			@RequestParam("aadharCard")MultipartFile file2,@RequestParam("photo")MultipartFile file3,
			@RequestParam("salarySlips")MultipartFile file4,@RequestParam("bankStatement") MultipartFile file5,
			@RequestParam(value ="sanctionLetter" )MultipartFile file6,
			@RequestPart("Customer")String customer,@PathVariable ("customerId")Integer customerId){
	
		ObjectMapper om = new ObjectMapper();
	CustomerDetails cu = new CustomerDetails();

		try {
			cu = om.readValue(customer, CustomerDetails.class);

			System.out.println(cu);

			CustomerDocuments ad = new CustomerDocuments();
			SanctionLetter ss=new SanctionLetter();
			
			ad.setPanCard(file1.getBytes());
			ad.setAadharCard(file2.getBytes());
			ad.setPhoto(file4.getBytes());
			ad.setSalarySlips(file3.getBytes());
			ad.setBankStatement(file5.getBytes());
			ss.setSanctionLetter(file6.getBytes());
			cu.setCustomerdocuments(ad);
			
			cu.setCustomerSanctionLetter(ss);
			cu.getCustomerSanctionLetter();
			CustomerDetails documentUpdate = cs.DocumentUpdate(cu, customerId);
			cs.addForm(documentUpdate);
	}
		 catch (IOException ie) {
				// e.printStackTrace();
			}
			return new ResponseEntity<CustomerDetails>(cu, HttpStatus.ACCEPTED);
		}
	   
	@GetMapping("/getCustomer/{custloanstatus}")	//get customer by loan status
	public ResponseEntity<BaseResponse<Iterable<CustomerDetails>>> getCustomerByStatus(
			@PathVariable("custloanstatus") String custloanstatus) {
		CustomerDetails cstd = null;
		Iterable<CustomerDetails> cst = cs.getCustomerbyStatus(custloanstatus);
		for (CustomerDetails cstds : cst) {
			if (cstds != null) {
				cstd = cstds;
			}
		}
		if (cstd != null) {
			BaseResponse<Iterable<CustomerDetails>> br = new BaseResponse<>(200, "All Data Successfully get..", cst);
			return new ResponseEntity<BaseResponse<Iterable<CustomerDetails>>>(br, HttpStatus.OK);
		} else {
			throw new CustomerNotFound();
		}
	}

	
	@DeleteMapping("/deleteCustomer/{customerId}")	//delete customer by id
	public ResponseEntity<BaseResponse<CustomerDetails>> deleteCustomer(@PathVariable Integer customerId) {

		Optional<CustomerDetails> customerdetail = cs.findById(customerId);
		if (customerdetail.isPresent()) {
			cs.deleteData(customerId);
			BaseResponse br = new BaseResponse<>(200, "Data Deleted Successfully", customerdetail);
			return new ResponseEntity<BaseResponse<CustomerDetails>>(br, HttpStatus.OK);

		} else {

			throw new CustomerNotFound();
		}
	}


	@GetMapping("/getSingleCust/{customerFirstName}")
	public ResponseEntity<List<CustomerDetails>> searchCustomer(@PathVariable String customerFirstName) {
		try {
			List<CustomerDetails> applicationlist = cs.searchCustomer(customerFirstName);
			return new ResponseEntity<List<CustomerDetails>>(applicationlist, HttpStatus.OK);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		}
		return new ResponseEntity<List<CustomerDetails>>(HttpStatus.OK);

	}

	
	}
