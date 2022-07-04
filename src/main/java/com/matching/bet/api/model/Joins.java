package com.matching.bet.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Joins {
	
	private String type;
	private String collection;
	private String field1;
	private String field2;
	
	@Override
	public String toString() {
		return "Joins [type=" + type + ", collection=" + collection + ", field1=" + field1 + ", field2=" + field2 + "]";
	}

}
