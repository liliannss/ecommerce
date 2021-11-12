package br.com.devs.javagirl.user.handlers;

import br.com.devs.javagirl.user.models.dtos.ErrorDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.sql.Timestamp.from;
import static java.time.Instant.now;
import static java.util.UUID.randomUUID;

@AllArgsConstructor
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final HttpServletRequest httpServletRequest;

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Object> responseStatusException(ResponseStatusException ex) {
        return handleExceptionInternal(ex, getErrorDTO(ex.getStatus(), Collections.singletonList(ex.getMessage()), httpServletRequest), null, ex.getStatus(), null);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleExceptionInternal(ex, getErrorDTO(status, Collections.singletonList(ex.getMessage()), httpServletRequest), headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> fieldErrorList = ex
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getDefaultMessage())
                .collect(Collectors.toList());
        return handleExceptionInternal(ex, getErrorDTO(status, fieldErrorList, httpServletRequest), headers, status, request);
    }

    private ErrorDTO getErrorDTO(HttpStatus httpStatus, List<String> message, HttpServletRequest httpServletRequest) {
        return ErrorDTO
                .builder()
                .status(httpStatus)
                .path(httpServletRequest.getRequestURL().toString())
                .method(httpServletRequest.getMethod())
                .message(message)
                .errorId(randomUUID().toString())
                .instantCreated(from(now()))
                .build();
    }
}