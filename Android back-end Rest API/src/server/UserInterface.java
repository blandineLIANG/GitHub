package server;
import org.json.JSONException;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.Post;
import org.restlet.resource.Put;
public interface UserInterface {
	
	@Post("?getallUser")
	public JsonRepresentation allUser(JsonRepresentation jsorp)throws JSONException;
	@Post("?getallObject")
	public JsonRepresentation allObject(JsonRepresentation jsorp)throws JSONException;
	@Post("?getInformation")
	public JsonRepresentation getInformation(JsonRepresentation jsorp)throws JSONException;
	@Post("?rechargeBalles")
	public Representation rechargeBalles(JsonRepresentation jsorp)throws JSONException;
	@Post("?tirerBalles")
	public Representation tirerBalles(JsonRepresentation jsorp)throws JSONException;
	
	@Post("?sendAllInformation")
	public void sendAllInformation(JsonRepresentation jsorp)throws JSONException;
	
}
