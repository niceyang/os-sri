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
}







