package com.test.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.ipro.model.bean.ProductBean;
import com.ipro.web.util.GenID;
import com.ipro.web.util.Utilize;
public class TestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test4();
	}
	
	private static void test4(){
		List list = new ArrayList<String>();	
		PersonBean obj = new PersonBean(); 
		obj.setId("1001");
		obj.setFname("AAAA");
		obj.setPoint(2);		
		list.add(obj);
		
		PersonBean obj1 = new PersonBean(); 
		obj1.setId("1002");
		obj1.setFname("BBBB");
		obj1.setPoint(3);
		list.add(obj1);
		
		PersonBean obj2 = new PersonBean(); 
		obj2.setId("1003");
		obj2.setFname("CCCC");
		obj2.setPoint(1);
		list.add(obj2);
		
		System.out.println("List :"+list.size());
		printList(list);
		
		boolean isDup = true;
		if(list !=null && list.size()>0){
			PersonBean  prod = null;
     		
			int i = 0;
			int itemp = 0;
			System.out.println("--> Check duplicate process.");
			Iterator it=list.iterator();
        	while(it.hasNext()){
        		
        		prod =(PersonBean)it.next();
        		if(prod.getId().equals("1009")){
        			itemp = prod.getPoint()+5;        			
        			list.remove(i);
        			      			
        			PersonBean obx = new PersonBean(); 
        			obx.setId("1002");
        			obx.setFname("BBBBx");
        			obx.setPoint(itemp);
        			list.add(obx);
	      			//System.out.println("Remove productId :"+productId);
	      			//System.out.println("Remove seq:"+recNo);
        			//list.
        			isDup = false;
	    		    break;      			
        		}
        		i++;
        	}
     	}	
		
		System.out.println("List :"+list.size());		
		printList(list);		
		if(isDup){			
			PersonBean obj4 = new PersonBean(); 
			obj4.setId("1004");
			obj4.setFname("DDDDDD");
			obj4.setPoint(10);
			list.add(obj4);
		}
		printList(list);
	}
	
	private static void printList(List list){
		PersonBean p = null;
		for(int i=0;i<list.size();i++){
		   p = (PersonBean)list.get(i);
		   System.out.println("ID: "+p.getId()+",Fname :"+p.getFname()+",POINT :"+p.getPoint());
		}
	
	}
	
	
	private static void test2(){
		List list = new ArrayList<String>();	
		list.add("Test11");
		list.add(null);
		list.add("Test33");
		list.add("TEST44");
		list.add("");
		list.add("");
		list.add("");
		int i = 1;
		
		System.out.println("size:"+list.size());
		Iterator it = list.iterator();
		 while(it.hasNext()){
			 String doc =(String)it.next();
			 // i=1;
			// if(doc!=null && !doc.equals("")){
			//if(doc!=null && !"".equals(doc)){
				 System.out.println(i+++" : "+doc);  
			 //}
		  }
		
		
	}
	
	private static void test3(){
		System.out.println("------->>"+Utilize.getThaiCurrentYYYYMMDD().replaceAll("-", ""));
	}
	
	
	private static void test1(){
		for(int i=0;i<100;i++)
			  System.out.println(GenID.getInstance().getId());
	}

}
