package an20282386.foundation.bank.AN20282386_bank.Repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import an20282386.foundation.bank.AN20282386_bank.Models.Customer;



/**
 * @author Ankit Mohapatra
 * 
 */

@Repository
@Transactional
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	
	@Query(value= "SELECT * from customers c where c.is_deleted = 0",nativeQuery = true)
	List<Customer> findAllNotDeleted();

	@Query(value= "SELECT * from customers c where c.customer_name = ? or c.email_id = ? or contact_number = ?",nativeQuery = true)
	Customer findByAny(String customerName,String email,String contactNum);

	
	@Modifying
	@Query(value = "update customers c , accounts a set a.is_enabled=0 ,c.is_deleted = 1 where c.customer_id in (:ids) and a.customer_id in (:ids);",nativeQuery = true)
	int archieveCustomerAccounts(@Param("ids") List<Long> ids);
	
	

	@Modifying
	@Query(value = "update customers c , accounts a set a.is_enabled=1 ,c.is_deleted = 0 where c.customer_id in (:ids) and a.customer_id in (:ids);",nativeQuery = true)
	int enableCustomerAccounts(@Param("ids") List<Long> ids);
	
	
	

}
