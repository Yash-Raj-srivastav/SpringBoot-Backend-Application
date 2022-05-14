package com.yash.blog.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException{

	String resourceName;
	String fieldName;
	long fieldValuel;
	public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
		super();
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValuel = fieldValue;
	}
}
