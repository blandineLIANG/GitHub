package sca_bpel;

import org.osoa.sca.annotations.Remotable;

@Remotable
public interface TransactionService {
	public String process(String s);
}
