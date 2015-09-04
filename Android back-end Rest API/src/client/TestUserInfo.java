package client;
import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;

import server.AdminClassInterface;
import server.UserInterface;
public class TestUserInfo {
	
	public static void main(String[] args) throws JSONException, IOException, InterruptedException {
		ClientResource client = new ClientResource("http://localhost:8183/user/getInformation");
		
		JSONObject jso = new JSONObject();
		jso.put("login","ying");
		jso.put("usegame_id", "1");
		JsonRepresentation jr = new JsonRepresentation(jso);
		//Representation result = client.put(client);  
		System.out.println("get infromation of ying...");
		JsonRepresentation result = client.wrap(UserInterface.class).getInformation(jr);
		System.out.println(result.getText());
	
		
	}

}
