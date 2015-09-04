package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.data.MediaType;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.*;

public class AdminClass extends ServerResource implements AdminClassInterface {
	private static  Connection con = null;
	private static String url="jdbc:mysql://localhost/jeu_mobile";
	private static ResultSet résultats = null;
	

	public JsonRepresentation createGame(JsonRepresentation jsorp) throws JSONException {
		
    	JSONObject jso = jsorp.getJsonObject();
    	 JSONObject jso1 = new JSONObject();
    	//String id = jso.getString("id");
		String name = jso.getString("name");
		String city = jso.getString("city");
		String map1x = jso.getString("map1x");
		String map1y = jso.getString("map1y");
		String map2x = jso.getString("map2x");
		String map2y = jso.getString("map2y");
		Date date = new Date();       
		Timestamp timeStamp = new Timestamp(date.getTime());
		try {
			   Class.forName("com.mysql.jdbc.Driver");
			   try {
			    con = DriverManager.getConnection("jdbc:mysql://localhost/jeu_mobile", "root", "");
			    Statement stmt = con.createStatement();
			    String sql = "insert into game (NAME,TIMESTAMP,CITY,MAP1X,MAP1Y,MAP2X,MAP2Y) values('"+name+"','"+timeStamp+"','"+city+"','"+map1x+"','"+map1y+"','"+map2x+"','"+map2y+"')";
			    stmt.executeUpdate(sql);
			    sql="select ID from game where NAME='"+name+"'";
			    résultats=stmt.executeQuery(sql);
			    résultats.next();
			    int id =résultats.getInt("ID");
			   
			    jso1.put("id",id);
			    jso1.put("gameName", name);
				stmt.close();
				con.close();
			} catch (SQLException e) {
			    
			    e.printStackTrace();
			   }
			} catch (ClassNotFoundException e) {
			   e.printStackTrace();
			  }
		JsonRepresentation jr = new JsonRepresentation(jso1);
		//Representation result = new StringRepresentation("A new game has been created",MediaType.TEXT_PLAIN);
		return jr;
	} 		

	
	public Representation placeObject(JsonRepresentation jsorp) throws JSONException {
		JSONObject jso = jsorp.getJsonObject();
		//String id = jso.getString("id");
		String gameName=jso.getString("gameName");
		String objectType = jso.getString("objectType");
		String latitude = jso.getString("latitude");
		String longitude = jso.getString("longitude");
		String value = jso.getString("value");
		Date date = new Date();       
		Timestamp timeStamp = new Timestamp(date.getTime());
		String duration = jso.getString("duration");
		//String tableName="anaconda";
		int gameId = 0;
		try {
			   Class.forName("com.mysql.jdbc.Driver");
			   try {
			    con = DriverManager.getConnection("jdbc:mysql://localhost/jeu_mobile", "root", "");
			    Statement stmt = con.createStatement();
			    String sql1="select ID from game where name='"+gameName+"'";
/*need to check*/  ResultSet rs=stmt.executeQuery(sql1);
				while(rs.next()){
					
					gameId = rs.getInt("ID");
					}

			    
				String sql2 = "insert into object (OBJECTTYPE,LATITUDE,LONGITUDE,VALUE,STARTTIME,DURATION,OBJGAME_ID)values('"+objectType+"','"+latitude+"','"+longitude+"','"+value+"','"+timeStamp+"','"+duration+"','"+gameId+"')";
			    stmt.executeUpdate(sql2);
			 
				stmt.close();
				con.close();
			} catch (SQLException e) {
			    
			    e.printStackTrace();
			   }

			} catch (ClassNotFoundException e) {
			   
			   e.printStackTrace();
			  }
		Representation result = new StringRepresentation("object placed",MediaType.TEXT_PLAIN);
		return result;
	}


