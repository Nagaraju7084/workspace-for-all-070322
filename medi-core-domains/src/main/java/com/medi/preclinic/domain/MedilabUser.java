package com.medi.preclinic.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Table(name = "MedilabUser")
@Data
public class MedilabUser extends AuditInfo implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String firstName;
	private String lastName;
	private String userId;
	private String email;
	
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date dob;
	
	private String gender;
	private String shortBiography;
	private boolean status;
	
	@OneToOne
	private UserRole role;
	
	@OneToOne
	private Address postalAddress;
	
	@OneToOne
	private UserType userType;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Document> docsList;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Notification> notificationsList;

}
