package com.ipro.model.bean;
import java.io.Serializable;
public class UsersBean implements Serializable {

    private String id;
    private String prefix;
    private String fName;
    private String lName;
    private String tel;
    private String mobile;
    private String email; 
    private String address;
    private String login;
    private String password;
    private String activate;
    private String citizenId;
    private String date;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getActivate() {
		return activate;
	}
	public void setActivate(String activate) {
		this.activate = activate;
	}
	public String getCitizenId() {
		return citizenId;
	}
	public void setCitizenId(String citizenId) {
		this.citizenId = citizenId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
    
}
