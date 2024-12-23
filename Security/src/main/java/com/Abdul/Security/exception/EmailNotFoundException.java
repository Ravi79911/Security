package com.Abdul.Security.exception;

import com.Abdul.Security.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;


@Getter
@Setter
public class EmailNotFoundException extends RuntimeException {

    String message;
    public EmailNotFoundException(String message) {
        super(String.format(message));
          this.message=message;
    }


    public String getMessage() {
        return message;
    }
}
