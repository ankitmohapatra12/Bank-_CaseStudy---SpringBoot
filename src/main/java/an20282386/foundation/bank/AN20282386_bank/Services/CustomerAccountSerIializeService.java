package an20282386.foundation.bank.AN20282386_bank.Services;

import org.springframework.stereotype.Service;

import an20282386.foundation.bank.AN20282386_bank.Payloads.CustomerAccountDto;


@Service
public interface CustomerAccountSerIializeService {

	
	String updateCustomerAccount(CustomerAccountDto customerAccountDto);
}
