package postech.soat.tech.challenge.api.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import postech.soat.tech.challenge.api.response.ApiErrorResponse;
import postech.soat.tech.challenge.api.response.ApiResponse;
import postech.soat.tech.challenge.validation.DomainInvalidException;

import java.time.LocalDateTime;
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

    @ExceptionHandler(DomainInvalidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse handleValidationExceptions(DomainInvalidException ex) {
        logger.warning(ex.getMessage());
        return new ApiResponse<>(
                postech.soat.tech.challenge.api.response.ResponseStatus.BAD_REQUEST,
                null,
                ex.getValidationErrors().stream().map(ApiErrorResponse::new).toList(),
                LocalDateTime.now()
        );
    }
}
