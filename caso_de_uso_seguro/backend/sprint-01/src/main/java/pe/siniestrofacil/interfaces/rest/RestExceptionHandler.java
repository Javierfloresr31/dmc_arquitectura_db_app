package pe.siniestrofacil.interfaces.rest;

import pe.siniestrofacil.application.dto.ApiError;
import pe.siniestrofacil.application.exception.IdempotencyConflictException;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ApiError> validation(MethodArgumentNotValidException e) {
        List<String> d = e.getBindingResult().getFieldErrors().stream()
                .map(x -> x.getField() + ": " + x.getDefaultMessage())
                .toList();
        return ResponseEntity.badRequest()
                .body(new ApiError("VALIDATION_ERROR", "Solicitud inválida", UUID.randomUUID().toString(), d));
    }

    @ExceptionHandler(IdempotencyConflictException.class)
    ResponseEntity<ApiError> idempotencyConflict(IdempotencyConflictException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ApiError(
                        "IDEMPOTENCY_CONFLICT",
                        e.getMessage(),
                        UUID.randomUUID().toString(),
                        List.of()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    ResponseEntity<ApiError> business(IllegalArgumentException e) {
        return ResponseEntity.unprocessableEntity()
                .body(new ApiError("BUSINESS_ERROR", e.getMessage(), UUID.randomUUID().toString(), List.of()));
    }
}
