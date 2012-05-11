package com.ipro.dao.service;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import com.ipro.model.bean.BrandProduct;

public class MasterBrandProductImpl  extends Common implements MasterBrandProduct {
	
	private static org.apache.log4j.Logger Log = Logger.getLogger(MasterBrandProductImpl.class);
	static String INSTANT_DB_NAME ="db_merchant";
	static{
		String dataSource = "jdbc/Datasource_merchant";
		Common.setConfigForConnectionPool("", dataSource);		
		/*Common.setConfig(host, port, dns, usr, pwd);*/
	}
	
	//@Override
	public boolean insertBrandProduct(BrandProduct bProduct) throws Exception {	
			CallableStatement clbstmt = null;
			Connection conn = null;
			int i = 1;
			//***********
			boolean isInsert = true; //true is fail
			try{
					// TODO 	
					conn = open();
					Common.beginTransaction(conn);
					
					Log.debug("-->Before Insert.");					
					//Insert to DB
					clbstmt = conn.prepareCall("{call "+INSTANT_DB_NAME+".INSERT_BRAND_PRODUCT(?)}");
					clbstmt.setString(i++,bProduct.getbName());				    
					
					isInsert = clbstmt.execute();
					//result = clbstmt.executeUpdate();	
					Log.debug("-->Execute Insert.");		    				
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
	
	

	//@Override
	public boolean updateBrandProduct(BrandProduct bProduct) throws Exception {
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
				clbstmt = conn.prepareCall("{call "+INSTANT_DB_NAME+".UPDATE_BRAND_PRODUCT(?,?)}");
				clbstmt.setInt(i++, bProduct.getbId());
				clbstmt.setString(i++,bProduct.getbName());					    
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

	
	//@Override
	public boolean deleteBrandProduct(int bId) throws Exception {
		CallableStatement clbstmt = null;
		Connection conn = null;
		int i = 1;
		int intRet = 0;
		//***********
		boolean isDel = true;  //true is success,false is error
		try{
				//connection db
				conn = open();
				Common.beginTransaction(conn);
				Log.debug("-->Delete record.");	
				
				clbstmt = conn.prepareCall("{call "+INSTANT_DB_NAME+".DELETE_BRAND_PRODUCT(?,?)}");
				clbstmt.setInt(i++,bId);
				clbstmt.registerOutParameter(i++, java.sql.Types.INTEGER);					    
				clbstmt.execute();				
				//result = clbstmt.executeUpdate();	
				Log.debug("--->Execute delete()");     			        
				//get out parameter
				intRet = clbstmt.getInt("p_IntResult"); //name param from  stroeprocedure  

				Common.commitTransaction(conn);
				
				Log.debug("--->Execute delet"); 
				//intRet 0=Fail
				//intRet 1=success
			}catch(Exception e){
				e.fillInStackTrace();
				try{
					conn.rollback();
				}catch(Exception ex){}
			}
			finally{
				try {
					if(clbstmt!=null)
						clbstmt.close();
					if(conn!=null)
						conn.close();
				 } catch (SQLException e) {
					  e.printStackTrace();
				}
			}			
			
			//*******Return*********
			if(intRet==0){
				return true;//Error #End
			}else{
				return false;//Success #End
			}  
	}

	
	//@Override
	public BrandProduct getBrandProduct(int bId) throws Exception {
		CallableStatement clbstmt = null;
		Connection conn = null;
		int i = 1;
		//***********
		BrandProduct obj;
		try{
				//connection db
				conn = open();
				Log.debug("-->Before Retrieve data.");
				clbstmt = conn.prepareCall("{call "+INSTANT_DB_NAME+".GET_BRAND_PRODUCT(?,?)}");
				clbstmt.setInt(i++,bId);
				clbstmt.registerOutParameter(i++, java.sql.Types.VARCHAR);
			    
				clbstmt.execute();
				//result = clbstmt.executeUpdate();	
				Log.debug("--->Execute getType()");     			        
				//get out parameter
				obj = new BrandProduct();
				 
				obj.setbId(bId);  
				obj.setbName(clbstmt.getString("p_bName")); //name param from  stroeprocedure
				// obj.setTypeDesc(clbstmt.getString("p_typeDesc"));    
				 
				Log.debug("--->Retrieve completed.");  
		    }catch(Exception e){
				e.fillInStackTrace();
				obj = null;
			}
			
		finally{
			try {
				if(clbstmt!=null)
					clbstmt.close();
				if(conn!=null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		//*******Return*********
		return obj;//#End	
	}

	//@Override
	public ArrayList listBrandProduct() throws Exception {
		// TODO Auto-generated method stub
		//Log.debug("List WatchdogForm..");
		Connection conn		=	null;
		ResultSet rs 		= 	null;
		PreparedStatement pstmt = null;
		String sql				="";
		BrandProduct  obj =null;
	    ArrayList result = new ArrayList();				
		try{	 
				conn = open();
				//Common.beginTransaction(conn);
				Log.debug("-->Query.");	
			    // Log.debug("Open connection");	    	 
			    //setTransaction 
				
			     sql=" SELECT brand_product_id, brand_product_name FROM db_merchant.brand_product "+
			    	  " ORDER BY brand_product_name ASC " ;			    	 
			    pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				Log.debug("--->execute SQL Query.");
			    	  
				int c=0;
				while(rs.next()){	 			  
					obj = new  BrandProduct();
					obj.setbId(rs.getInt("brand_product_id"));
					obj.setbName(rs.getString("brand_product_name"));					
			 		result.add(obj);
			 		c++;
			 	}	 		  
			 	//Log.debug("get UserInfo FetchSize :"+c);
			    //Commit Transaction
				Log.debug("--->Query completed.");
			}catch(Exception e){
				e.printStackTrace();
				// Log.debug(" "+e.getMessage());
			}finally{ 
				//close
				try{
					rs.close();
					pstmt.close();
					close(conn); 
			}catch(Exception e){}		    
				//Log.debug("close connection ");
			} 			    
		 return result;
	}

}
