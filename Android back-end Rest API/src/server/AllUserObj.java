package server;

import java.security.Timestamp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.data.MediaType;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.ServerResource;

public class AllUserObj {
	
	private static String Newligne=System.getProperty("line.separator");
	
	public void ALLUserObj(){};
	
	public static JsonRepresentation allUser(JsonRepresentation jsorp)throws JSONException{
		
		JSONObject jso = jsorp.getJsonObject();
		int game_id = jso.getInt("game_id");
		 ResultSet resultats = null;
		// ArrayList<Information> array_inf=new ArrayList<Information>();
		 String retour="";
		 JsonRepresentation jr1=null;
		 JSONArray ja = new JSONArray();
		
		 //JsonRepresentation jr2 = null;
		 try {
			   Class.forName("com.mysql.jdbc.Driver");
			   try {
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost/jeu_mobile", "root", "");
			    Statement stmt = con.createStatement();
			    String sql = "select user.ID,user.SCORE,user.BALLES,user.LOGIN,user.USERTYPE,location.LONGITUDE,location.LATITUDE from user join location on user.LOCATION_ID=location.ID where USEGAME_ID = '"+game_id+"'";
			    resultats=stmt.executeQuery(sql);
			    	while (resultats.next()) {
			    	//retour=retour+resultats.getString("ID") +" "+resultats.getString("LOGIN")+" "+ resultats.getString("USERTYPE")+"  "+resultats.getString("SCORE")+" "+ resultats.getString("BALLES")+" "+resultats.getString("LONGITUDE")+
			    		//"  "+resultats.getString("LATITUDE")+Newligne;
			    		int id=resultats.getInt("ID");
			    		String login=resultats.getString("LOGIN");
			    		String userType=resultats.getString("USERTYPE");
			    		int score = resultats.getInt("SCORE");
			    		int balles=resultats.getInt("BALLES");
			    		Double longitude = resultats.getDouble("LONGITUDE");
			    		Double latitude = resultats.getDouble("LATITUDE");
			    		
			    		JSONObject jso1 = new JSONObject();
					    jso1.put("id",id);
						jso1.put("login", login);
						jso1.put("userType", userType);
						jso1.put("longitude", longitude);
						jso1.put("latitude", latitude);
						jso1.put("score",score);
						jso1.put("balles",balles);
						
						
						 ja.put(jso1);
						 
					   			    	}
				stmt.close();
				con.close();
			} catch (SQLException e) {
			    e.printStackTrace();
			   }
			} catch (ClassNotFoundException e) {
			   e.printStackTrace();
			  }
			jr1 = new JsonRepresentation(ja);
			   
			//Representation result = new StringRepresentation(retour,MediaType.TEXT_PLAIN);
			return jr1;
			}
	
public static JsonRepresentation allObject(JsonRepresentation jsorp)throws JSONException{
		
		JSONObject jso = jsorp.getJsonObject();
		int game_id = jso.getInt("game_id");
		 ResultSet resultats = null;
		// ArrayList<Information> array_inf=new ArrayList<Information>();
		 String retour="";
		 JsonRepresentation jr1=null;
		 JSONArray ja = new JSONArray();
		 try {
			   Class.forName("com.mysql.jdbc.Driver");
			   try {
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost/jeu_mobile", "root", "");
			    Statement stmt = con.createStatement();
			    String sql = "select * from object where OBJGAME_ID = '"+game_id+"'";
			    resultats=stmt.executeQuery(sql);
			    	while (resultats.next()) {
			    	//retour=retour+resultats.getString("ID") +" "+resultats.getString("OBJECTTYPE")+" "+ resultats.getString("VALUE")+" "+resultats.getString("LONGITUDE")+
			    			//"  "+resultats.getString("LATITUDE")+Newligne;
			    		int id=resultats.getInt("ID");
			    		String objectType=resultats.getString("OBJECTTYPE");
			    		int value = resultats.getInt("VALUE");
			    		Double longitude = resultats.getDouble("LONGITUDE");
			    		Double latitude = resultats.getDouble("LATITUDE");
			    		//Timestamp starttime = resultats.getTimestamp("STARTTIME");
			    		String duration = resultats.getString("DURATION");
			    		
			    		JSONObject jso1 = new JSONObject();
					    jso1.put("id",id);
						jso1.put("objectType", objectType);
						jso1.put("longitude", longitude);
						jso1.put("latitude", latitude);
						jso1.put("duration",duration);
						ja.put(jso1);
			    	}
				stmt.close();
				con.close();
			} catch (SQLException e) {
			    e.printStackTrace();
			   }
			} catch (ClassNotFoundException e) {
			   e.printStackTrace();
			  }
			
			jr1 = new JsonRepresentation(ja);
			return jr1;
		}
}
