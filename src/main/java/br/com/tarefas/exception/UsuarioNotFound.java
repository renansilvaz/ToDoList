package br.com.tarefas.exception;

import org.springframework.http.HttpStatus;

public class UsuarioNotFound extends RuntimeException implements ApiException{
    private final String code = "USUARIO_NOT_FOUND";
    private String message;

    public UsuarioNotFound(String message) {
        this.message = message;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage(){
        return this.message;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
