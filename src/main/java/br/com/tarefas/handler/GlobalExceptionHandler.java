package br.com.tarefas.handler;

import br.com.tarefas.dto.ErroResponse;
import br.com.tarefas.dto.ValidateError;
import br.com.tarefas.exception.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErroResponse> handlerApiException(ApiException apiException){
        HttpStatus status = apiException.getHttpStatus();

        ErroResponse error = new ErroResponse(
                apiException.getCode(),
                apiException.getMessage(),
                status.value()
        );
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResponse> handleValidationException(MethodArgumentNotValidException validException){
        List<ValidateError> errors = validException.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> new ValidateError(fieldError.getField(), fieldError.getDefaultMessage()))
                .toList();
        ErroResponse erroResponse = new ErroResponse(
                "FIELD_VALIDATE_ERROR", "Existem não campos preenchidos corretamente",
                HttpStatus.BAD_REQUEST.value(),
                errors
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroResponse> handlerGenericException(Exception ex){
        ErroResponse erro = new ErroResponse("INTERNAL_SERVER_ERROR",
                "Ocorreu um erro inesperado!",
                HttpStatus.INTERNAL_SERVER_ERROR.value());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
    }
}
