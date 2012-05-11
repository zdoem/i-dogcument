package com.ipro.model.domain;

public class ProductForm {
	
	 private String productId;
	 private String productName;
	 private Float priceCost;
	 private Float priceSale;
	 private Integer productItem;
	 private String productDateStart;
	 private String productDateExpire;
	 private String productDesc;
	 private String brandProductIdDDL;
	 private String typeProductIdDDL;
	 private String cmd;
	 
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Float getPriceCost() {
		return priceCost;
	}
	public void setPriceCost(Float priceCost) {
		this.priceCost = priceCost;
	}
	public Float getPriceSale() {
		return priceSale;
	}
	public void setPriceSale(Float priceSale) {
		this.priceSale = priceSale;
	}
	public Integer getProductItem() {
		return productItem;
	}
	public void setProductItem(Integer productItem) {
		this.productItem = productItem;
	}
	public String getProductDateStart() {
		return productDateStart;
	}
	public void setProductDateStart(String productDateStart) {
		this.productDateStart = productDateStart;
	}
	public String getProductDateExpire() {
		return productDateExpire;
	}
	public void setProductDateExpire(String productDateExpire) {
		this.productDateExpire = productDateExpire;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public String getBrandProductIdDDL() {
		return brandProductIdDDL;
	}
	public void setBrandProductIdDDL(String brandProductIdDDL) {
		this.brandProductIdDDL = brandProductIdDDL;
	}
	public String getTypeProductIdDDL() {
		return typeProductIdDDL;
	}
	public void setTypeProductIdDDL(String typeProductIdDDL) {
		this.typeProductIdDDL = typeProductIdDDL;
	}
	public String getCmd() {
		return cmd;
	}
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

}
