package analyseService;

public class Customer {
	private String lastName;
	private String firstName;
	private String loanRisk;
	
	public Customer(String lastName, String firstName, String loanRisk){
		this.firstName = firstName;
		this.lastName = lastName;
		this.loanRisk = loanRisk;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLoanRisk() {
		return loanRisk;
	}
	public void setLoanRisk(String loanRisk) {
		this.loanRisk = loanRisk;
	}

	/*public boolean analyse(int loanAmount){
		if(loanAmount>20000&&this.loanRisk.equalsIgnoreCase("high"))
			return false;
		return true;
	}*/
}
