package com.ipro.dao.service;
import java.util.ArrayList;
import com.ipro.model.bean.ProductBean;
public interface MasterProduct {
	
	public boolean insertProduct(ProductBean product) throws Exception;
	public boolean updateProduct(ProductBean product) throws Exception;
	public boolean deleteProduct(String proId) throws Exception;
	public ProductBean getProduct(String proId) throws Exception;
	public ArrayList  listProduct() throws Exception;

}
