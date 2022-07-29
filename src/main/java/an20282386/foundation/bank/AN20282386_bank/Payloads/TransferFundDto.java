package an20282386.foundation.bank.AN20282386_bank.Payloads;




/**
 * @author Ankit Mohapatra
 * 
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferFundDto {

	
	private long from;
	private long to;
	private double amount;
	
}
