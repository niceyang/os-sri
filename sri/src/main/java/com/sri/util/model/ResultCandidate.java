package com.sri.util.model;

/**
 * 
 * Result candidate model (before query)
 * 
 * */
public class ResultCandidate {
	String index;
	PIITag tag;
	String query;
	
	public ResultCandidate(String index, PIITag tag) {
		super();
		this.index = index;
		this.tag = tag;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public PIITag getTag() {
		return tag;
	}

	public void setTag(PIITag tag) {
		this.tag = tag;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	@Override
	public String toString() {
		return "ResultCandidate [index=" + index + ", tag=" + tag + ", query=" + query + "]";
	}

}
