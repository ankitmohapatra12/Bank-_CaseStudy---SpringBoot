package an20282386.foundation.bank.AN20282386_bank.Payloads;




/**
 * @author Ankit Mohapatra
 * 
 */



import java.util.Date;

import an20282386.foundation.bank.AN20282386_bank.Models.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CustomerDto {

	private long customerId;
	private String customerName;
	private String gender;
	private Date dob;
	private String maritalStatus;
	private String phoneNumber;
	private String emailId;
	private String address;
	private String city;
	private String state;
	private String country;
	private String pincode;
	private Account account;
}
