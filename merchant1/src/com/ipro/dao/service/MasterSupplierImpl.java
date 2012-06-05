package com.ipro.dao.service;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import com.ipro.model.bean.SupplierBean;

public class MasterSupplierImpl extends Common implements MasterSupplier {
	private static org.apache.log4j.Logger Log = Logger.getLogger(MasterSupplierImpl.class);
	static String INSTANT_DB_NAME ="db_merchant";
	static{
		String dataSource = "jdbc/Datasource_merchant";
		Common.setConfigForConnectionPool("", dataSource);		
		/*Common.setConfig(host, port, dns, usr, pwd);*/
	}

	public boolean insertSupplier(SupplierBean obj) throws Exception {
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
				clbstmt = conn.prepareCall("{call "+INSTANT_DB_NAME+".INSERT_SUPPLIER(?,?,?,?,?,?,?)}");
				clbstmt.setString(i++,obj.getCompanyName());				    
				clbstmt.setString(i++,obj.getContactName());	
				clbstmt.setString(i++,obj.getEmail());	
				clbstmt.setString(i++,obj.getTel());	
				clbstmt.setString(i++,obj.getMobile());	
				clbstmt.setString(i++,obj.getAddress());	
				clbstmt.setString(i++,obj.getDesc());	

				Log.debug("-->Mapping parameter successfully.");	
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

	public boolean updateSupplier(SupplierBean obj) throws Exception {
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
				clbstmt = conn.prepareCall("{call "+INSTANT_DB_NAME+".UPDATE_SUPPLIER(?,?,?,?,?,?,?,?)}");
				clbstmt.setInt(i++,obj.getId());
				clbstmt.setString(i++,obj.getCompanyName());	
				clbstmt.setString(i++,obj.getContactName());	
				clbstmt.setString(i++,obj.getEmail());	
				clbstmt.setString(i++,obj.getTel());	
				clbstmt.setString(i++,obj.getMobile());	
				clbstmt.setString(i++,obj.getAddress());	
				clbstmt.setString(i++,obj.getDesc());	

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

	public boolean deleteSupplier(String supId) throws Exception {
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
				
				clbstmt = conn.prepareCall("{call "+INSTANT_DB_NAME+".DELETE_SUPPLIER(?)}");
				clbstmt.setInt(i++,Integer.parseInt(supId));				    
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
	}

	public SupplierBean getSupplier(String supId) throws Exception {
		CallableStatement clbstmt = null;
		Connection conn = null;
		int i = 1;
		//***********
		SupplierBean obj =null;
		try{
				//connection db
				conn = open();
				Log.debug("-->Before Retrieve data.");
				clbstmt = conn.prepareCall("{call "+INSTANT_DB_NAME+".GET_SUPPLIER(?,?,?,?,?,?,?,?)}");
				clbstmt.setInt(i++,Integer.parseInt(supId));
				clbstmt.registerOutParameter(i++, java.sql.Types.VARCHAR);
				clbstmt.registerOutParameter(i++, java.sql.Types.VARCHAR);
				clbstmt.registerOutParameter(i++, java.sql.Types.VARCHAR);
				clbstmt.registerOutParameter(i++, java.sql.Types.VARCHAR);
				clbstmt.registerOutParameter(i++, java.sql.Types.VARCHAR);
				clbstmt.registerOutParameter(i++, java.sql.Types.VARCHAR);
				clbstmt.registerOutParameter(i++, java.sql.Types.VARCHAR);
			   
				System.out.println("Mapping parameter successfully.");
				clbstmt.execute();
				//result = clbstmt.executeUpdate();	
				Log.debug("--->Execute GET_SUPPLIER() :"+supId);     			        
				//get out parameter
				obj = new SupplierBean();
				
				obj.setId(Integer.parseInt(supId)); 
				obj.setCompanyName(clbstmt.getString("p_companyName"));
				obj.setContactName(clbstmt.getString("p_contactName"));
				obj.setEmail(clbstmt.getString("p_email"));
				obj.setTel(clbstmt.getString("p_tel"));
				obj.setMobile(clbstmt.getString("p_mobile"));
				obj.setAddress(clbstmt.getString("p_address"));
				obj.setDesc(clbstmt.getString("p_desc"));
								
				//obj.setbName(clbstmt.getString("p_bName")); //name param from  stroeprocedure
				Log.debug("--->Retrieve completed.");  
		    }catch(Exception e){
				e.fillInStackTrace();
				System.out.println("xxx--Exception :"+e.toString());
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

	public ArrayList listSupplier() throws Exception {
		// TODO Auto-generated method stub
		// Log.debug("List WatchdogForm..");
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String sql = "";
		SupplierBean obj = null;
		ArrayList result = new ArrayList();
		try {
			conn = open();
			//Common.beginTransaction(conn);
			Log.debug("-->Query.");
			//setTransaction
			sql = " SELECT supp_id,  supp_company_name, supp_contact_name, supp_email,  supp_tel, supp_mobile, supp_address,  supp_desc " +
					" FROM  db_merchant.SUPPLIER  " +
					" ORDER BY supp_id ASC ";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			Log.debug("--->execute SQL Query.");
			int c = 0;
			while (rs.next()) {
				obj = new SupplierBean();
				obj.setId(rs.getInt("supp_id"));
				obj.setCompanyName(rs.getString("supp_company_name"));
				obj.setContactName(rs.getString("supp_contact_name"));
				obj.setEmail(rs.getString("supp_email"));
				obj.setTel(rs.getString("supp_tel"));
				obj.setMobile(rs.getString("supp_mobile"));
				obj.setAddress(rs.getString("supp_address"));
				obj.setDesc(rs.getString("supp_desc"));				
				result.add(obj);
				c++;
			}
			//Log.debug("get UserInfo FetchSize :"+c);
			//Commit Transaction
			Log.debug("--->Query completed.");
		} catch (Exception e) {
			e.printStackTrace();
			Log.debug("Exception : "+e.getMessage());
		} finally {
			// close
			try {
				rs.close();
				pstmt.close();
				close(conn);
			}catch (Exception e) {
			}
		}
		return result;
	}
}
	 
