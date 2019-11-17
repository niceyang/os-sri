package com.sri.util.model;

import java.util.List;
import java.util.Map;

/**
 * 
 * Data model for per table
 * 
 * */
public class DataModel {
	String category;
	String subCategory1;
	String subCategory2;
	List<Map<String, Object>> data;
	
	public DataModel() {}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSubCategory1() {
		return subCategory1;
	}

	public void setSubCategory1(String subCategory1) {
		this.subCategory1 = subCategory1;
	}

	public String getSubCategory2() {
		return subCategory2;
	}

	public void setSubCategory2(String subCategory2) {
		this.subCategory2 = subCategory2;
	}

	public List<Map<String, Object>> getData() {
		return data;
	}

	public void setData(List<Map<String, Object>> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "DataModel [category=" + category + ", subCategory1=" + subCategory1 + ", subCategory2=" + subCategory2
				+ ", data=" + data + "]";
	}
	
}
