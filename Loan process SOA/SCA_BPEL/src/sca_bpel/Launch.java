package sca_bpel;

import org.apache.tuscany.sca.host.embedded.SCADomain;

public class Launch {
	public final static void main(String[] args) throws Exception {
		SCADomain scaDomain = SCADomain.newInstance("transaction.composite");
		Transaction scaApp = scaDomain.getService(sca_bpel.Transaction.class,"TransactionServiceComponent");
		System.out.println(scaApp.transaction("true"));
		
		scaDomain.close();
	}
}