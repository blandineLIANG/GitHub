package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

public class RelocationAuto {
	   private static RelocationAuto instance=null; 
	    private  Connection con = null;
	    
	    private RelocationAuto() { 
	    }  
	     
	    public static RelocationAuto getInstance(){  
	        if (instance == null) {  
	            synchronized (RelocationAuto.class) {  
	                if (instance == null) {  
	                    instance = new RelocationAuto();  
	                }  
	            }  
	        }  
	         
	        return instance;  
	    }
	    
	    public  void relocation(int userId, Double longitude, Double latitude) {  
	    	
	    	try {
				   Class.forName("com.mysql.jdbc.Driver");
				   try {
				    con = DriverManager.getConnection("jdbc:mysql://localhost/jeu_mobile", "root", "");
				    if(!con.isClosed())  
				        System.out.println("sucessful connect with database, begin to relocation user: userId="+userId);
				    Statement stmt = con.createStatement();
				    Date date = new Date();       
				    Timestamp timeStamp = new Timestamp(date.getTime());

				    String sql = "insert into location(LONGITUDE, LATITUDE, USER_ID, TIMESTAMP)values('"+longitude+"','"+latitude+"','"+userId+"','"+timeStamp+"')";
				    stmt.executeUpdate(sql);
				    String sql2 = "update user, location set user.LOCATION_ID = (select ID from location where user.ID=location.USER_ID order by TIMESTAMP desc limit 0,1)";
				    stmt.executeUpdate(sql2);
					stmt.close();
					con.close();
					System.out.println("new location of user: userId="+userId+"    longitude="+longitude+"      latitude="+latitude);
				} catch (SQLException e) {
				    
				    e.printStackTrace();
				   }

				} catch (ClassNotFoundException e) {
				   
				   e.printStackTrace();
				  } 
	    } 
}
