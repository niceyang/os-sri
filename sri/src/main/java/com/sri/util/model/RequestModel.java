package com.sri.util.model;
/**
 * 
 * The model for 
 * 
 */
public class RequestModel {
	String piType;
	String piData;
	TopologyModel topology;
	
	public RequestModel(String piType, String piData, TopologyModel topology) {
		super();
		this.piType = piType;
		this.piData = piData;
		this.topology = topology;
	}
	
	public String getPiType() {
		return piType;
	}
	public void setPiType(String piType) {
		this.piType = piType;
	}
	public String getPiData() {
		return piData;
	}
	public void setPiData(String piData) {
		this.piData = piData;
	}
	public TopologyModel getTopology() {
		return topology;
	}
	public void setTopology(TopologyModel topology) {
		this.topology = topology;
	}
}







