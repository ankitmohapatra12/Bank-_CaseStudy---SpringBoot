package an20282386.foundation.bank.AN20282386_bank.Services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonIgnore;

import an20282386.foundation.bank.AN20282386_bank.Models.Account;
import an20282386.foundation.bank.AN20282386_bank.Models.Customer;
import an20282386.foundation.bank.AN20282386_bank.Payloads.AccountDto;
import an20282386.foundation.bank.AN20282386_bank.Payloads.CustomerAccountDto;
import an20282386.foundation.bank.AN20282386_bank.Payloads.CustomerDto;
import an20282386.foundation.bank.AN20282386_bank.Repositories.AccountRepository;
import an20282386.foundation.bank.AN20282386_bank.Repositories.CustomerRepository;



/**
 * @author Ankit Mohapatra
 * 
 */


@Service
public class CustomerAccountServiceImpl implements CustomerAccountService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Override
	public String saveCustomerAccount(CustomerAccountDto customerAccountDto) {
		String message = "SUCCESS";
		Customer customer = null;
		Account account = null;
		try {
			customer = dtoToCustomer(customerAccountDto);
			account = dtoToAccount(customerAccountDto);
			customer.setCustomerId(customerAccountDto.getCustomerId());
			customer.setAccount_details(account);
			
			Customer customer_returned=new Customer();
			Account account_returned = new Account();
			if(customer.getCustomerId()==0) {
				customer_returned = customerRepository.save(customer);
				
			}
			else {
				account.setAccountId(customerAccountDto.getAccountId());
				account.setCustomer(customer);
				customer_returned = customerRepository.saveAndFlush(customer);
				account_returned =  accountRepository.saveAndFlush(account);
			}
			
			if(customer_returned != null && account_returned!=null) {
				message = "SUCCESS";
			}
		}
		catch(Exception e) {
			message = "FAILED";
			System.out.println(e);
		}
		return message;
	}
	

	
	
	
	@Override
	@JsonIgnore
	public AccountDto getBalanceOf(long accountId) {
		
		Account account = new Account();
		AccountDto accDto = new AccountDto();
		
		try {
			account =  accountRepository.findById(accountId).get();
			if(account!=null) {

				accDto = accountToDto(account);
				return accDto;
			}
		}catch (Exception e) {
			System.out.println("Failed to fetch customer !"+e);
			return accDto;
		}
		return accDto;
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

	
	public CustomerDto customerentityToDto(Customer customer) throws ParseException {
		CustomerDto customerDto = new CustomerDto();
		customerDto.setCustomerName(customer.getCustomerName());
		customerDto.setGender(customer.getGender());
		customerDto.setDob(customer.getDob());
		customerDto.setMaritalStatus(customer.getMaritalStatus());
		customerDto.setPhoneNumber(customer.getPhoneNumber());
		customerDto.setEmailId(customer.getEmailId());
		customerDto.setAddress(customer.getAddress());
		customerDto.setCity(customer.getCity());
		customerDto.setState(customer.getState());
		customerDto.setPincode(customer.getPincode());
		customerDto.setCountry(customer.getCountry());
		customerDto.setAccount(customer.getAccount_details());
		return customerDto;
		
	}
	
	
	@Override
	public List<CustomerDto> getAllCustomersAccounts() {
		List<Customer> customers = new ArrayList<>();
		List<CustomerDto> customerDto = new ArrayList<>();
		
		try{
			customers = customerRepository.findAllNotDeleted();
			
			if(!customers.isEmpty()) {
				for(Customer customer : customers) {
					customerDto.add(customerentityToDto(customer));
				}
			}
			System.out.println(customerDto);
		   
		}catch(Exception e) {
			System.out.println("Error in getting customer data !");
			return null;
		}
		return customerDto;
	}
	
	
	@Override
	public CustomerDto findById(Long id) {
		Customer customer = null;
		CustomerDto customerDto = null;
		try {
			customer = customerRepository.findById(id).get();
			customerDto = customerentityToDto(customer);
			
		}catch (Exception e) {
			System.out.println("Error in fetching customer !");
			return null;
		}
		return customerDto;
	}
	
	
	@Override
	public String archieveCustomerAccounts(List<Long> item_ids) {
		String response ="";
	    try {
	    	int  i = customerRepository.archieveCustomerAccounts(item_ids);
	    	if(i>0) {
	    		response = "SUCCESS";
	    	}
	    	
	    }catch(Exception e ) {
	    	return "FAILED";
	    }
		return response;
	}
	
	
	@Override
	public String enableCustomerAccounts(List<Long> item_ids) {
		String response ="";
	    try {
	    	int  i = customerRepository.enableCustomerAccounts(item_ids);
	    	if(i>0) {
	    		response = "SUCCESS";
	    	}
	    	
	    }catch(Exception e ) {
	    	return "FAILED";
	    }
		return response;
	}
	
	@Override
	public CustomerDto findByAnyField(CustomerDto searchData) {
		Customer customer = null;
		CustomerDto customerDto = null;
		String customerName = searchData.getCustomerName();
		String email = searchData.getEmailId();
		String contactNum =  searchData.getPhoneNumber();
		
		try {
			customer = customerRepository.findByAny(customerName,email,contactNum);
			customerDto = customerentityToDto(customer);
			
		}catch (Exception e) {
			System.out.println("Error in fetching customer !");
			return null;
		}
		return customerDto;
	}





	@Override
	public String transferFunds(long from, long to, double amount) {
	
		Account accountTo = new Account();
		Account accountFrom =  new Account();
		try {
			try {
			accountFrom = accountRepository.findById(from).get();
			accountTo = accountRepository.findById(to).get();
			}catch (Exception e) {
				return "Failed to find account".toUpperCase();
			}
			double accountFromBal = accountFrom.getBalance();
			boolean toEnabled = accountFrom.isEnabled();
			boolean fromEnabled = accountTo.isEnabled();
			if(accountFrom.getAccountNumber()==null && accountTo.getAccountNumber()==null) 
			{
				return "ID MISMATCH";
			}
			else if(toEnabled==false || fromEnabled==false) 
			{
				return "ACCOUNT IS DELETED";
			}
			else if(accountFrom.getBalance()<amount) {
				
				return ("insufficient funds").toUpperCase();
			}
			else {
				try {
					
					accountFrom.setBalance(accountFromBal-amount);
					accountTo.setBalance(accountTo.getBalance()+amount);
					
					Object accFrom = accountRepository.saveAndFlush(accountFrom);
					Object accTo = accountRepository.saveAndFlush(accountTo);
					
					if(accFrom!=null && accTo!=null) {
						return "success".toUpperCase();
					}
					
				}catch (Exception e) {
					System.out.println("Failed to save balance !");
					return ("failed to save balance !").toUpperCase();	
				}
			}
			
		}catch (Exception e) {
			System.out.println("Failed"+e);
			return "Some internal server error occured".toUpperCase();
		}
		return "SUCCESS";
	}

}
