package postech.soat.tech.challenge.validation;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class DomainInvalidException extends RuntimeException {
    @Getter
    private List<String> validationErrors = new ArrayList<>();

    public DomainInvalidException(List<String> validationErrors, String message) {
        super(message);
        this.validationErrors = validationErrors;
    }
}
