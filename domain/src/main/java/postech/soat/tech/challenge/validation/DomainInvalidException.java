package postech.soat.tech.challenge.validation;

public class DomainInvalidException extends RuntimeException {
    private Class<?> domainClass;

    public DomainInvalidException(Class<?> domainClass, String message) {
        super(message);
        this.domainClass = domainClass;
    }
}
