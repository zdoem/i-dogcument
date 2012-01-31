package com.ipro.dao.service;
import java.util.ArrayList;
import com.ipro.model.bean.BrandProduct;
public interface MasterBrandProduct {
	
	public boolean insertBrandProduct(BrandProduct bProduct) throws Exception;
	public boolean updateBrandProduct(BrandProduct bProduct) throws Exception;
	public boolean deleteBrandProduct(int bId) throws Exception;
	public BrandProduct getBrandProduct(int bId) throws Exception;
	public ArrayList  listBrandProduct() throws Exception;

}
