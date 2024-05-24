package postech.soat.tech.challenge.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
public class ApiResponse<T> {
    private final ResponseStatus status;
    private final T data;
    private final List<ApiErrorResponse> errors;
    private final LocalDateTime timestamp;

    public ApiResponse(T data) {
        this.status = ResponseStatus.SUCCESS;
        this.timestamp = LocalDateTime.now();
        this.data = data;
        this.errors = null;
    }

    public ApiResponse(List<ApiErrorResponse> errors) {
        this.status = ResponseStatus.ERROR;
        this.timestamp = LocalDateTime.now();
        this.data = null;
        this.errors = errors;
    }
}
