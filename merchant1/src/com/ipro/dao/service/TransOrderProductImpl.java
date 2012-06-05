package com.ipro.dao.service;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import com.ipro.model.bean.PaymentBean;
import com.ipro.model.bean.ProductBean;

public class TransOrderProductImpl extends Common implements TransOrderProduct{
	private static org.apache.log4j.Logger Log = Logger.getLogger(TransOrderProductImpl.class);
	static String INSTANT_DB_NAME = "db_merchant";
	static {
		String dataSource = "jdbc/Datasource_merchant";
		Common.setConfigForConnectionPool("", dataSource);
		/* Common.setConfig(host, port, dns, usr, pwd); */
	}

	public boolean insertTransOrder(List list,String invoidId) throws Exception {
		CallableStatement clbstmt = null;
		Connection conn = null;
		int i = 1;
		//***********
		boolean isInsert = true; //true is fail
		try{
				//::TODO 	
				conn = open();
				Common.beginTransaction(conn);
								
				int totalUnit = 0;
				float totalPrice = 0.0f;
				ProductBean prod = null;				
				Iterator it=list.iterator();
	        	while(it.hasNext()){
	        		prod =(ProductBean)it.next();        		
	        		totalPrice = prod.getProductItem() * prod.getPriceSale();
	        		totalUnit  += prod.getProductItem();
	        	}

				Log.debug("-->Before Insert.");					
				//Insert to DB
				clbstmt = conn.prepareCall("{call "+INSTANT_DB_NAME+".INSERT_IORDER(?,?,?,?,?)}");
				clbstmt.setString(i++,invoidId);				    
				clbstmt.setString(i++,"99999999999");//CustomerID
				clbstmt.setString(i++,"11111111111");//UserID	
				clbstmt.setFloat(i++,totalPrice);	
				clbstmt.setInt(i++,totalUnit);					
				Log.debug("-->Mapping parameter Okay.");	
				isInsert = clbstmt.execute();				
				Log.debug("-->Execute Insert ORDER successfully. :"+isInsert);		    				
								
				Log.debug("-->Start Insert INSERT_IORDER_ITEM.");
				it=list.iterator();
	        	while(it.hasNext()){
	        		i = 1;
	        		prod =(ProductBean)it.next();	        		
	        		//Insert to DB
					clbstmt = conn.prepareCall("{call "+INSTANT_DB_NAME+".INSERT_IORDER_ITEM(?,?,?,?)}");
					clbstmt.setString(i++,invoidId);				    
					clbstmt.setString(i++,prod.getProductId());	//p_productId	
					clbstmt.setFloat(i++,prod.getPriceSale());	
					clbstmt.setInt(i++,prod.getProductItem());				
					
					Log.debug("-->Mapping parameter Okay.");	
					isInsert = clbstmt.execute();				
					Log.debug("-->INSERT_IORDER_ITEM Insert ORDER successfully. :"+isInsert);		
	        	}

				Common.commitTransaction(conn);
				Log.debug("-->commit successfully.");				
		}catch(Exception e){
			e.fillInStackTrace();
			Log.debug("Insert Exception e:"+e.toString());
			//try{
			Common.rollbackTransaction(conn);
			//}catch(Exception ex){}
		}finally{
			try {
				if(clbstmt!=null)
					clbstmt.close();
				if(conn!=null)
					conn.close();
				} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//*******Return*********
		return isInsert;//#End
	}

	public boolean updateTransPayin(PaymentBean obj) throws Exception {
		// TODO Auto-generated method stub		
		CallableStatement clbstmt = null;
		Connection conn = null;
		int i = 1;
		boolean isUpd = true; //true is fail
		try{	
				conn = open();
				Common.beginTransaction(conn);
				Log.debug("-->Before Update.");	
				//Update to DB
				clbstmt = conn.prepareCall("{call "+INSTANT_DB_NAME+".UPDATE_PAYMENT(?,?,?,?,?)}");
				clbstmt.setString(i++,obj.getInvoidId());
				clbstmt.setFloat(i++,obj.getAmount());	
				clbstmt.setString(i++,obj.getFlag());	
				clbstmt.setString(i++,obj.getType());
				clbstmt.setString(i++,obj.getBankRef());
				
				System.out.println(" Mapping parameter successfully.");
				isUpd = clbstmt.execute();
						
				System.out.println(" is Result :"+isUpd);
				Log.debug("-->Update successfully.");
				//result = clbstmt.executeUpdate();		    
				Common.commitTransaction(conn);
				Log.debug("-->commit update successfully.");
			}catch(Exception e){
				e.fillInStackTrace();
				Log.debug("Update Exception e:"+e.toString());
				try{
					conn.rollback();
				}catch(Exception ex){}
			}finally{				
			try {
				if(clbstmt!=null)
					clbstmt.close();
				if(conn!=null)
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}					
			  }
		return isUpd;//#End					
	}

}
