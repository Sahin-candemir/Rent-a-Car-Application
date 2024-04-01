package com.shn.reservation.dto.exception;

public record ErrorMessage(
        String timeStamp,
        int status,
        String message,
        String path
) {
}
