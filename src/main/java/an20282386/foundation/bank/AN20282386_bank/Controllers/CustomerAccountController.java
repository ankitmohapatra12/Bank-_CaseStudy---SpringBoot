package an20282386.foundation.bank.AN20282386_bank.Controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnore;

import an20282386.foundation.bank.AN20282386_bank.Payloads.AccountDto;
import an20282386.foundation.bank.AN20282386_bank.Payloads.CustomerAccountDto;
import an20282386.foundation.bank.AN20282386_bank.Payloads.CustomerDto;
import an20282386.foundation.bank.AN20282386_bank.Payloads.TransferFundDto;
import an20282386.foundation.bank.AN20282386_bank.Services.CustomerAccountSerIializeService;
import an20282386.foundation.bank.AN20282386_bank.Services.CustomerAccountService;




/**
 * @author Ankit Mohapatra
 * 
 */


@RestController
public class CustomerAccountController {
	
	@Autowired
	private CustomerAccountService customerAccountService;
	
	@Autowired
	private CustomerAccountSerIializeService customerSerializedService;
	
	
	@PostMapping("/saveCustomerAccount")
	public ResponseEntity<String> saveCustomerAndCreateAccount(@RequestBody CustomerAccountDto customerAccountDto){
		String message = customerAccountService.saveCustomerAccount(customerAccountDto);
		return new ResponseEntity<>(message,HttpStatus.OK);
	}
	
	
	@GetMapping("/getCustomers")
	@JsonIgnore
	public ResponseEntity<Collection<CustomerDto>> getAllCustomersAccounts(){
		List<CustomerDto> customerAccounts = new ArrayList<>();
		
		 customerAccounts = customerAccountService.getAllCustomersAccounts();
		
			if(customerAccounts.isEmpty()) {
				return new ResponseEntity<>(customerAccounts,HttpStatus.NOT_FOUND);
			}
	
		return new ResponseEntity<>(customerAccounts,HttpStatus.FOUND);
	}
	
	@PostMapping("/transferAmount")
	public ResponseEntity<String> transferFunds(@RequestBody TransferFundDto transferFundDto){
		String message = customerAccountService.transferFunds(transferFundDto.getFrom(),transferFundDto.getTo(),transferFundDto.getAmount());
		return new ResponseEntity<>(message,HttpStatus.OK);
	}
	
	
	@GetMapping("/getCustomer/{id}")
	public ResponseEntity<CustomerDto> getCustomerById(@PathVariable("id") long id){
		CustomerDto customerAccount = customerAccountService.findById(id);
		return new ResponseEntity<>(customerAccount,HttpStatus.FOUND);
	}
	
	
	@PostMapping("/searchCustomer")
	public ResponseEntity<CustomerDto> getCustomerByAnyValue(@RequestBody CustomerDto  customerDto){
		CustomerDto customerAccount = customerAccountService.findByAnyField(customerDto);
		return new ResponseEntity<>(customerAccount,HttpStatus.FOUND);
	}
	
	@GetMapping("/getAccount/{id}")
	@JsonIgnore
	public ResponseEntity<AccountDto> getAccountDetails(@PathVariable("id") long id){
		AccountDto account = customerAccountService.getBalanceOf(id);
		return new ResponseEntity<>(account,HttpStatus.FOUND);
	}
	
	
	//http://localhost:8088/archieveCustomerAccounts?ids=2,3 use this to send values 
	@GetMapping(value="/archieveCustomerAccounts", params = "ids")
	public ResponseEntity<String> archievedItems(@RequestParam List<Long> ids){
		String message = customerAccountService.archieveCustomerAccounts(ids);
		return new ResponseEntity<>(message,HttpStatus.OK);
	}
	
	
	@GetMapping(value="/enableCustomerAccounts", params = "ids")
	public ResponseEntity<String> enableItems(@RequestParam List<Long> ids){
		String message = customerAccountService.enableCustomerAccounts(ids);
		return new ResponseEntity<>(message,HttpStatus.OK);
	}
	
	
	@PostMapping("/updateCustomerAccount")
	public ResponseEntity<String> updateCustomerById(@RequestBody CustomerAccountDto customerAccountDto){
		String message = customerAccountService.saveCustomerAccount(customerAccountDto);
		return new ResponseEntity<>(message,HttpStatus.OK);
	}
	
	
	@PostMapping("/updateSerializedCustomerAccount")
	public ResponseEntity<String> updateSerialiazedCustomerById(@RequestBody CustomerAccountDto customerAccountDto){
		String message = customerSerializedService.updateCustomerAccount(customerAccountDto);
		return new ResponseEntity<>(message,HttpStatus.OK);
	}
	
	
	
}
