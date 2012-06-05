package com.ipro.dao.service;
import java.util.List;
import com.ipro.model.bean.PaymentBean;
public interface TransOrderProduct {	
	public boolean insertTransOrder(List list,String invoidId) throws Exception;
	public boolean updateTransPayin(PaymentBean obj) throws Exception;

}
