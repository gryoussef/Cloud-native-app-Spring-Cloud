package ma.s.gcm.exception;

public class SuiviClientsExceptionMapper {

    private SuiviClientsExceptionMapper() {
    }

    public static ExceptionDto toExceptionDto(SuiviClientsException exception) {
        return new ExceptionDto()
                .setCode(exception.getExceptionCode().name())
                .setMessage(exception.getMsg())
                .setTicketId(exception.getTickedId())
                .setTime(exception.getTime())
                .setStatus(exception.getExceptionCode().getStatus())
                .setErrors(exception.getErrors());
    }
}
