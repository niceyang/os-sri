package com.sri.util.model;

import java.util.List;

public class PIITag {
	String table;
	String idField;
	String categoryMain;
	List<String> categorySub1;
	List<String> categorySub2;
	List<String> piiFields;
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
	public String getIdField() {
		return idField;
	}
	public void setIdField(String idField) {
		this.idField = idField;
	}
	public String getCategoryMain() {
		return categoryMain;
	}
	public void setCategoryMain(String categoryMain) {
		this.categoryMain = categoryMain;
	}
	public List<String> getCategorySub1() {
		return categorySub1;
	}
	public void setCategorySub1(List<String> categorySub1) {
		this.categorySub1 = categorySub1;
	}
	public List<String> getCategorySub2() {
		return categorySub2;
	}
	public void setCategorySub2(List<String> categorySub2) {
		this.categorySub2 = categorySub2;
	}
	public List<String> getPiiFields() {
		return piiFields;
	}
	public void setPiiFields(List<String> piiFields) {
		this.piiFields = piiFields;
	}
	
	@Override
	public String toString() {
		return "PIITag [table=" + table + ", idField=" + idField + ", categoryMain=" + categoryMain + ", categorySub1="
				+ categorySub1 + ", categorySub2=" + categorySub2 + ", piiFields=" + piiFields + "]";
	}
}
