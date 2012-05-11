package com.ipro.dao.service;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import com.ipro.model.bean.CustomerBean;

public class MasterCustomerImpl extends Common implements MasterCustomer{
	
	private static org.apache.log4j.Logger Log = Logger.getLogger(MasterCustomerImpl.class);
	static String INSTANT_DB_NAME ="db_merchant";
	static{
		String dataSource = "jdbc/Datasource_merchant";
		Common.setConfigForConnectionPool("", dataSource);		
		/*Common.setConfig(host, port, dns, usr, pwd);*/
	}

	//Action Insert 
	public boolean insertCustomers(CustomerBean obj) throws Exception {
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
				clbstmt = conn.prepareCall("{call "+INSTANT_DB_NAME+".INSERT_CUSTOMER(?,?,?,?,?,?,?,?,?)}");
				clbstmt.setString(i++,obj.getCusId());				    
				clbstmt.setString(i++,obj.getCusFname());	
				clbstmt.setString(i++,obj.getCusLname());	
				clbstmt.setString(i++,obj.getCusTel());	
				clbstmt.setString(i++,obj.getCusMobile());	
				clbstmt.setString(i++,obj.getCusEmail());	
				clbstmt.setString(i++,obj.getCusAddress());	
				clbstmt.setString(i++,"0");	
				clbstmt.setString(i++,obj.getCusComment());	

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

	public boolean updateCustomers(CustomerBean customer) throws Exception {
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
				clbstmt = conn.prepareCall("{call "+INSTANT_DB_NAME+".UPDATE_CUSTOMER(?,?,?,?,?,?,?,?)}");
				clbstmt.setString(i++,customer.getCusId());
				clbstmt.setString(i++,customer.getCusFname());	
				clbstmt.setString(i++,customer.getCusLname());	
				clbstmt.setString(i++,customer.getCusTel());	
				clbstmt.setString(i++,customer.getCusMobile());	
				clbstmt.setString(i++,customer.getCusEmail());	
				clbstmt.setString(i++,customer.getCusAddress());	
				clbstmt.setString(i++,customer.getCusComment());	
				
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

	public boolean deleteCustomers(String cusId) throws Exception {
		CallableStatement clbstmt = null;
		Connection conn = null;
		int i = 1;
		//int intRet = 0;
		//***********
		boolean isDel = true;  //true is success,false is error
		try{
				//connection db
				conn = open();
				Common.beginTransaction(conn);
				Log.debug("-->Delete record.");	
				
				clbstmt = conn.prepareCall("{call "+INSTANT_DB_NAME+".DELETE_CUSTOMER(?)}");
				clbstmt.setString(i++,cusId);				    
				isDel = clbstmt.execute();				
					
				System.out.println("--->Execute delete() :"+isDel);     			        
				Common.commitTransaction(conn);				
				Log.debug("--->Execute delete."); 
				//intRet 0=Fail
				//intRet 1=success
				return false;
			}catch(Exception e){
				//intRet = 0;
				e.fillInStackTrace();
				try{
					conn.rollback();
				}catch(Exception ex){}
				return true;
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
			//if(intRet==0){
			//	return true;//Error #End
			//}else{
			//	return false;//Success #End
			//}  
	}

	public CustomerBean getCustomers(String cusId) throws Exception {
		CallableStatement clbstmt = null;
		Connection conn = null;
		int i = 1;
		//***********
		CustomerBean obj;
		try{
				//connection db
				conn = open();
				Log.debug("-->Before Retrieve data.");
				clbstmt = conn.prepareCall("{call "+INSTANT_DB_NAME+".GET_CUSTOMER(?,?,?,?,?,?,?,?,?)}");
				clbstmt.setString(i++,cusId);
				clbstmt.registerOutParameter(i++, java.sql.Types.VARCHAR);
				clbstmt.registerOutParameter(i++, java.sql.Types.VARCHAR);
				clbstmt.registerOutParameter(i++, java.sql.Types.VARCHAR);
				clbstmt.registerOutParameter(i++, java.sql.Types.VARCHAR);
				clbstmt.registerOutParameter(i++, java.sql.Types.VARCHAR);
				clbstmt.registerOutParameter(i++, java.sql.Types.VARCHAR);
				clbstmt.registerOutParameter(i++, java.sql.Types.VARCHAR);
				clbstmt.registerOutParameter(i++, java.sql.Types.VARCHAR);
			    
				clbstmt.execute();
				//result = clbstmt.executeUpdate();	
				Log.debug("--->Execute GET_CUSTOMER()");     			        
				//get out parameter
				obj = new CustomerBean();
				 
				obj.setCusId(cusId);
				obj.setCusFname(clbstmt.getString("p_Fname"));
				obj.setCusLname(clbstmt.getString("p_Lname"));
				obj.setCusTel(clbstmt.getString("p_Tel"));
				obj.setCusMobile(clbstmt.getString("p_Mobile"));
				obj.setCusEmail(clbstmt.getString("p_Email"));
				obj.setCusAddress(clbstmt.getString("p_Address"));
				obj.setCusFlag(clbstmt.getString("p_Flag"));
				obj.setCusComment(clbstmt.getString("p_Comment"));				
				
				//obj.setbName(clbstmt.getString("p_bName")); //name param from  stroeprocedure
				Log.debug("--->Retrieve completed.");  
		    }catch(Exception e){
				e.fillInStackTrace();
				obj = null;
			}finally{
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

	public ArrayList listCustomers() throws Exception {
		// TODO Auto-generated method stub		
		Connection conn		=	null;
		ResultSet rs 		= 	null;
		PreparedStatement pstmt = null;
		String sql				="";
		CustomerBean  obj =null;
		ArrayList result = new ArrayList();				
		try{	 
				conn = open();
				//Common.beginTransaction(conn);
				Log.debug("-->Query.");	
				// Log.debug("Open connection");	    	 
				//setTransaction 			
				 sql=" SELECT cus_id, cus_prefix,cus_fname,cus_lname,cus_tel,cus_mobile" +
				 " ,cus_email,cus_address,cus_flag,cus_start_date ,cus_comment " +
				 " FROM db_merchant.customers "+
				 " ORDER BY cus_id ASC " ;	
				 Log.debug("--->SQL:"+sql);	
				 pstmt = conn.prepareStatement(sql);
				 rs = pstmt.executeQuery();
				 
				 Log.debug("--->execute SQL Query.");					    	  
				 int c=0;
				 while(rs.next()){	 			  
					obj = new  CustomerBean();
					obj.setCusId(rs.getString("cus_id"));
					obj.setCusPrefix(rs.getString("cus_prefix"));
					obj.setCusFname(rs.getString("cus_fname"));
					obj.setCusLname(rs.getString("cus_lname"));
					obj.setCusTel(rs.getString("cus_tel"));
					obj.setCusMobile(rs.getString("cus_mobile"));
					obj.setCusEmail(rs.getString("cus_email"));
					obj.setCusAddress(rs.getString("cus_address"));
					obj.setCusFlag(rs.getString("cus_flag"));
					obj.setCusStartDate(rs.getString("cus_start_date"));
					obj.setCusComment(rs.getString("cus_comment"));

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
