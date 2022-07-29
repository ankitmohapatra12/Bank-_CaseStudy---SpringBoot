package an20282386.foundation.bank.AN20282386_bank.Payloads;

import an20282386.foundation.bank.AN20282386_bank.Models.Customer;



/**
 * @author Ankit Mohapatra
 * 
 */

public class AccountDto {

	
	private long accountId;
	
	private String accountType;
	
	private String accountNumber;
	
	private String ifscCode;
	
	private String branchName;
	
	private double balance;
	
    private Customer customer;
	
	private boolean isEnabled;
	
	

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public AccountDto(long accountId, String accountType, String accountNumber, String ifscCode, String branchName,
			double balance, Customer customer, boolean isEnabled) {
		super();
		this.accountId = accountId;
		this.accountType = accountType;
		this.accountNumber = accountNumber;
		this.ifscCode = ifscCode;
		this.branchName = branchName;
		this.balance = balance;
		this.customer = customer;
		this.isEnabled = isEnabled;
	}

	public AccountDto() {
		super();
	}
	
	
	
	

}
