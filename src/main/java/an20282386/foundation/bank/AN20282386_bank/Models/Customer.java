package an20282386.foundation.bank.AN20282386_bank.Models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



/**
 * @author Ankit Mohapatra
 * 
 */


@Builder
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "customers")
public class Customer {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_id")
	private long customerId;
	
	@Column(name = "customer_name")
	private String customerName;

	
	@Column(name = "gender")
	private String gender;
	
	@Column(name = "date_of_birth")
	private Date dob;
	
	@Column(name = "marital_status")
	private String maritalStatus;
	
	@Column(name = "contact_number")
	private String phoneNumber;
	
	@Column(name = "email_id")
	private String emailId;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "state")
	private String state;
	
	@Column(name = "country")
	private String country;
	
	@Column(name = "pincode")
	private String pincode;
	
	
	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY,
	            cascade =  CascadeType.ALL,
	            mappedBy = "customer")
	private Account account_details;
	
	@Column(name = "isDeleted")
	private boolean isDeletedCustomer;

	
	
	
	
	
}
