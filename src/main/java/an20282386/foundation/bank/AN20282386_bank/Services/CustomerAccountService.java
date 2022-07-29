package an20282386.foundation.bank.AN20282386_bank.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import an20282386.foundation.bank.AN20282386_bank.Payloads.AccountDto;
import an20282386.foundation.bank.AN20282386_bank.Payloads.CustomerAccountDto;
import an20282386.foundation.bank.AN20282386_bank.Payloads.CustomerDto;



/**
 * @author Ankit Mohapatra
 * 
 */


@Service
public interface CustomerAccountService {

	String saveCustomerAccount(CustomerAccountDto customerAccountDto);

	List<CustomerDto> getAllCustomersAccounts();

	CustomerDto findById(Long id);
	
	CustomerDto findByAnyField(CustomerDto  customerDto);

	String archieveCustomerAccounts(List<Long> ids);

	String enableCustomerAccounts(List<Long> ids);

	AccountDto getBalanceOf(long id);

	String transferFunds(long from, long to, double amount);

}
