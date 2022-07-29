package an20282386.foundation.bank.AN20282386_bank.Repository;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import an20282386.foundation.bank.AN20282386_bank.Models.Account;
import an20282386.foundation.bank.AN20282386_bank.Models.Customer;
import an20282386.foundation.bank.AN20282386_bank.Repositories.AccountRepository;
import an20282386.foundation.bank.AN20282386_bank.Repositories.CustomerRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace =  Replace.NONE)
public class CustomerRepositoryTest {

	
	private static final Logger log = LoggerFactory.getLogger(CustomerRepositoryTest.class);
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	
	@Test
	public void testCreateCustomer() {
		
		Account account =  Account.builder()
				.accountNumber("dsererer34343")
				.accountType("saving")
				.ifscCode("ifsc224")
				.branchName("cspur")
				.balance(243434)
				.customer(null)
				.isEnabled(true).build();
		
		
		Customer customer = Customer.builder()
				.customerName("DIXIT")
				.gender("male")
				.maritalStatus("single")
				.phoneNumber("3993943943")
				.emailId("sef@gmail.com")
				.address("48/922")
				.city("delhi")
				.state("new delhi")
				.country("India")
				.pincode("43434343")
				.account_details(account).build();
		
		
		account.setCustomer(customer);
		
		customerRepository.save(customer);
		
		Assertions.assertThat(customer.getCustomerId()).isGreaterThan(0);
		
		log.info("", Assertions.assertThat(customer.getCustomerId()).isGreaterThan(0));
	}
	
	
	
	@Test
	public void testEmptyRecord() {
		Optional<Customer> customer = customerRepository.findById(4L);
		Assertions.assertThat(customer.isPresent()).isEqualTo(false);
	}
	
	
	@Test
	public void testFindAllCustomers() {
		List<Customer> customers = customerRepository.findAll();
		Assertions.assertThat(customers.size()).isGreaterThan(0);
	}
	
	@Test
	public void testFindCustomerById() {
		
		Customer customer = customerRepository.findById(1L).get();
		Assertions.assertThat(customer.getCustomerId()).isEqualTo(1L);
	}
	
	@Test
	public void testFindByAnyField() {
		
		Account account =  Account.builder()
				.accountNumber("dsererer34343")
				.accountType("saving")
				.ifscCode("ifsc224")
				.branchName("cspur")
				.balance(243434)
				.customer(null)
				.isEnabled(true).build();
		
		
		Customer customer = Customer.builder()
				.customerName("DIXIT")
				.gender("male")
				.maritalStatus("single")
				.phoneNumber("3993943943")
				.emailId("sef@gmail.com")
				.address("48/922")
				.city("delhi")
				.state("new delhi")
				.country("India")
				.pincode("43434343")
				.account_details(account).build();
		
		
		account.setCustomer(customer);
		
		customerRepository.save(customer);
		
		
		String customerName = "DIXIT";String email="sef@gmail.com";String contactNum="3993943943";
		Customer customer1 = customerRepository.findByAny("DIXIT","","");
		Assertions.assertThat(customer1.getCustomerName()).isEqualTo(customerName);
		
		
		Customer customer2 = customerRepository.findByAny("",email,"");
		Assertions.assertThat(customer2.getEmailId()).isEqualTo(email);
		
		Customer customer3 = customerRepository.findByAny("","",contactNum);
		Assertions.assertThat(customer3.getPhoneNumber()).isEqualTo(contactNum);
		
		
	}
	
	
	
	
	@Test
	public void testUpdateCustomerSerializedField() {
		
		
		Customer customer = customerRepository.findById(1L).get();
		customer.setEmailId("ankitz@gmail.com");
		
		Customer customerUpdated = customerRepository.saveAndFlush(customer);
		Assertions.assertThat(customerUpdated.getEmailId()).isEqualTo("ankitz@gmail.com");
		Assertions.assertThat(customer.getCustomerId()).isEqualTo(1L);
	}
	
	@Test
    public void testUpdateCustomerNonSerializedField() {
		
	
		Customer customer = customerRepository.findById(1L).get();
		customer.setEmailId("ankitz@gmail.com");
		
		Customer customerUpdated = customerRepository.saveAndFlush(customer);
		Assertions.assertThat(customerUpdated.getEmailId()).isEqualTo("ankitz@gmail.com");
		Assertions.assertThat(customer.getCustomerId()).isEqualTo(1L);
	}
	
	@Test
	public void testCustomerDeleteById() {
		
		Customer customer = customerRepository.findById(1L).get();
		Assertions.assertThat(customer.isDeletedCustomer()).isEqualTo(false);
		
	}
	
	@Test
	public void testAccountDeleteById() {
		
		Account account = accountRepository.findById(1L).get();
		System.out.println(account.isEnabled());
		Assertions.assertThat(account.isEnabled()).isEqualTo(true);
		
	}
	
	@Test
	public void testDeleteAll() {
		Account account =  Account.builder()
				.accountNumber("dsererdssdser34343")
				.accountType("saving")
				.ifscCode("ifsc224")
				.branchName("cspur")
				.balance(243434)
				.customer(null)
				.isEnabled(true).build();
		
		
		Customer customer = Customer.builder()
				.customerName("scxcx")
				.gender("male")
				.maritalStatus("single")
				.phoneNumber("3993943943")
				.emailId("sef@gmail.com")
				.address("48/922")
				.city("delhi")
				.state("new delhi")
				.country("India")
				.pincode("43434343")
				.account_details(account).build();
		
		
		account.setCustomer(customer);
		
		customerRepository.save(customer);
		
		
		customerRepository.deleteAll();
		
		List<Customer> customers =  customerRepository.findAll();
		Assertions.assertThat(customers.size()).isEqualTo(0);
		
	}
	
}
