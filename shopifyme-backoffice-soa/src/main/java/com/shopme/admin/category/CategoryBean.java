package com.shopme.admin.category;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class CategoryBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	
	private String alias;
	
	private MultipartFile fileImage;
	
	private boolean enabled;
	
	public CategoryBean() {
	}
	
}
