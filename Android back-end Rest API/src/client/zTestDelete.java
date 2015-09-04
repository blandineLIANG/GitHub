package client;

import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;

import server.AdminClassInterface;

public class zTestDelete {

	public static void main(String[] args) throws JSONException {

		
		/*#######################################################
		 * #####################################################
		 */
		ClientResource client7 = new ClientResource("http://localhost:8183/admin/deleteUser");
		JSONObject jso7 = new JSONObject();
		 jso7.put("LOGIN","xinyue");
		 JsonRepresentation jr7 = new JsonRepresentation(jso7);
		System.out.println("deleting user...");
		Representation result7 = client7.wrap(AdminClassInterface.class).deleteUser(jr7);
		//System.out.println(result7.getText());
		System.out.println("user has been deleted");
		/*##########################################
		 * ######################################
		 */
		ClientResource client8 = new ClientResource("http://localhost:8183/admin/deleteObject");
		JSONObject jso8 = new JSONObject();
		 jso8.put("ID","1");
		 JsonRepresentation jr8 = new JsonRepresentation(jso8);
		System.out.println("deleting object...");
		Representation result8 = client8.wrap(AdminClassInterface.class).deleteObject(jr8);
		//System.out.println(result7.getText());
		System.out.println("object has been  deleted");
		/*##########################################
		 * ######################################
		 */
		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ClientResource client6 = new ClientResource("http://localhost:8183/admin/deleteGame");
		JSONObject jso6 = new JSONObject();
		 jso6.put("GAMENAME","jeu1");
		 JsonRepresentation jr6 = new JsonRepresentation(jso6);
		System.out.println("deleting game...");
		Representation result6 = client6.wrap(AdminClassInterface.class).deleteGame(jr6);
		//System.out.println(result6.getText());
		System.out.println("game has been deleted");
	}

}
