package com.shn.reservation.dto.client;

import com.shn.reservation.dto.exception.ErrorMessage;
import com.shn.reservation.dto.exception.VehicleInUseException;
import com.shn.reservation.dto.exception.ResourceNotFoundException;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class ErrorMessageDecoder implements ErrorDecoder {
    private final ErrorDecoder errorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        ErrorMessage errorMessage = null;
        try (InputStream body = response.body().asInputStream()) {
            errorMessage = new ErrorMessage(
                    (String) response.headers().get("date").toArray()[0],
                    response.status(),
                    new String(body.readAllBytes(), StandardCharsets.UTF_8),
                    response.request().url()
            );
        }catch (IOException exception){
            return new Exception(exception.getMessage());
        }
        return switch (response.status()) {
            case 404 -> throw new ResourceNotFoundException(errorMessage);
            case 409 -> throw new VehicleInUseException(errorMessage);
            default -> errorDecoder.decode(methodKey, response);
        };
    }
}
