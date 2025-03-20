package com.shopifyme.brands.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.shopifyme.ms.commons.domains.IdBasedEntity;

import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "brands")
@Getter
@Setter
public class Brand extends IdBasedEntity {
	
	@Column(nullable = false, length = 45, unique = true)
	private String name;
	
	@Column(nullable = false, length = 128)
	private String logo;
	
	private String categories;
		
}
