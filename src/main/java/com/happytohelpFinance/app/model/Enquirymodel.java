
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
public class Enquirymodel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer enquiryId;
	private String customerFirstName;
	private String customerLastName;
	private String customerEmailId;

	private Long aadharNumber;
	private long customerMobileNumber;
	private String pancardNumber;
	

	private String cibilStatus;
	private Integer cibilScore;

}
