package br.com.tarefas.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class ErroResponse {

    public ErroResponse(String code, String message, int status){
        this.code = code;
        this.message = message;
        this.status = status;
        validateError = null;
    }

    private String code;
    private String message;
    private int status;
    private List<ValidateError> validateError;
}

