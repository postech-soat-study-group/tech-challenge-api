package postech.soat.tech.challenge.model;


import lombok.Getter;
import postech.soat.tech.challenge.validation.DomainInvalidException;
import postech.soat.tech.challenge.validation.DomainValidationResult;
import postech.soat.tech.challenge.validation.validator.CPFValidator;

@Getter
public class Customer {

    private final long id;
    private final String name;
    private final String cpf;
    private final String email;
    private final String phone;

    public Customer(
            long id,
            String name,
            String cpf,
            String email,
            String phone
    ) {
        DomainValidationResult validationResult = validate(name, cpf, email, phone);

        if (!validationResult.isValid()) {
            throw new DomainInvalidException(validationResult.getErrors(), validationResult.getErrorsMessage());
        }

        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.phone = phone;
    }

    private DomainValidationResult validate(
            String name,
            String cpf,
            String email,
            String phone
    ) {

        DomainValidationResult domainValidationResult = new DomainValidationResult();
        if (name == null || name.isEmpty()) {
            domainValidationResult.addError("Name is required");
        }

        if (cpf == null || cpf.isEmpty() || (!CPFValidator.isValidCPF(cpf)) && !cpf.equals("00000000019")) {
            domainValidationResult.addError("CPF is empty or invalid.");
        }

        if ((email == null || email.isEmpty()) && (phone == null || phone.isEmpty())) {
            domainValidationResult.addError("E-mail or Phone is required.");
        }

        return domainValidationResult;
    }

}
