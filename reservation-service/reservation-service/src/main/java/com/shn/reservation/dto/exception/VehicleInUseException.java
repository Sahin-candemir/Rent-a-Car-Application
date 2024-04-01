package com.shn.reservation.dto.exception;

import lombok.Getter;

@Getter
public class VehicleInUseException extends RuntimeException{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ErrorMessage errorMessage;

    public VehicleInUseException(ErrorMessage errorMessage) {
        super(errorMessage.message());
        this.errorMessage = errorMessage;
    }
}
