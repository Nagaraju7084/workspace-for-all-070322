package com.medi.scedule.exception;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.medi.scedule.bean.DoctorsScheduleErrorBean;

@ControllerAdvice
public class DoctorScheduleExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(DoctorScheduleException.class)
	public ResponseEntity<DoctorsScheduleErrorBean> handleDoctorScheduleException(DoctorScheduleException sne){
		DoctorsScheduleErrorBean errorBean = new DoctorsScheduleErrorBean();
		errorBean.setErrorCode(sne.getErrorCode());
		errorBean.setErrorMessage(sne.getErrorMessage());
		errorBean.setTime(LocalDate.now());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorBean);
	}
	
	@ExceptionHandler(AvailableDaysException.class)
	public ResponseEntity<DoctorsScheduleErrorBean> handleAvailableDaysException(AvailableDaysException sne){
		DoctorsScheduleErrorBean errorBean = new DoctorsScheduleErrorBean();
		errorBean.setErrorCode(sne.getErrorCode());
		errorBean.setErrorMessage(sne.getErrorMessage());
		errorBean.setTime(LocalDate.now());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorBean);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		System.out.println("the status of the arguement validation is:\t"+status.value());
		    List<DoctorsScheduleErrorBean> errorsList = new ArrayList<DoctorsScheduleErrorBean>();
		    
		   ex.getBindingResult().getFieldErrors().forEach(errorObject->{
			   DoctorsScheduleErrorBean error = new DoctorsScheduleErrorBean();
			   error.setErrorCode(status.value());
			   error.setErrorMessage(errorObject.getDefaultMessage());
			   error.setField(errorObject.getField());
			   errorsList.add(error);
			   
		   });
		
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorsList);
	}
	
}
