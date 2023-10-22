package com.happytohelpFinance.app.service;

import com.happytohelpFinance.app.model.CustomerDetails;
import com.happytohelpFinance.app.model.Email;

public interface EmailService {
	 public void sendMail(Email e);

	 // public SanctionLetter sendSantionLetterMail(CustomerDetails customerDetails);
	}
