package postech.soat.tech.challenge.validation;

import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
public class DomainValidationResult {
    private final List<String> errors = new ArrayList<>();

    public boolean isValid() {
        return this.errors.isEmpty();
    }

    public void addError(String error) {
        this.errors.add(error);
    }

    public String getErrorsMessage() {
        return String.join(", ", this.errors);
    }
}
