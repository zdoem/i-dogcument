package com.ipro.model.bean;
import java.io.Serializable;
public class CustomerBean  implements Serializable {
	
	    private String cusId;
	    private String cusPrefix;
	    private String cusFname;
	    private String cusLname;
	    private String cusTel;
	    private String cusMobile;
	    private String cusEmail;
	    private String cusAddress;
	    private String cusFlag;
	    private String cusStartDate;
	    private String cusComment;
	    
	    
		public String getCusId() {
			return cusId;
		}
		public void setCusId(String cusId) {
			this.cusId = cusId;
		}
		public String getCusPrefix() {
			return cusPrefix;
		}
		public void setCusPrefix(String cusPrefix) {
			this.cusPrefix = cusPrefix;
		}
		public String getCusFname() {
			return cusFname;
		}
		public void setCusFname(String cusFname) {
			this.cusFname = cusFname;
		}
		public String getCusLname() {
			return cusLname;
		}
		public void setCusLname(String cusLname) {
			this.cusLname = cusLname;
		}
		public String getCusTel() {
			return cusTel;
		}
		public void setCusTel(String cusTel) {
			this.cusTel = cusTel;
		}
		public String getCusMobile() {
			return cusMobile;
		}
		public void setCusMobile(String cusMobile) {
			this.cusMobile = cusMobile;
		}
		public String getCusEmail() {
			return cusEmail;
		}
		public void setCusEmail(String cusEmail) {
			this.cusEmail = cusEmail;
		}
		public String getCusAddress() {
			return cusAddress;
		}
		public void setCusAddress(String cusAddress) {
			this.cusAddress = cusAddress;
		}
		public String getCusFlag() {
			return cusFlag;
		}
		public void setCusFlag(String cusFlag) {
			this.cusFlag = cusFlag;
		}
		public String getCusStartDate() {
			return cusStartDate;
		}
		public void setCusStartDate(String cusStartDate) {
			this.cusStartDate = cusStartDate;
		}
		public String getCusComment() {
			return cusComment;
		}
		public void setCusComment(String cusComment) {
			this.cusComment = cusComment;
		}
}
