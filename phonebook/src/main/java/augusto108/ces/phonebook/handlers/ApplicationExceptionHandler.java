package augusto108.ces.phonebook.handlers;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.NoResultException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.Date;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(NoResultException.class)
    public ResponseEntity<ErrorResponse> handleNoResultFoundException(NoResultException e) {
        final ErrorResponse response = new ErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND, Date.from(Instant.now()));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    public record ErrorResponse(
            String message,
            HttpStatus httpStatus,
            int statusCode,
            @JsonFormat(pattern = "dd MMM yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING) Date timestamp
    ) {
        public ErrorResponse(String message, HttpStatus status, Date timestamp) {
            this(message, status, status.value(), timestamp);
        }
    }
}
