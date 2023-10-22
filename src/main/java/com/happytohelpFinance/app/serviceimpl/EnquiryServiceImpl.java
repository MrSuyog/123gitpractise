package com.happytohelpFinance.app.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.happytohelpFinance.app.model.Enquirymodel;
import com.happytohelpFinance.app.repository.EnquiryRepository;
import com.happytohelpFinance.app.service.EnquiryService;


@Service
public class EnquiryServiceImpl implements EnquiryService{


	@Autowired
	EnquiryRepository er;
	
	@Override
	public Enquirymodel saveEnquiry(Enquirymodel m) {
		Enquirymodel save = er.save(m);
		
		
		return save;
	}

	@Override
	public Iterable<Enquirymodel> getEnquiry(String CIBILStatus) {
		
		Iterable<Enquirymodel> get=er.findAllByCibilStatus(CIBILStatus);
		return get;
	}

	@Override
	public Optional<Enquirymodel> getSingleEnquiry(Integer enqid) {
		Optional<Enquirymodel> findById = er.findById(enqid);
		return findById;
	}

	@Override
	public Optional<Enquirymodel> findById(Integer enquiryId) {	
		return er.findById(enquiryId);
	}

}

