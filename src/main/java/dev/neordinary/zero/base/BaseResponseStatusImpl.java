package dev.neordinary.zero.base;

import org.springframework.http.HttpStatus;

public interface BaseResponseStatusImpl {
    HttpStatus getStatus();
    String getCode();
    String getMessage();
}
