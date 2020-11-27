package de.validation;

/**
 * ValidationError in de.jns.validation (Java-Network-Server)
 * <p>
 * Class description...
 *
 * @author
 * @version ...
 * @date 26.11.2020
 **/
public class ValidationError {

    private final String errorSource;
    private final Object value;
    private final ErrorType reason;

    public ValidationError(String errorSource, Object value, ErrorType reason) {
        this.errorSource = errorSource;
        this.value = value;
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "ValidationError{"
                + "errorType=" + reason
                + ", errorSource='" + errorSource + '\''
                + ", givenValue=" + value
                + '}';
    }

    public enum ErrorType {
        EMERGENCY(0),
        ALERT(1),
        CRITICAL(2),
        ERROR(3),
        WARNING(4),
        NOTICE(5),
        INFORMATIONAL(6),
        DEBUG(7);

        final int code;

        ErrorType(int i) {
            code = i;
        }

        public int getCode() {
            return code;
        }
    }

}
