package com.test.test;

import com.ipro.web.util.GenID;

public class TestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int i=0;i<100;i++)
		  System.out.println(GenID.getInstance().getId());

	}

}
