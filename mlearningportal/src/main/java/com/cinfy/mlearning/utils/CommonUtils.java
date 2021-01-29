package com.cinfy.mlearning.utils;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

public class CommonUtils {

	public static final String FROM_DATE_FORMAT = "yyyy-mm-dd hh:mm:ss";
	
	public static final String TO_DATE_FORMAT = "yyyyy-mm-dd hh:mm:ss";
	
	
	public static final Date getDateFromString (String dateString) {
		SimpleDateFormat dt = new SimpleDateFormat(FROM_DATE_FORMAT); 
		Date date = new Date();
		try {
			date = dt.parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return date;
	}
	
	
	public static final Date convertDateString (String dateString,String format) {
		SimpleDateFormat dt = new SimpleDateFormat(format); 
		Date date = new Date();
		try {
			date = dt.parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return date;
	}
	
	public static final String getStringFromDate (Date date) {
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'"); 
		String result = null;
			result = dt.format(date);
		return result;
	}
	//create for date
	public static final String getStringFromDateDDMMYYYY(Date date) {
//		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd"); 
		SimpleDateFormat dt = new SimpleDateFormat("dd-MMM-yyyy");
		String result = null;
			result = dt.format(date);
		return result;
	}
	
	
	 public static HttpHeaders getHeaders() {
	    	HttpHeaders headers = new HttpHeaders();
	    	headers.setAccessControlAllowOrigin("*");
	    	headers.setAccessControlMaxAge(1000*60*30);
	    	headers.setAccessControlAllowMethods((Arrays.asList(HttpMethod.GET,HttpMethod.POST,
	    			HttpMethod.PUT,HttpMethod.OPTIONS,HttpMethod.DELETE,HttpMethod.TRACE)));
	    	headers.setAccessControlAllowHeaders(Arrays.asList("Accept","Content-Type","X-PINGOTHER"));
	    	headers.set("Cache-Control", "no-cache");
	    	return headers;
	    }
	 
	 /*
	     * Common headers
	     */
	    public static HttpHeaders getDocumentHeaders() {
	    	HttpHeaders headers = new HttpHeaders();
	    	headers.setAccessControlAllowOrigin("*");
	    	headers.setAccessControlMaxAge(1000*60*30);
	    	headers.setAccessControlAllowMethods((Arrays.asList(HttpMethod.GET,HttpMethod.POST,
	    			HttpMethod.PUT,HttpMethod.OPTIONS,HttpMethod.DELETE,HttpMethod.TRACE)));
	    	headers.setAccessControlAllowHeaders(Arrays.asList("Accept","Content-Type","X-PINGOTHER"));
	    	headers.set("Cache-Control", "no-cache");
	    	headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
	    	return headers;
	    }
	    
	    public static String distance(double lat1, double lat2, double lon1,
	            double lon2, double el1, double el2) {
	    	double distance = 0.00;
	        final int R = 6371; // Radius of the earth
	        DecimalFormat df = new DecimalFormat("#.00");
	        try{
		        double latDistance = Math.toRadians(lat2 - lat1);
		        double lonDistance = Math.toRadians(lon2 - lon1);
		        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
		                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
		                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
		        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		        distance = R * c * 1000; // convert to meters
	
		        double height = 0.0;
	
		        distance = Math.pow(distance, 2) + Math.pow(height, 2);
		        distance = Math.sqrt(distance)/1000;		        
		        
	        } catch(Exception e){
	        	e.printStackTrace();
	        }
	        
	        return String.format("%.2f",distance);
	    }
	    
	    public static String generateAlphanumericRandom() {
	        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	        StringBuilder salt = new StringBuilder();
	        Random random = new Random();	
	        while (salt.length() < 18) { // length of the random string.
	            int index = (int) (random.nextFloat() * SALTCHARS.length());
	            salt.append(SALTCHARS.charAt(index));
	        }
	        String saltStr = salt.toString();
	        return saltStr;
	    }
	    
	    public static boolean isValidFormat(String format, String value) {
	        Date date = null;
	        try {
	            SimpleDateFormat sdf = new SimpleDateFormat(format);
	            date = sdf.parse(value);
	            if (!value.equals(sdf.format(date))) {
	                date = null;
	            }
	        } catch (ParseException ex) {
	            ex.printStackTrace();
	        }
	        return date != null;
	    }
	    
	    
}
