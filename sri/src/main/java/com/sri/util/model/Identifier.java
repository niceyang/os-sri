package com.sri.util.model;

public class Identifier {
	String table;
	String field;
	String type;
	String idField;
	
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
	public String getIdField() {
		return idField;
	}
	public void setIdField(String idField) {
		this.idField = idField;
	}
	public Identifier() {}
	
	public Identifier(String table, String field, String type, String idField) {
		super();
		this.table = table;
		this.field = field;
		this.type = type;
		this.idField = idField;
	}
}
