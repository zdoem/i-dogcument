package com.ipro.dao.service;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import com.ipro.model.bean.ProductBean;
public class MasterProductImpl extends Common implements MasterProduct{
	
	private static org.apache.log4j.Logger Log = Logger.getLogger(MasterProductImpl.class);
	static String INSTANT_DB_NAME ="db_merchant";
	static{
		String dataSource = "jdbc/Datasource_merchant";
		Common.setConfigForConnectionPool("", dataSource);		
		/*Common.setConfig(host, port, dns, usr, pwd);*/
	}


	public boolean insertProduct(ProductBean obj) throws Exception {
		CallableStatement clbstmt = null;
		Connection conn = null;
		int i = 1;
		//***********
		boolean isInsert = true; //true is fail
		try{
				//::TODO 	
				conn = open();
				Common.beginTransaction(conn);
				Log.debug("-->Before Insert.");					
				//Insert to DB
				clbstmt = conn.prepareCall("{call "+INSTANT_DB_NAME+".INSERT_PRODUCT(?,?,?,?,?,?,?,?,?,?)}");
				clbstmt.setString(i++,obj.getProductId());				    
				clbstmt.setString(i++,obj.getProductName());	
				clbstmt.setString(i++,obj.getTypeProductId());	
				clbstmt.setString(i++,obj.getBrandProductId());	
				clbstmt.setFloat(i++,obj.getPriceCost());	
				clbstmt.setFloat(i++,obj.getPriceSale());	
				clbstmt.setInt(i++,obj.getProductItem());	
				clbstmt.setString(i++,obj.getProductDateStart());	
				clbstmt.setString(i++,obj.getProductDateExpire());	
				clbstmt.setString(i++, obj.getProductDesc());

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

	public boolean updateProduct(ProductBean obj) throws Exception {
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
				clbstmt = conn.prepareCall("{call "+INSTANT_DB_NAME+".UPDATE_PRODUCT(?,?,?,?,?,?,?,?,?,?)}");
				clbstmt.setString(i++,obj.getProductId());
				clbstmt.setString(i++,obj.getProductName());	
				clbstmt.setInt(i++,Integer.parseInt(obj.getTypeProductId()));	
				clbstmt.setInt(i++,Integer.parseInt(obj.getBrandProductId()));	
				clbstmt.setFloat(i++,obj.getPriceCost());	
				clbstmt.setFloat(i++,obj.getPriceSale());	
				clbstmt.setInt(i++,obj.getProductItem());	
				clbstmt.setString(i++,obj.getProductDateStart());	
				clbstmt.setString(i++,obj.getProductDateExpire());	
				clbstmt.setString(i++,obj.getProductDesc());	

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

	public boolean deleteProduct(String proId) throws Exception {
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
				
				clbstmt = conn.prepareCall("{call "+INSTANT_DB_NAME+".DELETE_FAKE_PRODUCT(?)}");
				clbstmt.setString(i++,proId);				    
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

	
	public ProductBean getProduct(String proId) throws Exception {
		CallableStatement clbstmt = null;
		Connection conn = null;
		int i = 1;
		//***********
		ProductBean obj = null;
		try{
				//connection db
				conn = open();
				Log.debug("-->Before Retrieve data.");
				clbstmt = conn.prepareCall("{call "+INSTANT_DB_NAME+".GET_PRODUCT(?,?,?,?,?,?,?,?,?,?,?,?)}");
				clbstmt.setString(i++,proId);
				clbstmt.registerOutParameter(i++, java.sql.Types.VARCHAR);
				clbstmt.registerOutParameter(i++, java.sql.Types.INTEGER);
				clbstmt.registerOutParameter(i++, java.sql.Types.INTEGER);
				clbstmt.registerOutParameter(i++, java.sql.Types.FLOAT);
				clbstmt.registerOutParameter(i++, java.sql.Types.FLOAT);
				clbstmt.registerOutParameter(i++, java.sql.Types.INTEGER);
				clbstmt.registerOutParameter(i++, java.sql.Types.DATE);
				clbstmt.registerOutParameter(i++, java.sql.Types.DATE);
				clbstmt.registerOutParameter(i++, java.sql.Types.VARCHAR);
				clbstmt.registerOutParameter(i++, java.sql.Types.VARCHAR);
				clbstmt.registerOutParameter(i++, java.sql.Types.VARCHAR);

				System.out.println("Mapping parameter successfully.");
				clbstmt.execute();
				//result = clbstmt.executeUpdate();	
				Log.debug("--->Execute GET_PRODUCT :"+proId);     			        
				//get out parameter
				obj = new ProductBean();
				obj.setProductId(proId);
				obj.setProductName(clbstmt.getString("p_name"));
				obj.setTypeProductId(clbstmt.getString("p_type_id"));
				obj.setBrandProductId(clbstmt.getString("p_brand_id"));
				obj.setPriceCost(clbstmt.getFloat("p_cost"));
				obj.setPriceSale(clbstmt.getFloat("p_sale"));
				obj.setProductItem(clbstmt.getInt("p_item"));
				obj.setProductDateStart(clbstmt.getString("p_d_start"));
				obj.setProductDateExpire(clbstmt.getString("p_d_expire"));
				obj.setProductDesc(clbstmt.getString("p_desc"));
				obj.setBrandProductName(clbstmt.getString("p_brandName"));
				obj.setTypeProductName(clbstmt.getString("p_typeName"));

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

	public ArrayList listProduct() throws Exception {
		// Log.debug("List WatchdogForm..");
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String sql = "";
		ProductBean obj= null;
		ArrayList result = new ArrayList();		
		try {			
			conn = open();
			// Common.beginTransaction(conn);
			Log.debug("-->Query.");
			// setTransaction
			sql = " SELECT p.product_id,"+
		          " p.product_name ,"+
		          " p.type_product_id  ,"+
		          " p.brand_product_id ,"+
		          " p.price_cost,"+
		          " p.price_sale ,"+
		          " p.product_item ,"+
		          " p.product_date_start,"+
		          " p.product_date_expire ,"+
		          " p.product_desc ,"+
		          " b.brand_product_name,"+
		          " t.type_product_name"+
		          " FROM product p, brand_product b, type_product t"+
		          " WHERE (p.delete_flag <> 'D' OR   p.delete_flag  is null  OR p.delete_flag <> '' ) "+
		          " and b.brand_product_id = p.brand_product_id "+
		          " and t.type_product_id = p.type_product_id ";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			Log.debug("--->execute SQL Query.");
			int c = 0;
			while (rs.next()) {
				obj = new ProductBean();
				obj.setProductId(rs.getString("product_id"));
				obj.setProductName(rs.getString("product_name"));
				obj.setTypeProductId(rs.getString("type_product_id"));
				obj.setBrandProductId(rs.getString("brand_product_id"));
				obj.setPriceCost(rs.getFloat("price_cost"));
				obj.setPriceSale(rs.getFloat("price_sale"));
				obj.setProductItem(rs.getInt("product_item"));
				obj.setProductDateStart(rs.getString("product_date_start"));
				obj.setProductDateExpire(rs.getString("product_date_expire"));
				obj.setProductDesc(rs.getString("product_desc"));
				obj.setBrandProductName(rs.getString("brand_product_name"));
				obj.setTypeProductName(rs.getString("type_product_name"));			
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
			}catch (Exception e) {
			}
		}
		return result;
	}
}
