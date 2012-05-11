package com.ipro.dao.service;
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


	public boolean insertProduct(ProductBean product) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean updateProduct(ProductBean product) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deleteProduct(String proId) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	public ProductBean getProduct(String proId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList listProduct() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
