package com.ipro.web.util;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Utilize {
	
	public static String getThaiCurrentYYYYMMDD(){
		//Date format
	  	Date date = Calendar.getInstance().getTime();
	  	 // Display a date in day, month, year format
	  	DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	  	String today = formatter.format(date);
	  	String [] tempDate ;
	  	tempDate = today.split("/");
	  	int yyy = Integer.parseInt(tempDate[2])+543;
	  	
	  return	yyy+"-"+tempDate[1]+"-"+tempDate[0];
  }
	
	//------->>GenID
    public static String GenNextId(int b){
		        String temp=""+b;
		        String newSp_id;
		        switch(temp.length()){ 
		          // case 1: newSp_id="00000"+temp; break; // case 2: newSp_id="0000"+temp; break; //case 1: newSp_id="000"+temp; break;
		           case 1: newSp_id="00"+temp; break;
		           case 2: newSp_id="0"+temp; break;
		           default:newSp_id=temp;
		        }
		      return newSp_id;
		 }

}
