package com.ipro.dao.service;
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

	public boolean insertSupplier(SupplierBean sup) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean updateSupplier(SupplierBean sup) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deleteSupplier(String supId) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	public SupplierBean getSupplier(String supId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList listSupplier() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
