package sca_bpel;
import org.osoa.sca.annotations.Reference;
import org.osoa.sca.annotations.Service;

@Service(Transaction.class)
public class Transaction {
	@Reference
	private TransactionService transactionService;
	
	public String transaction(String s){
		return transactionService.process(s);
	}
}
