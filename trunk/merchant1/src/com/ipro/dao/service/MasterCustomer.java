package com.ipro.dao.service;
import java.util.ArrayList;
import com.ipro.model.bean.CustomerBean;

public interface MasterCustomer {
	public boolean insertCustomers(CustomerBean customer) throws Exception;
	public boolean updateCustomers(CustomerBean customer) throws Exception;
	public boolean deleteCustomers(String cusId) throws Exception;
	public CustomerBean getCustomers(String cusId) throws Exception;
	public ArrayList  listCustomers() throws Exception;

}
