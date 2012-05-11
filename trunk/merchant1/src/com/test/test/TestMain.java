package com.test.test;

import com.ipro.web.util.GenID;
import com.ipro.web.util.Utilize;

public class TestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test2();

	}
	
	
	private static void test2(){
		System.out.println("------->>"+Utilize.getThaiCurrentYYYYMMDD().replaceAll("-", ""));
	}
	
	
	private static void test1(){
		for(int i=0;i<100;i++)
			  System.out.println(GenID.getInstance().getId());
	}

}
