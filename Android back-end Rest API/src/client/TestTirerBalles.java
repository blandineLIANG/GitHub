package client;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;

import server.UserInterface;

public class TestTirerBalles {
	public static void main(String[] args) throws JSONException, IOException, InterruptedException {
		ClientResource client = new ClientResource("http://localhost:8183/user/tirerBalles");
		//Add a user xinyue police, houssem ganster pour le test 
		JSONObject jso = new JSONObject();
		int id=1;
		jso.put("id",id);
		jso.put("longitude","5.5" );
		jso.put("latitude", "6.6");
		
		JsonRepresentation jr = new JsonRepresentation(jso);
		//Representation result = client.put(client);  
		System.out.println("tirer une balle...");
		Representation result = client.wrap(UserInterface.class).tirerBalles(jr);
		System.out.println(result.getText());
	
		
	}
}
