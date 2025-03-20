package com.shopifyme.gateway;

import org.springframework.http.HttpStatus;

public class UserMgmtException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final HttpStatus statusCode;
    private final String errorMessage;

    public UserMgmtException(HttpStatus statusCode, String errorMessage) {
        this.statusCode = statusCode;
        this.errorMessage = errorMessage;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}

