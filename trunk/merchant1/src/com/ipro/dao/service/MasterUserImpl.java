package com.ipro.dao.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.log4j.Logger;

import com.ipro.model.bean.CustomerBean;
import com.ipro.model.bean.UsersBean;

public class MasterUserImpl extends Common implements MasterUser {

	private static org.apache.log4j.Logger Log = Logger
			.getLogger(MasterUserImpl.class);
	static String INSTANT_DB_NAME = "db_merchant";
	static {
		String dataSource = "jdbc/Datasource_merchant";
		Common.setConfigForConnectionPool("", dataSource);
		/* Common.setConfig(host, port, dns, usr, pwd); */
	}

	public boolean insertUsers(UsersBean obj) throws Exception {
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
				clbstmt = conn.prepareCall("{call "+INSTANT_DB_NAME+".INSERT_USER2(?,?,?,?,?,?,?,?,?,?,?)}");
				clbstmt.setString(i++,obj.getId());				    
				clbstmt.setString(i++,obj.getfName());	
				clbstmt.setString(i++,obj.getlName());	
				clbstmt.setString(i++,obj.getTel());	
				clbstmt.setString(i++,obj.getMobile());	
				clbstmt.setString(i++,obj.getEmail());	
				clbstmt.setString(i++,obj.getAddress());	
				clbstmt.setString(i++,obj.getLogin());	
				clbstmt.setString(i++,obj.getPassword());	
				clbstmt.setString(i++, "P");
				clbstmt.setString(i++, obj.getCitizenId());
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

	public boolean updateUsers(UsersBean user) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deleteUsers(String usrId) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	public UsersBean getUsers(String usrId) throws Exception {
		CallableStatement clbstmt = null;
		Connection conn = null;
		int i = 1;
		//***********
		UsersBean obj =null;
		try{
				//connection db
				conn = open();
				Log.debug("-->Before Retrieve data.");
				clbstmt = conn.prepareCall("{call "+INSTANT_DB_NAME+".GET_USER2(?,?,?,?,?,?,?,?,?,?,?,?)}");
				clbstmt.setString(i++,usrId);
				clbstmt.registerOutParameter(i++, java.sql.Types.VARCHAR);
				clbstmt.registerOutParameter(i++, java.sql.Types.VARCHAR);
				clbstmt.registerOutParameter(i++, java.sql.Types.VARCHAR);
				clbstmt.registerOutParameter(i++, java.sql.Types.VARCHAR);
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
				Log.debug("--->Execute GET_USER2() :"+usrId);     			        
				//get out parameter
				obj = new UsersBean();

				obj.setId(usrId); 
				obj.setfName(clbstmt.getString("p_Fname"));
				obj.setlName(clbstmt.getString("p_Lname"));
				obj.setTel(clbstmt.getString("p_Tel"));
				obj.setMobile(clbstmt.getString("p_Mobile"));
				obj.setEmail(clbstmt.getString("p_Email"));
				obj.setAddress(clbstmt.getString("p_Address"));
				obj.setLogin(clbstmt.getString("p_Login"));
				obj.setPassword(clbstmt.getString("p_password"));
				obj.setActivate(clbstmt.getString("p_Activate"));
				obj.setCitizenId(clbstmt.getString("p_Citizen_id"));
				obj.setDate(clbstmt.getString("p_date"));
				
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


	public ArrayList listUsers() throws Exception {
		// TODO Auto-generated method stub
		// Log.debug("List WatchdogForm..");
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String sql = "";
		UsersBean obj= null;
		ArrayList result = new ArrayList();
		try {
			conn = open();
			// Common.beginTransaction(conn);
			Log.debug("-->Query.");
			// setTransaction

			sql = " SELECT usr_id,usr_prefix,usr_fname,usr_lname,usr_tel,usr_mobile,usr_email,usr_address,usr_login,usr_password" +
					",usr_activate,usr_citizen_id,usr_date  FROM db_merchant.user2  ORDER BY usr_id ASC ";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			Log.debug("--->execute SQL Query.");

			int c = 0;
			while (rs.next()) {
				obj = new UsersBean();
				obj.setId(rs.getString("usr_id"));
				obj.setfName(rs.getString("usr_fname"));
				obj.setlName(rs.getString("usr_lname"));
				obj.setTel(rs.getString("usr_tel"));
				obj.setMobile(rs.getString("usr_mobile"));
				obj.setEmail(rs.getString("usr_email"));
				obj.setAddress(rs.getString("usr_address"));
				obj.setLogin(rs.getString("usr_login"));
				obj.setPassword(rs.getString("usr_password"));
				obj.setActivate(rs.getString("usr_activate"));
				obj.setCitizenId(rs.getString("usr_citizen_id"));
				obj.setDate(rs.getString("usr_date"));				
				result.add(obj);
				c++;
			}
			// Log.debug("get UserInfo FetchSize :"+c);
			// Commit Transaction
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
			} catch (Exception e) {
			}
			// Log.debug("close connection ");
		}
		return result;
	}
}
