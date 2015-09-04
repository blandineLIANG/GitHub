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

import com.mysql.jdbc.PreparedStatement;

public class User extends ServerResource implements UserInterface {
	
	
	
public JsonRepresentation getInformation(JsonRepresentation jsorp) throws JSONException {
		
		JSONObject jso = jsorp.getJsonObject();
    	//String id = jso.getString("id");
		String login = jso.getString("login");
		String usegame_id = jso.getString("usegame_id");
		int score = 0;
		int balles = 0;
		try {
			   Class.forName("com.mysql.jdbc.Driver");
			   try {
			    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/jeu_mobile", "root", "");
			    Statement stmt = con.createStatement();
			    String sql = "select SCORE,BALLES from user where LOGIN='"+login+"' and USEGAME_ID='"+usegame_id+"'";
			    ResultSet rs = stmt.executeQuery(sql);
				
				while(rs.next()){
					
					 score = rs.getInt("SCORE");
					 balles = rs.getInt("BALLES");
					}
			   
				stmt.close();
				con.close();
			} catch (SQLException e) {
			    
			    e.printStackTrace();
			   }
			} catch (ClassNotFoundException e) {
			   e.printStackTrace();
			  }
			System.out.println(score + "  " + balles);
			JSONObject jso1 = new JSONObject();
		    jso1.put("usegame_id",usegame_id);
			jso1.put("login", login);
			jso1.put("score",score);
			jso1.put("balles",balles);
			
			JsonRepresentation jr1 = new JsonRepresentation(jso1);
			//Representation result = new StringRepresentation("get it " ,MediaType.TEXT_PLAIN);
		
		return jr1;

	}

public  Representation rechargeBalles(JsonRepresentation jsorp) throws JSONException {
		
		JSONObject jso = jsorp.getJsonObject();
    	//String id = jso.getString("id");
		String login = jso.getString("login");
		String usegame_id = jso.getString("usegame_id");
		int location_id = 0;
		int balles = 0;
		double longitude = 0.0;
		double latitude = 0.0;
		String objectType="  ";
		Representation result=new StringRepresentation("There is no recharge zone in this range." ,MediaType.TEXT_PLAIN);
		try {
			   Class.forName("com.mysql.jdbc.Driver");
			   try {
			    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/jeu_mobile", "root", "");
			    Statement stmt = con.createStatement();
			    String sql = "SELECT LONGITUDE, LATITUDE, BALLES FROM user, location WHERE user.LOCATION_ID = location.ID AND user.LOGIN = '"+login+"' AND user.USEGAME_ID =  '"+usegame_id+"' LIMIT 0 , 30";
			    ResultSet rs = stmt.executeQuery(sql);
				while(rs.next()){
					
					 longitude = rs.getDouble("LONGITUDE");
					 latitude = rs.getDouble("LATITUDE");
					 balles = rs.getInt("BALLES");
					}
				JSONObject jso1 = CheckPosition.findNeighPosition(longitude, latitude);
				Double minlat=jso1.getDouble("minlat");
				Double maxlat=jso1.getDouble("maxlat");
				Double minlng=jso1.getDouble("minlng");
				Double maxlng=jso1.getDouble("maxlng");
				//System.out.println(minlat +" " + maxlat +" "+ minlng+ " " + maxlng);
				
				String sql1 = "SELECT OBJECTTYPE FROM object WHERE ((LATITUDE BETWEEN '"+minlat+"' AND '"+maxlat+"') AND (LONGITUDE BETWEEN '"+minlng+"' AND '"+maxlng+"')) AND OBJGAME_ID='"+usegame_id+"'";
				ResultSet rs1 = stmt.executeQuery(sql1);
				while(rs1.next()){
					
					 objectType=rs1.getString("OBJECTTYPE");
					 if (objectType.equals("Zone De Recharge"))
						 objectType="success";
					     balles++;
					 
					}
				//System.out.println(objectType);
				if(objectType.equals("success"))
					{String sql2 = " update user set BALLES='"+balles+"' where LOGIN = '"+login+"' AND USEGAME_ID =  '"+usegame_id+"'";
					stmt.executeUpdate(sql2);
					result = new StringRepresentation("recharge finished, balles plus 1." ,MediaType.TEXT_PLAIN);
					
					}
				
				
				
				stmt.close();
				con.close();
			} catch (SQLException e) {
			    
			    e.printStackTrace();
			   }
			} catch (ClassNotFoundException e) {
			   e.printStackTrace();
			  }
			//System.out.println(balles);
			
			
	    
		return  result;

	}

@Override
public Representation tirerBalles(JsonRepresentation jsorp) throws JSONException {
	// TODO Auto-generated method stub
	JSONObject jso = jsorp.getJsonObject();
	//String id = jso.getString("id");
	int id = jso.getInt("id");
    Double longitude = jso.getDouble("longitude");
	Double latitude = jso.getDouble("latitude");
	int balles=0;
	int score=0;
	String userType=" ";
	String userType1=" ";/* The type of the user who is in our search range*/
	int id1=0;/* ID of this user in our search range*/
	int score1=0;/*score of the user in our search range*/
	String login = null;
	Representation result=new StringRepresentation("There is no one in the range",MediaType.TEXT_PLAIN);
	try {
		   Class.forName("com.mysql.jdbc.Driver");
		   try {
		    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/jeu_mobile", "root", "");
		    Statement stmt = con.createStatement();
		    String sql = "SELECT USERTYPE,BALLES,SCORE FROM user WHERE ID= '"+id+"'";
		    ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){			
				
				userType = rs.getString("USERTYPE");
				balles = rs.getInt("BALLES");
				score = rs.getInt("SCORE");
				
			}
			if (userType.equals("Gangster") && balles > 0)
			{
				JSONObject jso1 = CheckPosition.findNeighPosition(longitude, latitude);
				Double minlat=jso1.getDouble("minlat");
				Double maxlat=jso1.getDouble("maxlat");
				Double minlng=jso1.getDouble("minlng");
				Double maxlng=jso1.getDouble("maxlng");
				//System.out.println(minlat +" " + maxlat +" "+ minlng+ " " + maxlng);
				String sql1 = "SELECT USERTYPE, u.ID FROM user u,location l WHERE ((LATITUDE BETWEEN '"+minlat+"' AND '"+maxlat+"') AND (LONGITUDE BETWEEN '"+minlng+"' AND '"+maxlng+"')) AND u.LOCATION_ID=L.ID";
				ResultSet rs1 = stmt.executeQuery(sql1);
				
				while(rs1.next()){
					
					 userType1 = rs1.getString("USERTYPE");
					 id1= rs1.getInt("ID");
					 if (userType1.equals("Police"))
					 {
						result = new StringRepresentation("Police est fige pendant 90s",MediaType.TEXT_PLAIN);
						balles--;
						String sql5="update user set BALLES= '"+balles+"' where ID= '"+id+"'";
						stmt.executeUpdate(sql5);
					 }
					 
				}
					
			
			}
			else if (userType.equals("Police"))
			{
				JSONObject jso1 = CheckPosition.findNeighPosition(longitude, latitude);
				Double minlat=jso1.getDouble("minlat");
				Double maxlat=jso1.getDouble("maxlat");
				Double minlng=jso1.getDouble("minlng");
				Double maxlng=jso1.getDouble("maxlng");
				//System.out.println(minlat +" " + maxlat +" "+ minlng+ " " + maxlng);
				String sql2 = "SELECT USERTYPE,u.ID,TIMEATTRAPE,SCORE FROM user u,location l WHERE ((LATITUDE BETWEEN '"+minlat+"' AND '"+maxlat+"') AND (LONGITUDE BETWEEN '"+minlng+"' AND '"+maxlng+"')) AND u.LOCATION_ID=L.ID";
				ResultSet rs2 = stmt.executeQuery(sql2);
				while(rs2.next())
				{
					
					 userType1 = rs2.getString("USERTYPE");
					 id1= rs2.getInt("ID");
					 Timestamp ts=rs2.getTimestamp("TIMEATTRAPE");
					 score1 = rs2.getInt("SCORE");
					 if (userType1.equals("Gangster"))
					 {
						 
						 Date date = new Date();       
						 Timestamp ts1 = new Timestamp(date.getTime());
						 Double a = CheckTimeDiff.calculTimeDiff(ts1, ts);
						 if (a > 8.0)
						 {
							
							 score1 = score1 / 2; 
							 score = score + score1;
							 Statement stmt1 = con.createStatement();	 
							 String sql3=" update user set SCORE = '"+score1+"' where ID = '"+id1+"' "; 
							 stmt1.executeUpdate(sql3);
							 String sql4=" update user set SCORE = '"+score+"' where ID = '"+id+"' ";
							 stmt1.executeUpdate(sql4);
							 String sql6= "update user set TIMEATTRAPE = '"+ts1+"' where ID = '"+id1+"'; ";
							 stmt1.executeUpdate(sql6);
							// System.out.println(id1);
							 String sql7="select LOGIN from user where ID = '"+id1+"' ";
							 ResultSet rs7=stmt1.executeQuery(sql7);
							 while (rs7.next())
							 {
								  login=rs7.getString("LOGIN");
							 }
							
							result = new StringRepresentation(login + " est tire par le police",MediaType.TEXT_PLAIN);
						 }
						 else {
							 result = new StringRepresentation("Vous ne pouvez pas tirer mtn",MediaType.TEXT_PLAIN);
						      
						       }
					 }
				}
				
					
			
			}
				
			stmt.close();
			con.close();
		} catch (SQLException e) {
		    
		    e.printStackTrace();
		   }
		} catch (ClassNotFoundException e) {
		   e.printStackTrace();
		  }
	
