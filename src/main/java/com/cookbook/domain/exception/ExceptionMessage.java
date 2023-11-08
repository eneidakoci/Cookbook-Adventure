package com.cookbook.domain.exception;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
/*
Will be used in the advice package, inside the controller, to create custom exception messages
 */
@Getter
@Setter
public class ExceptionMessage {
    private LocalDateTime date = LocalDateTime.now();
    private Integer statusCode;
    private String path;
    private String message;

    public ExceptionMessage(Integer statusCode, String path, String message) {
        this.statusCode = statusCode;
        this.path = path;
        this.message = message;
    }
}
