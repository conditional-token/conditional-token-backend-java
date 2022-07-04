package com.matching.bet.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Filters {
	
	private String filter;
	private String value;
	private String operator;
	private String type;
	
	public String getValue() {
		if(getType() != null && getType().equalsIgnoreCase("operation")) {
			return value;
		}
		return " '" + value + "'";
	}
	
	@Override
	public String toString() {
		return "Filters [filter=" + filter + ", value=" + value + ", operator=" + operator + "]";
	}
}
