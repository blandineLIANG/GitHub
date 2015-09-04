package server;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;
 
public class RouterApplication extends Application{
	
	@Override
	public synchronized Restlet createInboundRoot() {
		// Create a router Restlet that routes each call to a new respective instance of resource.
		Router router = new Router(getContext());
		// Defines only two routes
		//router.attach("/users", UserLocation.class);
		router.attach("/admin/createGame",AdminClass.class);
		router.attach("/admin/placeObject",AdminClass.class);
		router.attach("/admin/addUser",AdminClass.class);
		
		router.attach("/admin/getAllUser",AdminClass.class);
		router.attach("/admin/getAllObject",AdminClass.class);
		
		router.attach("/admin/deleteGame",AdminClass.class);
		router.attach("/admin/deleteUser",AdminClass.class);
		router.attach("/admin/deleteObject",AdminClass.class);

		
		router.attach("/user/getInformation",User.class);
		router.attach("/user/rechargeBalles",User.class);
		router.attach("/user/tirerBalles",User.class);
		router.attach("/user/getAllUser",User.class);
		router.attach("/user/getAllObject",User.class);
		router.attach("/user/sendAllInformation",User.class);
		
		return router;
	}
} 