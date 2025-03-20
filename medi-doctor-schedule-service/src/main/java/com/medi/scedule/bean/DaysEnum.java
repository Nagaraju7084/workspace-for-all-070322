/**
 * 
 */
package com.medi.scedule.bean;

import java.util.Arrays;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author SRI LALITHA DEVI
 *
 */
//@Entity
//@Table(name = "doctors-available-days")
public enum DaysEnum {
	
	SUNDAY(1,"Sunday"),
	MONNDAY(2,"Monday"),
	TUESDAY(3,"Tuesday"),
	WEDNESDAY(4,"Wednesday"),
	THURSDAY(5,"Thursday"),
	FRIDAY(6,"Friday"),
	SATURDAY(7,"Saturday");
	
	//@Id
	private int id;
	private String day;
	
	DaysEnum(int id, String day){
		this.id = id;
		this.day = day;
	}
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getDay() {
		return day;
	}



	public void setDay(String day) {
		this.day = day;
	}



	public static List<DaysEnum> findAllDays(){
		return Arrays.asList(DaysEnum.values());
	}

}
