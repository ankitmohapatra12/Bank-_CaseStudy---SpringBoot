package an20282386.foundation.bank.AN20282386_bank.Repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import an20282386.foundation.bank.AN20282386_bank.Models.Account;



/**
 * @author Ankit Mohapatra
 * 
 */

@Repository
@Transactional
public interface AccountRepository extends JpaRepository<Account, Long> {

}
