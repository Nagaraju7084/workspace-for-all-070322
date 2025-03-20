package com.medi.scedule.exception;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
//@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DoctorScheduleException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int errorCode;
	private String errorMessage;
	private Date time;
	public DoctorScheduleException(int errorCode, String errorMessage, Date time) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.time = time;
	}
	
}
