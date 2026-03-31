package augusto108.ces.phonebook.controller.handlers;

import augusto108.ces.phonebook.exceptions.UUIDNumberFormatException;
import augusto108.ces.phonebook.exceptions.UnmatchedIdException;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.NoResultException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.Instant;
import java.util.Date;

@ControllerAdvice
public class ApplicationExceptionHandler {

	@ExceptionHandler({NoResultException.class, NoHandlerFoundException.class})
	public ResponseEntity<ErrorResponse> handleNotFound(Exception e) {
		final ErrorResponse response = new ErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND, Date.from(Instant.now()));
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}

	@ExceptionHandler({UnmatchedIdException.class, UUIDNumberFormatException.class, HttpMessageNotReadableException.class})
	public ResponseEntity<ErrorResponse> handleUnmatchedId(RuntimeException e) {
		final ErrorResponse response = new ErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST, Date.from(Instant.now()));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
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
