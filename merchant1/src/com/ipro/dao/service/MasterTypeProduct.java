package com.ipro.dao.service;
import java.util.ArrayList;
import com.ipro.model.bean.TypeProduct;
public interface MasterTypeProduct {
	
	public boolean insertTypeProduct(TypeProduct tProduct) throws Exception;
	public boolean updateTypeProduct(TypeProduct tProduct) throws Exception;
	public boolean deleteTypeProduct(int tId) throws Exception;
	public TypeProduct getTypeProduct(int tId) throws Exception;
	public ArrayList  listTypeProduct() throws Exception;

}
