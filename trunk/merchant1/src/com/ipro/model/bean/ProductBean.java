package com.ipro.model.bean;

import java.io.Serializable;

public class ProductBean  implements Serializable {

    private String productId;
    private String productName;
    private Float priceCost;
    private Float priceSale;
    private Integer productItem;
    private String productDateStart;
    private String productDateExpire;
    private String productDesc;
    private String brandProductId;
    private String typeProductId;
    private String brandProductName;
    private String typeProductName;
   
	public String getBrandProductName() {
		return brandProductName;
	}
	public void setBrandProductName(String brandProductName) {
		this.brandProductName = brandProductName;
	}
	public String getTypeProductName() {
		return typeProductName;
	}
	public void setTypeProductName(String typeProductName) {
		this.typeProductName = typeProductName;
	}
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
	public String getBrandProductId() {
		return brandProductId;
	}
	public void setBrandProductId(String brandProductId) {
		this.brandProductId = brandProductId;
	}
	public String getTypeProductId() {
		return typeProductId;
	}
	public void setTypeProductId(String typeProductId) {
		this.typeProductId = typeProductId;
	}
 
}
