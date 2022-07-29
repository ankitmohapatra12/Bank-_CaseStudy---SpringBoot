package an20282386.foundation.bank.AN20282386_bank.Models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



/**
 * @author Ankit Mohapatra
 * 
 */

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "accounts")
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "account_id")
	private long accountId;
	
	@Column(name = "account_type")
	private String accountType;
	
	@Column(name = "account_number")
	private String accountNumber;
	
	@Column(name = "ifsc_code")
	private String ifscCode;
	
	@Column(name = "branch_name")
	private String branchName;
	
	@Column(name = "balance")
	private double balance;
	
	@JsonIgnore
	@OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL,optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
	
	@Column(name =  "is_enabled")
	private boolean isEnabled;

	
	
	
	
}
