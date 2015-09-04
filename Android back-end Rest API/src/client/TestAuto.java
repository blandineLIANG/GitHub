package client;

import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;

import server.AdminClassInterface;
import server.UserInterface;


public class TestAuto {

	public static void main(String[] args) throws JSONException {
	
		ClientResource client = new ClientResource("http://localhost:8183/user/sendAllInformation");
		JSONObject jso = new JSONObject();
		
		jso.put("ID","4");//userId
		jso.put("LATITUDE","4376.546268796076");//new location
		jso.put("LONGITUDE","5097.543819175682");
		jso.put("GAMEID","1");
		jso.put("USERTYPE", "Gangsters"); 
		
		JsonRepresentation jr = new JsonRepresentation(jso);
		
		System.out.println("send all information...");
		client.wrap(UserInterface.class).sendAllInformation(jr);
		ClientResource client1 = new ClientResource("http://localhost:8183/admin/placeObject");
		  JSONObject jso1 = new JSONObject();
		    jso1.put("gameName","jeu1");
			jso1.put("objectType", "money");
			jso1.put("longitude","5097.543003784313");
			jso1.put("latitude","4376.545819135674");
			jso1.put("value","1000000");
			jso1.put("duration","12");
			
			JsonRepresentation jr1 = new JsonRepresentation(jso1);
		
			System.out.println("placing object...");
			Representation result1 = client1.wrap(AdminClassInterface.class).placeObject(jr1);
		
	}

}
