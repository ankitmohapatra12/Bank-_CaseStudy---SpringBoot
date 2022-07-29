package an20282386.foundation.bank.AN20282386_bank.Services;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import an20282386.foundation.bank.AN20282386_bank.Models.Account;
import an20282386.foundation.bank.AN20282386_bank.Models.Customer;
import an20282386.foundation.bank.AN20282386_bank.Payloads.AccountDto;
import an20282386.foundation.bank.AN20282386_bank.Payloads.CustomerAccountDto;
import an20282386.foundation.bank.AN20282386_bank.Repositories.AccountRepository;
import an20282386.foundation.bank.AN20282386_bank.Repositories.CustomerRepository;

@Service
public class CustomerAccountSerializedServiceImpl implements Serializable,CustomerAccountSerIializeService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Override
	public String updateCustomerAccount(CustomerAccountDto customerAccountDto) {
		String message = "";
		Customer customer = null;
		Account account = null;
		try {
			customer = dtoToCustomer(customerAccountDto);
			account = dtoToAccount(customerAccountDto);
			
			customer.setCustomerId(customerAccountDto.getCustomerId());
			customer.setAccount_details(account);
			account.setAccountId(customerAccountDto.getAccountId());
			account.setCustomer(customer);
			Customer customer_returned=new Customer();
			customer_returned = customerRepository.saveAndFlush(customer);
			
			if(customer_returned != null) {
				message = "SUCCESS";
			}
		}
		catch(Exception e) {
			message = "FAILED";
			System.out.println(e);
		}
		return message;
	}

	
	public Account dtoToAccount(CustomerAccountDto customerDto) {
		Account account = new Account();
		account.setBalance(customerDto.getBalance());
		account.setAccountType(customerDto.getAccountType());
		account.setAccountNumber(getUniqueAccountNumber());
		account.setIfscCode(customerDto.getIfscCode());
		account.setBranchName(customerDto.getBranchName());
		account.setEnabled(true);
		return account;
		
	}
	
	private String getUniqueAccountNumber() {
		String accountNumber = "HDFCACC";
		Random rand = new Random();

	    for (int i = 0; i < 15; i++)
	    {
	        int n = rand.nextInt(10) + 0;
	        accountNumber += Integer.toString(n);
	    }
	   
		return accountNumber;
	}

	public AccountDto accountToDto(Account account) {
		AccountDto accDto = new AccountDto();
		accDto.setAccountNumber(account.getAccountNumber());
		accDto.setAccountType(account.getAccountType());
		accDto.setBranchName(account.getBranchName());
		accDto.setBalance(account.getBalance());
		
		accDto.setIfscCode(account.getIfscCode());
		accDto.setCustomer(account.getCustomer());
		
		return accDto;
	}
	
	public Customer dtoToCustomer(CustomerAccountDto customerDto) throws ParseException {
		Customer customer = new Customer();
		SimpleDateFormat sdt = new SimpleDateFormat("dd-MM-YYYY");
		Date date = sdt.parse(customerDto.getDob());
		customer.setCustomerName(customerDto.getCustomerName());
		customer.setGender(customerDto.getGender());
		customer.setDob(date);
		customer.setMaritalStatus(customerDto.getMaritalStatus());
		customer.setPhoneNumber(customerDto.getPhoneNumber());
		customer.setEmailId(customerDto.getEmailId());
		customer.setAddress(customerDto.getAddress());
		customer.setCity(customerDto.getCity());
		customer.setState(customerDto.getState());
		customer.setPincode(customerDto.getPincode());
		customer.setCountry(customerDto.getCountry());
		customer.setDeletedCustomer(false);
		return customer;
		
	}

}
