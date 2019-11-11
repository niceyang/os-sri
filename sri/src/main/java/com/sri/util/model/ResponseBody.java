package com.sri.util.model;
/**
 * 
 * The model for the error response
 * 
 */
public class ResponseBody {
	String version;
	String companyID;
	String callbackID;
	String responseType;
	String data;
	
	public ResponseBody(String version, String companyID, String callbackID, String responseType, String data) {
		super();
		this.version = version;
		this.companyID = companyID;
		this.callbackID = callbackID;
		this.responseType = responseType;
		this.data = data;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getCompanyID() {
		return companyID;
	}

	public void setCompanyID(String companyID) {
		this.companyID = companyID;
	}

	public String getCallbackID() {
		return callbackID;
	}

	public void setCallbackID(String callbackID) {
		this.callbackID = callbackID;
	}

	public String getResponseType() {
		return responseType;
	}

	public void setResponseType(String responseType) {
		this.responseType = responseType;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
}







