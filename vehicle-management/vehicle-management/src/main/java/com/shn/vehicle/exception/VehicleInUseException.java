package com.shn.vehicle.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class VehicleInUseException extends RuntimeException{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VehicleInUseException(String message) {
        super(message);
    }
}
