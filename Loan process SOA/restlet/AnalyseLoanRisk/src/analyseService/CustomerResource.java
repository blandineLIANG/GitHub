package analyseService;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
 
public class CustomerResource extends ServerResource { 
	
	Map<String,Customer> listCustomer;
	
	public CustomerResource(){
		listCustomer = new HashMap<String,Customer>();
		listCustomer.put("1",new Customer("john", "jack", "medium"));
		listCustomer.put("2",new Customer("yang", "sheng","low"));
		listCustomer.put("3",new Customer("liang", "xinyue","high"));
	}
 
	/*		
	@Get  
	public String analyseLoan() {
		String lastname = (String) getRequestAttributes().get("lastname");
		String firstname = (String) getRequestAttributes().get("firstname");
		String amount = (String) getRequestAttributes().get("amount");
		
		for (Entry<String,Customer> c:listCustomer.entrySet()){
			if(firstname.equalsIgnoreCase(c.getValue().getFirstName())
					&&lastname.equalsIgnoreCase(c.getValue().getLastName())){
				if(c.getValue().analyse(Integer.parseInt(amount)))
					return "<result>validated</result>";
				return "<result>declined</result>";
			}
		}		
		return "<result>No customer found.</result>";  
	}  */
	@Get
	public String analyseRisk() {
		String lastname = (String) getRequestAttributes().get("lastname");
		String firstname = (String) getRequestAttributes().get("firstname");
		
		
		for (Entry<String,Customer> c:listCustomer.entrySet()){
			if(firstname.equalsIgnoreCase(c.getValue().getFirstName())
					&&lastname.equalsIgnoreCase(c.getValue().getLastName()))
				/*return "<customer>" +
						"<lastname>" + c.getValue().getLastName() + "</lastname>" +
						"<firstname>" + c.getValue().getFirstName() + "</firstname>" +
						"<loanrisk>" + c.getValue().getLoanRisk() + "</loanrisk>" +
						"</customer>";*/
				return c.getValue().getLoanRisk();
		}		
		return "<result>No customer found.</result>";  
	}  
}