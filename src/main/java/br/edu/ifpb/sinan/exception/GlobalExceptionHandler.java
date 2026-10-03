package br.edu.ifpb.sinan.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErroResposta> erroArgumentoIlegal(IllegalArgumentException ex) {
        ErroResposta erro = new ErroResposta(
            HttpStatus.BAD_REQUEST.value(),
            "Regra de Negócio violada", 
            ex.getMessage()
        );

        return new ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> erroValidacao(MethodArgumentNotValidException ex) {
        Map<String, String> erros = new HashMap<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            erros.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        Map<String, Object> resposta = new HashMap<>();
        resposta.put("status", HttpStatus.BAD_REQUEST.value());
        resposta.put("erro", "Erro de validação");
        resposta.put("camposInvalidos", erros);
        return new ResponseEntity.Status(HttpStatus.BAD_REQUEST).body(resposta);
    }
}
