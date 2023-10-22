package com.happytohelpFinance.app.service;

import java.util.List;
import java.util.Optional;

import com.happytohelpFinance.app.model.Enquirymodel;



public interface EnquiryService {

//	public Enquiry saveEnquiry(Enquiry e);
//
//	public List<Enquiry> showAllData();
//
//	public void deleteEnquiry(int enquiryId);
//
//	public Enquiry editEnquiry(Integer enquiryId, Enquiry e);
//
//

	
	public Enquirymodel saveEnquiry(Enquirymodel m);

	public Iterable<Enquirymodel> getEnquiry(String CIBILStatus);

	public Optional<Enquirymodel> getSingleEnquiry(Integer enqid);

	public Optional<Enquirymodel> findById(Integer enquiryId);

}

