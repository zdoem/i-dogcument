package com.ipro.dao.service;
import java.util.ArrayList;
import com.ipro.model.bean.SupplierBean;

public interface MasterSupplier {
	public boolean insertSupplier(SupplierBean sup) throws Exception;
	public boolean updateSupplier(SupplierBean sup) throws Exception;
	public boolean deleteSupplier(String supId) throws Exception;
	public SupplierBean getSupplier(String supId) throws Exception;
	public ArrayList  listSupplier() throws Exception;
	
}
