package br.com.tarefas.handler;

import br.com.tarefas.dto.ErroResponse;
import br.com.tarefas.exception.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroResponse> handlerGenericException(Exception ex){
        ErroResponse erro = new ErroResponse("INTERNAL_SERVER_ERROR",
                "Ocorreu um erro inesperado!",
                HttpStatus.INTERNAL_SERVER_ERROR.value());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
    }
}
