package com.ipro.model.bean;

import java.io.Serializable;

public class PaymentBean  implements Serializable {
	private String invoidId;
	private String pDate;
	private float amount;
	private String flag;
	private String type;
	private String bankRef;
	
	public String getInvoidId() {
		return invoidId;
	}
	public void setInvoidId(String invoidId) {
		this.invoidId = invoidId;
	}
	public String getpDate() {
		return pDate;
	}
	public void setpDate(String pDate) {
		this.pDate = pDate;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getBankRef() {
		return bankRef;
	}
	public void setBankRef(String bankRef) {
		this.bankRef = bankRef;
	}
}
