package com.ipro.dao.service;
import java.util.ArrayList;
import com.ipro.model.bean.UsersBean;

public interface MasterUser {
	public boolean insertUsers(UsersBean user) throws Exception;
	public boolean updateUsers(UsersBean user) throws Exception;
	public boolean deleteUsers(String usrId) throws Exception;
	public UsersBean getUsers(String usrId) throws Exception;
	public ArrayList  listUsers() throws Exception;

}
