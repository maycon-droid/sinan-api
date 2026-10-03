package br.edu.ifpb.sinan.exception;

import lombok.*;
import java.time.LocalDateTime;

@Getter
public class ErroResposta {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;

    public ErroResposta(int status, String error, String message) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.error = error;
        this.message = message;
    }
}