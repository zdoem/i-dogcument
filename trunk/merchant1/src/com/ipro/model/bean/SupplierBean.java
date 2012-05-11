package com.ipro.model.bean;

import java.io.Serializable;

public class SupplierBean  implements Serializable {
    private Integer suppId;
    private String suppCompanyName;
    private String suppContactName;
    private String suppEmail;
    private String suppTel;
    private String suppMobile;
    private String suppAddress;
    private String suppDesc;
    
	public Integer getSuppId() {
		return suppId;
	}
	public void setSuppId(Integer suppId) {
		this.suppId = suppId;
	}
	public String getSuppCompanyName() {
		return suppCompanyName;
	}
	public void setSuppCompanyName(String suppCompanyName) {
		this.suppCompanyName = suppCompanyName;
	}
	public String getSuppContactName() {
		return suppContactName;
	}
	public void setSuppContactName(String suppContactName) {
		this.suppContactName = suppContactName;
	}
	public String getSuppEmail() {
		return suppEmail;
	}
	public void setSuppEmail(String suppEmail) {
		this.suppEmail = suppEmail;
	}
	public String getSuppTel() {
		return suppTel;
	}
	public void setSuppTel(String suppTel) {
		this.suppTel = suppTel;
	}
	public String getSuppMobile() {
		return suppMobile;
	}
	public void setSuppMobile(String suppMobile) {
		this.suppMobile = suppMobile;
	}
	public String getSuppAddress() {
		return suppAddress;
	}
	public void setSuppAddress(String suppAddress) {
		this.suppAddress = suppAddress;
	}
	public String getSuppDesc() {
		return suppDesc;
	}
	public void setSuppDesc(String suppDesc) {
		this.suppDesc = suppDesc;
	}

}
