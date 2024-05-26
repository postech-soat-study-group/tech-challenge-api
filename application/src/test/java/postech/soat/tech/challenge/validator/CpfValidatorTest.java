package postech.soat.tech.challenge.validator;

import org.junit.jupiter.api.Test;
import postech.soat.tech.challenge.validation.validator.CPFValidator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class CpfValidatorTest {

    @Test
    void testIsValidCPF() {
        String cpf = "81651786011";
        assertTrue(CPFValidator.isValidCPF(cpf));
    }

    @Test
    void shouldReturnFalseWhenIsInvalidCPF() {
        String cpf = "123456789111";
        assertFalse(CPFValidator.isValidCPF(cpf));
    }
}
