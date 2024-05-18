package postech.soat.tech.challenge.api.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import postech.soat.tech.challenge.api.response.ApiErrorResponse;
import postech.soat.tech.challenge.api.response.ApiResponse;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse handleValidationExceptions(RuntimeException ex) {
        var errorList = List.of(new ApiErrorResponse("An unknown error occurred."));

        return new ApiResponse<>(errorList);
    }
}
