package br.edu.ifpb.sinan.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // RFC 9457 para validações do @Valid
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail tratarErrosValidacao(MethodArgumentNotValidException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST, 
                "Um ou mais campos estão inválidos."
        );
        problemDetail.setTitle("Erro de Validação");
        problemDetail.setType(URI.create("https://sinan.ifpb.edu.br/erros/validacao"));

        Map<String, String> erros = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> 
            erros.put(error.getField(), error.getDefaultMessage())
        );
        problemDetail.setProperty("invalid-params", erros);

        return problemDetail;
    }

    // RFC 9457 para Regras de Negócio (IllegalArgumentException)
    @ExceptionHandler(IllegalArgumentException.class)
    public ProblemDetail tratarRegraDeNegocio(IllegalArgumentException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST, 
                ex.getMessage()
        );
        problemDetail.setTitle("Regra de Negócio Violada");
        problemDetail.setType(URI.create("https://sinan.ifpb.edu.br/erros/regra-de-negocio"));
        return problemDetail;
    }

    // RFC 9457 para JSON malformado
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ProblemDetail tratarJsonInvalido(HttpMessageNotReadableException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST, 
                "O corpo da requisição contém JSON malformado ou valores incompatíveis."
        );
        problemDetail.setTitle("Requisição Inválida");
        return problemDetail;
    }
}