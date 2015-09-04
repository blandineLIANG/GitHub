package server;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

public class CheckPosition {
	private double longitude;
	private double latitude;
	
	public void Checkposition(double longitude, double latitude){
		this.longitude=longitude;
		this.latitude=latitude;
	}
	
	public static JSONObject findNeighPosition(double longitude,double latitude) throws JSONException{  
        
        double r = 6371;  
        double dis = 0.05;  
        double dlng =  2*Math.asin(Math.sin(dis/(2*r))/Math.cos(latitude*Math.PI/180));  
        dlng = dlng*180/Math.PI; 
        double dlat = dis/r;  
        dlat = dlat*180/Math.PI;          
        double minlat =latitude-dlat;  
        double maxlat = latitude+dlat;  
        double minlng = longitude -dlng;  
        double maxlng = longitude + dlng; 
        
        if (minlat > maxlat) 
        {   
        	double flag=minlat;
            minlat=maxlat;
            maxlat= flag;
        }
        
        if (minlng > maxlng)
        {
        	double flag1=minlng;
        	minlng=maxlng;
        	maxlng=flag1;
        }
        
        JSONObject jso = new JSONObject();
		jso.put("minlat",minlat);
		jso.put("maxlat",maxlat);
		jso.put("minlng",minlng);
		jso.put("maxlng",maxlng);
		return jso;  
        
        
        
    }  
	/*public static void main(String[] args) throws JSONException{
		JSONObject jso1 = CheckPosition.findNeighPosition(5.5, 6.6);
		Double minlat=jso1.getDouble("minlat");
		Double maxlat=jso1.getDouble("maxlat");
		Double minlng=jso1.getDouble("minlng");
		Double maxlng=jso1.getDouble("maxlng");
		System.out.println(minlat +" "+ maxlat +" "+ minlng +" "+ maxlng );
	
		
	}*/
}
