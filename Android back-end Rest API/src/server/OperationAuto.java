package server;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

public class OperationAuto {
	private static OperationAuto instance=null; 
    private Connection con = null;
    
    private OperationAuto() { 
    }  
     
    public static OperationAuto getInstance(){  
        if (instance == null) {  
            synchronized (OperationAuto.class) {  
                if (instance == null) {  
                    instance = new OperationAuto();  
                }  
            }  
        }  
         
        return instance;  
    }
    
    public void operation(int userId,int gameId, Double longitude, Double latitude, String userType) throws JSONException{
    	JSONObject jsoposition=CheckPosition.findNeighPosition(longitude, latitude);
    	Double minlat=jsoposition.getDouble("minlat");
    	Double maxlat=jsoposition.getDouble("maxlat");
    	Double minlng=jsoposition.getDouble("minlng");
    	Double maxlng=jsoposition.getDouble("maxlng");
    	System.out.println("this is the area that user: userId="+userId+" that can fetch money and gilet pare-balle");
    	System.out.println("area : minlat = "+ minlat+";    maxlat = "+maxlat+";    minlng = "+minlng+";    maxlng = "+maxlng);
    	    	
    	ResultSet rsUserMoney;
    	ResultSet rsUserGilet;
    	ResultSet rsUserScore;
    	ResultSet rsGameStart;
    	ResultSet rsWinner;
    	int idObject=0; String valueObject=null;
    	
    	try {
			   Class.forName("com.mysql.jdbc.Driver");
			   try {
			    con = DriverManager.getConnection("jdbc:mysql://localhost/jeu_mobile", "root", "");
			    if(!con.isClosed())  
			        System.out.println("sucessful connect with database");
			    Statement stmt = con.createStatement();
			    Statement stmt1 = con.createStatement();
				
			    if(userType.equals("Gangsters")){
			    
			    String sqlObjectMoney = "select * from object where  OBJECTTYPE = 'money' and object.OBJGAME_ID ='"+gameId+"' and ( LATITUDE between '"+minlat+"'and '"+maxlat+"') and (LONGITUDE between '"+minlng+"' and'"+maxlng+"')";
			    
			    rsUserMoney = stmt.executeQuery(sqlObjectMoney);
			    System.out.println("begin to fetch money...");
			    while(rsUserMoney.next()){
			    	
			    	idObject=rsUserMoney.getInt("ID");
			    	//System.out.println("current money"+rsUserMoney.getInt("VALUE"));
    				valueObject = rsUserMoney.getString("VALUE");
    				
		    		System.out.println("----------------money: money id"+idObject+"");
		    		
    				int valueIntObejct = Integer.parseInt(valueObject);
    				//System.out.println("check object exist result "+checkMoneyExiste(idObject));
    				if(checkMoneyExiste(idObject)) {
    					
    					//System.out.println("after check money...");
    					
    					String sqlUpdateUser = "update user set SCORE = SCORE+'"+valueIntObejct+"' where ID='"+userId+"'";
    					stmt1.executeUpdate(sqlUpdateUser);

    					String sqlUpdateObject = "delete from object where ID='"+idObject+"'";
    					stmt1.executeUpdate(sqlUpdateObject);
    					System.out.println("-----------------have fetched money "+idObject+"------------------------------------");
    					
    				}
    				
			    }
			    rsUserMoney.close();
		
			    String idObjectGilet=null;
			    
			    String sqlObjectGilet = "select * from object where OBJECTTYPE = 'gilet' and object.OBJGAME_ID = '"+gameId+"' and ( LATITUDE between '"+minlat+"'and '"+maxlat+"') and (LONGITUDE between '"+minlng+"' and'"+maxlng+"')";
				rsUserGilet = stmt.executeQuery(sqlObjectGilet);
				System.out.println("begin to fetch gilet...");
				while(rsUserGilet.next()){
					
					idObjectGilet=rsUserGilet.getString("ID");
					System.out.println("-----------------gilet : gilet id="+idObjectGilet);
					
    				String sqlUpdateUser = "update user set GILET = 1 where ID='"+userId+"'";
					stmt1.executeUpdate(sqlUpdateUser);
					
					String sqlUpdateObject = "delete from object where ID='"+idObjectGilet+"'";
					stmt1.executeUpdate(sqlUpdateObject);
					System.out.println("-----------------have fetched gilet "+idObjectGilet+"------------------------------------");
					
			    }
				rsUserGilet.close();
				
				}
			    String sqlscore = "select SCORE from user where ID='"+userId+"'";
    			rsUserScore=stmt.executeQuery(sqlscore);
    			//System.out.println("bla bla bla blabla blabla blabla bla");
    			
    			while(rsUserScore.next()){
    				System.out.println("user "+userId+" score: "+rsUserScore.getInt("SCORE"));
    				int score=rsUserScore.getInt("SCORE");
    			if(rsUserScore.getInt("SCORE")>= 5000000){
    				
    				
    				String sqlwinner = "select LOGIN from user where ID ='"+userId+"'";
    				rsWinner=stmt1.executeQuery(sqlwinner);
    				String winner=null;
 				    while(rsWinner.next()){
 				    	winner = rsWinner.getString("LOGIN");
 				    }
 				   System.out.println("game over, "+winner+"(user "+userId+")  win!!!!!  Bravo");
    				Date date = new Date();       
 				    Timestamp timeStamp = new Timestamp(date.getTime());
 				    Timestamp timeStampStart = null;
 				    String sqlGameStart = "select TIMESTAMP from game where ID ='"+gameId+"'";
 				    rsGameStart=stmt1.executeQuery(sqlGameStart);
 				    while(rsGameStart.next()){
 				    	timeStampStart = rsGameStart.getTimestamp("TIMESTAMP");
 				    }
 				    double timetaken = CheckTimeDiff.calculTimeDiff(timeStamp, timeStampStart);
    				String sqlUpdateGame = "update game set SCORE = '"+score+"', WINNER = '"+winner+"', TIMETAKEN = '"+timetaken+"' where ID='"+gameId+"'";
    				stmt1.executeUpdate(sqlUpdateGame);
    				}
    			}
    			rsUserScore.close();
			   
				stmt.close();
				con.close();
			} catch (SQLException e) {
			    
			    e.printStackTrace();
			   }

			} catch (ClassNotFoundException e) {
			   
			   e.printStackTrace();
			  }
    	
    	
    }
    private boolean checkMoneyExiste(int idObejct){
    	ResultSet rsMoneyExiste;
    	double timeDiff = 0;
    	double durationDouble = 0;
    
	    	try {
				   Class.forName("com.mysql.jdbc.Driver");
				   try {
				    con = DriverManager.getConnection("jdbc:mysql://localhost/jeu_mobile", "root", "");
				    if(!con.isClosed())  
				        System.out.println("check money...");
				    Statement stmt = con.createStatement();
				    String sqlMoneyExiste = "select STARTTIME, DURATION from object where ID='"+idObejct+"' ";
				    
				    rsMoneyExiste = stmt.executeQuery(sqlMoneyExiste);
				    Date date = new Date();       
					Timestamp timeStampNow = new Timestamp(date.getTime());
					
					Timestamp	startTime = null;
				    while(rsMoneyExiste.next()){	
					startTime=rsMoneyExiste.getTimestamp("STARTTIME");
	 				/*String duration = rsMoneyExiste.getString("DURATION");
	 				durationDouble = Double.parseDouble(duration);*/
	 				durationDouble = rsMoneyExiste.getDouble("DURATION");
				    }
	 				timeDiff=CheckTimeDiff.calculTimeDiff(timeStampNow, startTime);
	 				rsMoneyExiste.close();
	 				stmt.close();
					con.close();
					
	 				
				   }catch (SQLException e) {
					    
					    e.printStackTrace();
					   }
				 

					} catch (ClassNotFoundException e) {
					   
					   e.printStackTrace();
					  }
	    	//System.out.println("timeDiff = "+timeDiff + "  duration = "+ durationDouble);
	    	if(timeDiff<durationDouble) return true;
	    	
	    	else return false;
	    	
    	   }
    
}