	public Representation addUser(JsonRepresentation jsorp)throws JSONException{
		
		JSONObject jso = jsorp.getJsonObject();
    	String gameName = jso.getString("gameName");
     	String login = jso.getString("login");
    	String type = jso.getString("type");
    	String latitude = jso.getString("latitude");
    	String longitude = jso.getString("longitude");
    	String score = jso.getString("score");
    	String balles = jso.getString("balles");
    	Date date = new Date();       
		Timestamp timeStamp = new Timestamp(date.getTime());
		int gameId=0;
    	try {
			   Class.forName("com.mysql.jdbc.Driver");
			   try {
			    con = DriverManager.getConnection("jdbc:mysql://localhost/jeu_mobile", "root", "");
			    Statement stmt = con.createStatement();
			    /*select the game_id*/
			    String sql1="select ID from game where name='"+gameName+"'";
			    ResultSet rs = stmt.executeQuery(sql1);
				
				while(rs.next()){
					
					gameId = rs.getInt("ID");
					}

                /*create a new user without location information*/
				String sql2 = "insert into user (LOGIN,USERTYPE,USEGAME_ID,SCORE,BALLES,TIMEATTRAPE) values('"+login+"','"+type+"','"+gameId+"','"+score+"','"+balles+"','"+timeStamp+"')";
				stmt.executeUpdate(sql2);
				
				/*select the user's ID*/
				String sql3 = "select ID from user where LOGIN='"+login+"'";
				ResultSet rs1 = stmt.executeQuery(sql3);
				int user_id = 0;
				while(rs1.next()){
					
					user_id = rs1.getInt("ID");
					}
				/*creat a location record with user_id information*/
				String sql4 = "insert into location (LONGITUDE,LATITUDE,TIMESTAMP,USER_ID) values('"+longitude+"','"+latitude+"','"+timeStamp+"','"+user_id+"')";
				stmt.executeUpdate(sql4);
				
				/*select the location_id*/
				String sql5 = "select ID from location where USER_ID='"+user_id+"'";
				ResultSet rs2 = stmt.executeQuery(sql5);
				int location_id = 0;
				while(rs2.next()){
					
					location_id = rs2.getInt("ID");
					}
				
				/*update the user table with current locaiont_id*/
				String sql6 = "update user set LOCATION_ID='"+location_id+"' where ID='"+user_id+"'";
				stmt.executeUpdate(sql6);
				
			    
				stmt.close();
				con.close();
			} catch (SQLException e) {
			    
			    e.printStackTrace();
			   }
			} catch (ClassNotFoundException e) {
			   e.printStackTrace();
			  }
		Representation result = new StringRepresentation("User added",MediaType.TEXT_PLAIN);
		return result;
	}
	

	public JsonRepresentation allUser(JsonRepresentation jsorp)
		throws JSONException {
	
	JsonRepresentation result=AllUserObj.allUser(jsorp);
	return result;
}


	public JsonRepresentation allObject(JsonRepresentation jsorp) throws JSONException {
	
	JsonRepresentation result=AllUserObj.allObject(jsorp);
	return result;
}


	public Representation deleteGame(JsonRepresentation jsorp) throws JSONException {
	
	JSONObject jso = jsorp.getJsonObject();
	String gameName=jso.getString("GAMENAME");
	
	try {
		   Class.forName("com.mysql.jdbc.Driver");
		   try {
		    con = DriverManager.getConnection(url, "root", "");
		    Statement stmt = con.createStatement();
		    String sql = "select ID from game where NAME='"+gameName+"'";
		    résultats=stmt.executeQuery(sql);
		    résultats.next();
		    String id=résultats.getString("ID");
		
		    sql="delete from game where ID='"+id+"'";
		    stmt.executeUpdate(sql);
		    
			stmt.close();
			con.close();
		} catch (SQLException e) {
		    e.printStackTrace();
		   }
		} catch (ClassNotFoundException e) {
		   e.printStackTrace();
		  }
	return null;
}


	public Representation deleteUser(JsonRepresentation jsorp) throws JSONException {
	JSONObject jso = jsorp.getJsonObject();
	String login=jso.getString("LOGIN");
	
	try {
		   Class.forName("com.mysql.jdbc.Driver");
		   try {
		    con = DriverManager.getConnection(url, "root", "");
		    Statement stmt = con.createStatement();
		    String sql = "select ID from user where LOGIN='"+login+"'";
		    résultats=stmt.executeQuery(sql);
		    résultats.next();
		    String id=résultats.getString("ID");
		    sql = "delete from location  where USER_ID='"+id+"'";
		    stmt.executeUpdate(sql);
		    sql = "delete from user  where ID='"+id+"'";
		    stmt.executeUpdate(sql);
			stmt.close();
			con.close();
		} catch (SQLException e) {
		    
		    e.printStackTrace();
		   }
		} catch (ClassNotFoundException e) {
		   e.printStackTrace();
		  }
	return null;
}


	public Representation deleteObject(JsonRepresentation jsorp)
		throws JSONException {
	JSONObject jso = jsorp.getJsonObject();
	String id=jso.getString("ID");
	try {
		   Class.forName("com.mysql.jdbc.Driver");
		   try {
		    con = DriverManager.getConnection(url, "root", "");
		    Statement stmt = con.createStatement();
		    String sql = "delete from object where ID='"+id+"'";
		    stmt.executeUpdate(sql);
			stmt.close();
			con.close();
		} catch (SQLException e) {
		    
		    e.printStackTrace();
		   }
		} catch (ClassNotFoundException e) {
		   e.printStackTrace();
		  }
	return null;
}


}


