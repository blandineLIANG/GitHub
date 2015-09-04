
package client;

import java.io.IOException;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;

import server.*;
public class TestAdd {

	/**
	 * @param args
	 * @throws JSONException 
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws JSONException, IOException, InterruptedException {
		Date mydate = new Date();
		java.sql.Date date = new java.sql.Date(mydate.getTime());
		
		
	   /*####################################################""
	    * ################################################### */
		ClientResource client1 = new ClientResource("http://localhost:8183/admin/placeObject");
		JSONObject jso1 = new JSONObject();
		jso1.put("objectType", "Zone De Recharge");
		jso1.put("longitude","5454.5438");
		jso1.put("latitude","4547.5396");
		jso1.put("value","0");
		jso1.put("startTime",date);
		jso1.put("duration","120");
		jso1.put("gameName", "jeu1");
		JsonRepresentation jr1 = new JsonRepresentation(jso1);
		System.out.println("placing object...");
		Representation result1 = client1.wrap(AdminClassInterface.class).placeObject(jr1);
		System.out.println("object has been  placed");
		/*####################################################""
		    * ################################################### */
		ClientResource client2 = new ClientResource("http://localhost:8183/admin/addUser");
		JSONObject jso2 = new JSONObject();
		jso2.put("timestamp",date);
		jso2.put("longitude", "5.5");
		jso2.put("latitude", "6.6");
		jso2.put("login", "luc");
		jso2.put("type","Police");
		jso2.put("score","10000");
		jso2.put("balles","1000");
		jso2.put("gilet", 0);
		jso2.put("gameName","jeu1");
		
		JsonRepresentation jr2 = new JsonRepresentation(jso2);
		System.out.println("adding user...");
		client2.wrap(AdminClassInterface.class).addUser(jr2);
		System.out.println("user     1    ");
		System.out.println("user has been added");
		/*####################################################""
		    * ################################################### */
		ClientResource client3 = new ClientResource("http://localhost:8183/admin/addUser");
		JSONObject jso3 = new JSONObject();
		
		 jso3.put("timestamp",date);
			jso3.put("longitude", "5454.5444");
			jso3.put("latitude", "4547.54");
			jso3.put("login", "ying");
			jso3.put("type","Gangster");
			jso3.put("score","0");
			jso3.put("balles","0");
			jso3.put("gilet",0);
			jso3.put("gameName","jeu1");
			JsonRepresentation jr3 = new JsonRepresentation(jso3);
		System.out.println("adding user...");
		client3.wrap(AdminClassInterface.class).addUser(jr3);
		System.out.println("user    2    ");
		System.out.println("user has been added");
		/*####################################################""
		    * ################################################### */
		ClientResource client6 = new ClientResource("http://localhost:8183/admin/addUser");
		JSONObject jso6 = new JSONObject();
		jso6.put("timestamp",date);
		jso6.put("longitude", "5.50001");
		jso6.put("latitude", "6.60001");
		jso6.put("login", "houssem");
		jso6.put("type","Gangster");
		jso6.put("score","20000");
		jso6.put("balles","3");
		jso6.put("gilet", 0);
		jso6.put("gameName","jeu1");
		
		JsonRepresentation jr6 = new JsonRepresentation(jso6);
		System.out.println("adding user...");
		client6.wrap(AdminClassInterface.class).addUser(jr6);
		System.out.println("user     3   ");
		System.out.println("user has been added");
		
		ClientResource client7 = new ClientResource("http://localhost:8183/admin/placeObject");
		  JSONObject jso7 = new JSONObject();
		    jso7.put("gameName","jeu1");
			jso7.put("objectType", "money");
			jso7.put("longitude","5097.543003784313");
			jso7.put("latitude","4376.545819135674");
			jso7.put("value","1000000");
			jso7.put("duration","12");
			
			JsonRepresentation jr7 = new JsonRepresentation(jso7);
		
