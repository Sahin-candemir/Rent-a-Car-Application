package com.shn.reservation.dto.exception;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ErrorMessage errorMessage;

    public ResourceNotFoundException(ErrorMessage errorMessage) {
        super(errorMessage.message());
        this.errorMessage = errorMessage;
    }

}
