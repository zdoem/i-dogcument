package com.ipro.dao.service;
import java.sql.CallableStatement;
import java.sql.Connection;
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
	
	@Override
	public boolean insertBrandProduct(BrandProduct bProduct) throws Exception {	
			CallableStatement clbstmt = null;
			Connection conn = null;
			int i = 1;
			//***********
			boolean isInsert = true; //true is fail
			try{
					// TODO 	
					conn = open();
					conn.setAutoCommit(false);
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
	
	

	@Override
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

	
	@Override
	public boolean deleteBrandProduct(int bId) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public BrandProduct getBrandProduct(int bId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList listBrandProduct(BrandProduct bProduct) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