	return result;
}

@Override
public JsonRepresentation allUser(JsonRepresentation jsorp)
		throws JSONException {
	
	JsonRepresentation result=AllUserObj.allUser(jsorp);
	return result;
}

@Override
public JsonRepresentation allObject(JsonRepresentation jsorp) throws JSONException {
	
	JsonRepresentation result=AllUserObj.allObject(jsorp);
	return result;
}

@Override
public void sendAllInformation(JsonRepresentation jsorp) throws JSONException {
	JSONObject jso = jsorp.getJsonObject();
	
	int id = jso.getInt("ID");
//	String login = jso.getString("LOGIN");
	String userType = jso.getString("USERTYPE");
//	String location_id = jso.getString("LOCATION_ID");
//	String score = jso.getString("SCORE");
//	String balles = jso.getString("BALLES");
//	String userGame_id = jso.getString("USERGAME_ID");
	Double longitude = jso.getDouble("LONGITUDE");
	Double latitude = jso.getDouble("LATITUDE");
	int gameId = jso.getInt("GAMEID");
	
	System.out.println("----------------------relocation auto------------------------");
	RelocationAuto relocation = RelocationAuto.getInstance();
	relocation.relocation(id, longitude, latitude);
	
	System.out.println("----------------------operation auto-----------------------------");
	OperationAuto operation = OperationAuto.getInstance();
	operation.operation(id,gameId, longitude, latitude, userType);
	
}
	
	
}
