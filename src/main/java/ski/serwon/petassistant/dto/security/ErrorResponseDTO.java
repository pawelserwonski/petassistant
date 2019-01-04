package ski.serwon.petassistant.dto.security;


import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.io.StringWriter;
import java.time.LocalDateTime;


public class ErrorResponseDTO {

    private final String timestamp = LocalDateTime.now().toString();
    private final String path;

    private final HttpStatus httpStatus;
    private final Throwable exception;
    private final boolean enableStackTrace;

    @Builder
    public ErrorResponseDTO(HttpStatus httpStatus, Throwable exception, boolean enableStackTrace, String path) {
        this.httpStatus = httpStatus;
        this.exception = exception;
        this.enableStackTrace = enableStackTrace;
        this.path = path;
    }

    public Integer getStatus() {
        return httpStatus != null ? httpStatus.value() : null;
    }

    public String getError() {
        return httpStatus != null ? httpStatus.getReasonPhrase() : null;
    }

    public String getMessage() {
        return exception != null ? exception.getMessage() : null;
    }

    public String getTrace() {
        if (enableStackTrace && exception != null) {
            StringWriter stackTrace = new StringWriter();
            stackTrace.flush();
            return stackTrace.toString();
        } else {
            return null;
        }
    }

    public String getException() {
        return enableStackTrace && exception != null
                ? exception.getClass().getName()
                : null;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getPath() {
        return path;
    }
}
