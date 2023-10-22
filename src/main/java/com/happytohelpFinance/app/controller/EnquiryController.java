package com.happytohelpFinance.app.controller;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.happytohelpFinance.app.enums.CibilStatus;
import com.happytohelpFinance.app.exception.EnquiryNotFound;
import com.happytohelpFinance.app.model.Email;
import com.happytohelpFinance.app.model.Enquirymodel;
import com.happytohelpFinance.app.repsonse.BaseResponse;
import com.happytohelpFinance.app.service.EmailService;
import com.happytohelpFinance.app.service.EnquiryService;

@CrossOrigin("*")
@RestController
@RequestMapping("/enquiry")
public class EnquiryController {


	@Autowired
	EnquiryService es;
	@Autowired
    EmailService emailservice;
	@Autowired
	Email email;
	

	@PostMapping("/postEnquiry")
	public ResponseEntity<BaseResponse<Enquirymodel>> postEnquiry(@RequestBody Enquirymodel m) {

		m.setCibilStatus(String.valueOf(CibilStatus.pending));

		Enquirymodel enquiry= es.saveEnquiry(m);
		BaseResponse bs=new BaseResponse<>(201,"Data saved",enquiry);
		
		return new ResponseEntity<BaseResponse<Enquirymodel>>(bs,HttpStatus.OK);
		
	}
	@GetMapping("/getEnquiry/{CIBILStatus}")
	public ResponseEntity<BaseResponse<Iterable<Enquirymodel>>> getEnquiry(@PathVariable("CIBILStatus") String CIBILStatus)
	{
		Enquirymodel eq=null;
		
		 Iterable<Enquirymodel> enq = es.getEnquiry(CIBILStatus);
		 for(Enquirymodel enq1:enq) {
			 if(enq1 !=null) {
				 eq=enq1;
			 }
		 }
		 if(eq !=null) {
		 BaseResponse<Iterable<Enquirymodel>> ba= new BaseResponse<>(200,"All data Ok",enq);
		 return new ResponseEntity<BaseResponse<Iterable<Enquirymodel>>>(ba,HttpStatus.OK) ;
		 }else {
		 throw new EnquiryNotFound();
		 }		
	}
	
	@GetMapping("/getsingleenquiry/{enqid}")
	public ResponseEntity<BaseResponse<Enquirymodel>> getSingleEnquiry(@PathVariable Integer enqid){
		Optional<Enquirymodel> singleEnquiry = es.getSingleEnquiry(enqid);
		if(singleEnquiry.isEmpty()) {
		throw new EnquiryNotFound();
		}else {
			BaseResponse ba= new BaseResponse<>(200,"All data Ok",singleEnquiry);
			return new ResponseEntity<BaseResponse<Enquirymodel>>(ba,HttpStatus.OK);
		}
	}
	
	
//	@PutMapping("/CheckCIBIL/{enquiryId}")
//	public ResponseEntity<BaseResponse<Enquirymodel>> checkCibilScore(@PathVariable("enquiryId") Integer enquiryId,@RequestBody Enquirymodel enq)
//	{
//		Random ramdom=new Random();
//		int cibilScore = ramdom.nextInt(300, 900);
//		
//		if(cibilScore>=600 && cibilScore<=900) 
//		{
//			enq.setCibilStatus(String.valueOf(CibilStatus.approved));
//			enq.setCibilScore(cibilScore);
//		
//		    Enquirymodel enquiry = es.saveEnquiry(enq);
//
//		    BaseResponse<Enquirymodel> baseResponse = new BaseResponse<Enquirymodel>(200,"CIBIL Approved",enquiry);
//			return new ResponseEntity<BaseResponse<Enquirymodel>>(baseResponse,HttpStatus.OK);
//		}
//		else
//		{
//			enq.setCibilStatus(String.valueOf(CibilStatus.rejected));
//			enq.setCibilScore(cibilScore);
//			
//			Enquirymodel enquiry = es.saveEnquiry(enq);
//			
//        BaseResponse<Enquirymodel> baseResponse = new BaseResponse<Enquirymodel>(200,"CIBIL Rejected",enquiry);
//			return new ResponseEntity<BaseResponse<Enquirymodel>>(baseResponse,HttpStatus.OK);
//		}
		@PutMapping("/CheckCIBIL/{enquiryId}")
		public ResponseEntity<BaseResponse<Enquirymodel>> checkCibilScore(@PathVariable("enquiryId") Integer enquiryId,@RequestBody Enquirymodel enq)
		{
			Random ramdom=new Random();
			int cibilScore = ramdom.nextInt(600) + 300;
			
			if(cibilScore>=600 && cibilScore<=900) 
			{
				enq.setCibilStatus(String.valueOf(CibilStatus.approved));
				enq.setCibilScore(cibilScore);
			
			    Enquirymodel enquiry = es.saveEnquiry(enq);

			    BaseResponse<Enquirymodel> baseResponse = new BaseResponse<Enquirymodel>(200,"CIBIL Approved",enquiry);
				return new ResponseEntity<BaseResponse<Enquirymodel>>(baseResponse,HttpStatus.OK);
			}
			else
			{
				enq.setCibilStatus(String.valueOf(CibilStatus.rejected));
				enq.setCibilScore(cibilScore);
				Enquirymodel enquiry = es.saveEnquiry(enq);			
			}
			return null;
		
			
		
	}
}
