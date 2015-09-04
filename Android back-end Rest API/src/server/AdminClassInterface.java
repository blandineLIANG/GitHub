package server;

import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
//import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Put;
import org.json.*;



public interface AdminClassInterface  {
	
	    
	@Put ("?createGame")
	public JsonRepresentation createGame(JsonRepresentation jsorp)throws JSONException;
	@Put("?placeObject")
	public Representation placeObject(JsonRepresentation jsorp)throws JSONException;
	@Put("?addUser")
	public Representation addUser(JsonRepresentation jsorp)throws JSONException;
	
	@Post("?getallUser")
	public JsonRepresentation allUser(JsonRepresentation jsorp)throws JSONException;
	@Post("?getallObject")
	public JsonRepresentation allObject(JsonRepresentation jsorp)throws JSONException;
	
	@Delete("?deleteGame")
	public Representation deleteGame(JsonRepresentation jsorp)throws JSONException;
	@Delete("?deletUser")
	public Representation deleteUser(JsonRepresentation jsorp)throws JSONException;
	@Delete("?deletObjet")
	public Representation deleteObject(JsonRepresentation jsorp)throws JSONException;
	
}