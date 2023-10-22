package com.happytohelpFinance.app.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CustomerDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer customerId;
		private String customerFirstName;
		private String customerMiddleName;
		private String customerLastName;
		private String customerDateOfBirth;
		private String customerGender;
		private String qualification;
		private String customeremailId;
		private long mobileNumber;
		private String panCardNumber;
		private long aadharNumber;
		private Integer customerCibilScore;
		private String customerLoanStatus;
		private Double loanAmountRequired;
		
		@OneToOne(cascade = CascadeType.ALL)
		private CustomerBankDetails customerbankdetails;
		
		@OneToOne(cascade = CascadeType.ALL)
		private CustomerProfessionalDetails customerprofessionaldetails;
		
		@OneToOne(cascade = CascadeType.ALL)
		private PreviousLoanDetails peviousloandetails;
		
		@OneToOne(cascade = CascadeType.ALL)
		private CustomerDocuments customerdocuments;
		
		
		@OneToOne(cascade = CascadeType.ALL)
		private PropertyDetails propertydetails;
	 
		@OneToOne(cascade = CascadeType.ALL)
		private GuarantorDetails guarantordetails;
		
		@OneToOne(cascade = CascadeType.ALL)
		private CustomerAddress customerAddress;

		@OneToOne(cascade = CascadeType.ALL)
		private BuilderDetails builderDetails;
		
		@OneToOne(cascade = CascadeType.ALL)
		private LoanDisbursement customerLoanDisbursement;

		
		@OneToOne(cascade = CascadeType.ALL)
		private SanctionLetter customerSanctionLetter;

}
