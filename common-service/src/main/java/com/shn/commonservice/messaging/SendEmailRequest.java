package com.shn.commonservice.messaging;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendEmailRequest implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String emailAddress;
    private String message;
}
