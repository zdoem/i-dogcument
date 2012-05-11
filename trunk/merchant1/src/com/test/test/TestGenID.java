package com.test.test;
import com.ipro.dao.service.Common;
import com.ipro.dao.service.GenerateAutoID;
import com.ipro.web.util.Utilize;
public class TestGenID {

	public static void main(String args[]){
		test2();
	}
		
	
	private static void test2(){		
		String id = GenerateAutoID.getAutoIdFromTable("cus_id", "customers");
		System.out.println("---->> :"+id);
	}
	
	
	private static void test1(){		
		//25550430001
		String tempMaxID = "25550430001";
		
		String dateID = "25550430001";
		//String counter = "001";
		//select max(cus_id) as maxid from customers
		//rs.next()
		//id = get maxid;		
		System.out.println("----> "+dateID.length());
		System.out.println("----> "+dateID.substring(0,8));
		System.out.println("----> "+dateID.substring(8,dateID.length()));
		//System.out.println("----> "+counter);
		System.out.println("- -->>"+Utilize.getThaiCurrentYYYYMMDD().replaceAll("-", ""));
		
		String currentID = Utilize.getThaiCurrentYYYYMMDD().replaceAll("-", "");
		String dateId = dateID.substring(0,8);
		if(dateId.equals(currentID)){
			int x = Integer.parseInt(dateID.substring(8,dateID.length()));
			System.out.println("NextID :"+x);
			System.out.println("---->Next ID X:"+currentID+Utilize.GenNextId(++x));			
		}else{
			System.out.println("---->New ID Y:"+currentID+"001");
		}
	}
}
