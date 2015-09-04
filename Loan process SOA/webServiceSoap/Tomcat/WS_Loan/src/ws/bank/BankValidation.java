package ws.bank;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BankValidation {
	List<String> list_checkNumber = new ArrayList<String>();
	public BankValidation(){
		
		list_checkNumber.add("123456");
		list_checkNumber.add("654321");
		list_checkNumber.add("112233");
	}

	public String bank_validation(int checknumber){
		Iterator<String> lc = list_checkNumber.iterator();
		while (lc.hasNext()) {
			if(checknumber==Integer.parseInt(lc.next()))
					return "validated";
		}
		return "declined";
	}
}
