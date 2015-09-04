package client;
import java.io.IOException;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;

import server.AdminClassInterface;
public class createGameTest {
public static void main(String[] args) throws JSONException, IOException, InterruptedException {
		
		ClientResource client = new ClientResource("http://localhost:8183/admin/createGame");
		
		Date mydate = new Date();
		java.sql.Date date = new java.sql.Date(mydate.getTime());
		JSONObject jso = new JSONObject();
		jso.put("name","jeu1");
		jso.put("timestamp",date);
		jso.put("city","Paris");
		jso.put("map1x","11.11");
		jso.put("map1y","11.22");
		jso.put("map2x","22.11");
		jso.put("map2y","22.22");
		
		JsonRepresentation jr = new JsonRepresentation(jso);
		System.out.println("creating game...");
		JsonRepresentation result = client.wrap(AdminClassInterface.class).createGame(jr);
		JSONObject jsss =  result.getJsonObject();
		System.out.println("game has been created");
		}
}
