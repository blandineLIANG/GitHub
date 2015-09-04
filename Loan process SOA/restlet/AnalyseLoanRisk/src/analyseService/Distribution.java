package analyseService;

import org.restlet.Component;
import org.restlet.data.Protocol;
 
public class Distribution {
 
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// Create a new Restlet component and add a HTTP server connector to it  
		Component component = new Component();  
		component.getServers().add(Protocol.HTTP, 8186); 
 
		// Attach the application to the component and start it  
		//component.getDefaultHost().attach("/analyse/lastname={lastname}&&firstname={firstname}&&amount={amount}", CustomerResource.class);
		component.getDefaultHost().attach("/analyse/lastname={lastname}&&firstname={firstname}", CustomerResource.class);  
		component.start();  
	}	 
}