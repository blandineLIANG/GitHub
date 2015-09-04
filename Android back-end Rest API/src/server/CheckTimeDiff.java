package server;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CheckTimeDiff {
	public CheckTimeDiff()
	{		
	}
	
	public static double calculTimeDiff(Timestamp ts,Timestamp ts1){
		Date d1 = new Date(ts.getTime());
		Date d2 = new Date(ts1.getTime());
		
		String sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(ts); 
	    
	    long dd1 = d1.getTime(); 
	    long dd2 = d2.getTime(); 
	    double time = (double)(dd1-dd2)/60/1000; 
	    //System.out.println(" ±º‰≤Ó «£∫"+time+"£®minutes£©");
		return time; 
		

	}
	
	/*public static void main(String[] args) throws ParseException{ 
	   
		String t1 = "2008-03-10 16:25:02"; 
	    String t2 = ""; 


	     Date dd1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(t1); 
	     Date dd2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(t2);
	     
	           
	     Timestamp ts = new Timestamp(dd1.getTime());
	     Timestamp ts1 = new Timestamp(dd2.getTime());
	     Double a = CheckTimeDiff.calculTimeDiff(ts, ts1);
	     System.out.println(a);
	    
	    } */
	}

