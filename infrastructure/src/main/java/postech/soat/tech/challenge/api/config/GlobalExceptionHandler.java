package postech.soat.tech.challenge.api.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import postech.soat.tech.challenge.api.response.ApiErrorResponse;
import postech.soat.tech.challenge.api.response.ApiResponse;

import java.util.List;
import java.util.logging.Logger;

@RestControllerAdvice
public class GlobalExceptionHandler {

    Logger logger = Logger.getLogger(GlobalExceptionHandler.class.getName());

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse handleValidationExceptions(RuntimeException ex) {
        logger.severe(ex.getMessage());
        var errorList = List.of(new ApiErrorResponse("An unknown error occurred"));

        return new ApiResponse<>(errorList);
    }
}
