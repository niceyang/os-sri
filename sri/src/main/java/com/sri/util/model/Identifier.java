package com.sri.util.model;

public class Identifier {
	String table;
	String field;
	String type;
	
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "Identifier [table=" + table + ", field=" + field + ", type=" + type + "]";
	}
}
