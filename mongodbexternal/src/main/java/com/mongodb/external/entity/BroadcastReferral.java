package com.mongodb.external.entity;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document // convert object as json format
@Data
public class BroadcastReferral {


	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;

	public String memberId;
	private String memberName;
	private String medicaidId;
	private String memberGroup;
	private LocalDate dob;
	private String memberGender;
	private String memberPhone;
	private String referralStatus;
	private LocalDate broadcastDate;
	private List<String> memberAvailabilityDays;
	private String memberAvailabilityTime;
	private String caregiverInvolved;
	private String careGiver;
	private String caregiverPhoneNumber;
	private LocalDate providerResponseNeededbyDate;
	private String memberAddress;
	private String county;
	private String languagePreference;
	private String genderPreferenceOfProvider;
	private String isSmoker;
	private String petFriendly;
	private String providerName;
	private String providerPhoneNumber;
	private String providerTaxID;
	private String providerNPI;
	private String contingencyProviderName;
	private String contingencyProviderPhoneNumber;
	private String contigencyTaxId;
	private String contingencyNPI;
	private String zip;
	private Services service;
	
	private List<ProviderResponse> providerResponses;
	
	@Transient
	private Owner owner;
	
	@Data
	public static class ProviderResponse{
		
		private ProviderInfo provider;		
		private ProviderAction action;		
	}
	
	@Data
	public static class ProviderAction{
        private String code;                                       
        private String reason;
        private String description;
        private String reasonInfo;
        private String startDate;                                        
        private String meetGreetDate;                             
        private ContactInfo contact;
        private String providerStatus;
	}
	
	@Data
	public static class ProviderInfo{
		private String tin;                                       
        private String npi;                                       
        private String name;                                       
        private String state;                                       
        private String firstName;                                       
        private String lastName;
	}
	
	@Data
	public static class ContactInfo{
		private String firstName;           
		private String lastName;                        
		private String email;     
		private String phone;                               
		private String requestMoreInfo;
	}
}