			System.out.println("placing object...");
			Representation result7 = client7.wrap(AdminClassInterface.class).placeObject(jr7);
			System.out.println("object has been placed...");
			ClientResource client8= new ClientResource("http://localhost:8183/admin/placeObject");
			  JSONObject jso8 = new JSONObject();
			    jso8.put("gameName","jeu1");
				jso8.put("objectType", "gilet");
				jso8.put("longitude","5097.543003684313");
				jso8.put("latitude","4376.545819135774");
				jso8.put("value","1");
				jso8.put("duration","120");
				
				JsonRepresentation jr8 = new JsonRepresentation(jso8);
			
				System.out.println("placing object...");
				Representation result8 = client8.wrap(AdminClassInterface.class).placeObject(jr8);
				System.out.println("object has been placed...");
				ClientResource client9 = new ClientResource("http://localhost:8183/admin/addUser");
				JSONObject jso9 = new JSONObject();
				jso9.put("timestamp",date);
				jso9.put("longitude", "456.50001");
				jso9.put("latitude", "789.60001");
				jso9.put("login", "xinyue");
				jso9.put("type","Gangster");
				jso9.put("score","20000");
				jso9.put("balles","3");
				jso9.put("gilet", 0);
				jso9.put("gameName","jeu1");
				
				JsonRepresentation jr9 = new JsonRepresentation(jso9);
				System.out.println("adding user...");
				client9.wrap(AdminClassInterface.class).addUser(jr9);
				System.out.println("user     4    ");
				System.out.println("user added");
		/*####################################################""
	    * ################################################### */
		/*ClientResource client6 = new ClientResource("http://localhost:8183/admin/deleteGame");
		JSONObject jso6 = new JSONObject();
		 jso6.put("GAMENAME",gameName);
		 JsonRepresentation jr6 = new JsonRepresentation(jso6);
		System.out.println("deleting game...");
		Representation result6 = client6.wrap(AdminClassInterface.class).deleteGame(jr6);
		//System.out.println(result6.getText());
		System.out.println("game deleted");
		/*#######################################################
		 * #####################################################
		 */
		/*ClientResource client7 = new ClientResource("http://localhost:8183/admin/deleteUser");
		JSONObject jso7 = new JSONObject();
		 jso7.put("LOGIN","hosm");
		 JsonRepresentation jr7 = new JsonRepresentation(jso7);
		System.out.println("deleting user...");
		Representation result7 = client7.wrap(AdminClassInterface.class).deleteUser(jr7);
		//System.out.println(result7.getText());
		System.out.println("user deleted");
		/*##########################################
		 * ######################################
		 */
		/*ClientResource client8 = new ClientResource("http://localhost:8183/admin/deleteObject");
		JSONObject jso8 = new JSONObject();
		 jso8.put("ID","8");
		 JsonRepresentation jr8 = new JsonRepresentation(jso8);
		System.out.println("deleting object...");
		Representation result8 = client8.wrap(AdminClassInterface.class).deleteObject(jr8);
		//System.out.println(result7.getText());
		System.out.println("object deleted");
		/*#######################################
		 * #######################################
		 */
		ClientResource clientb = new ClientResource("http://localhost:8183/admin/getAllUser");
		
		JSONObject jsob = new JSONObject();
		jsob.put("game_id","1");
		JsonRepresentation jrb = new JsonRepresentation(jsob);
		Representation resultb = clientb.wrap(AdminClassInterface.class).allUser(jrb);
		System.out.println("showing  users...");
		System.out.println(resultb.getText());
		System.out.println("users  have been shown...");
		/*#######################################
		 * #######################################
		 */
		 ClientResource clienta = new ClientResource("http://localhost:8183/admin/getAllObject");
		
		JSONObject jsoa = new JSONObject();
		jsoa.put("game_id","1");
		JsonRepresentation jra = new JsonRepresentation(jsoa);
		JsonRepresentation resulta = clienta.wrap(AdminClassInterface.class).allObject(jra);
		System.out.println("Showing  objects...");
		System.out.println(resulta.getText());
		System.out.println("Objects have been shown....");
		 
	}

}
