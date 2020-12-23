package ma.s.gcm.exception;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class SuiviClientsException extends RuntimeException {

    private ExceptionCode exceptionCode;
    private String tickedId;
    private String msg;
    private String technicalMessage;
    private LocalDateTime time;
    private Set<Error> errors = new HashSet<>();

    public SuiviClientsException(ExceptionCode exceptionCode) {
        this(exceptionCode, null, null, null);
    }

    public SuiviClientsException(ExceptionCode exceptionCode, String message) {
        this(exceptionCode, message, null, null);
    }

    public SuiviClientsException(ExceptionCode exceptionCode, String message, String technicalMessage) {
        this(exceptionCode, message, technicalMessage, null);
    }

    public SuiviClientsException(ExceptionCode exceptionCode, String message, String technicalMessage, Throwable cause) {
        super(message, cause);
        this.msg = message;
        this.technicalMessage = technicalMessage;
        this.exceptionCode = exceptionCode;
        tickedId = UUID.randomUUID().toString();
        this.time = LocalDateTime.now();
    }

    public ExceptionCode getExceptionCode() {
        return exceptionCode;
    }

    public SuiviClientsException setExceptionCode(ExceptionCode exceptionCode) {
        this.exceptionCode = exceptionCode;
        return this;
    }

    public String getTickedId() {
        return tickedId;
    }

    public SuiviClientsException setTickedId(String tickedId) {
        this.tickedId = tickedId;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public SuiviClientsException setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public String getTechnicalMessage() {
        return technicalMessage;
    }

    public SuiviClientsException setTechnicalMessage(String technicalMessage) {
        this.technicalMessage = technicalMessage;
        return this;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public SuiviClientsException setTime(LocalDateTime time) {
        this.time = time;
        return this;
    }

    public Set<Error> getErrors() {
        return errors;
    }

    public SuiviClientsException setErrors(Set<Error> errors) {
        this.errors = errors;
        return this;
    }
}