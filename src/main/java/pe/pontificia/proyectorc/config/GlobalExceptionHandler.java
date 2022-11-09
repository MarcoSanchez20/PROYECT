package pe.pontificia.proyectorc.config;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.*;

public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    public GlobalExceptionHandler(MessageSource messageSource){
        this.messageSource=messageSource;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<?> handleValidationException(MethodArgumentNotValidException exception){
        Map<String, Object> errorDetail=new HashMap<>();

        errorDetail.put("title","Error de validación");
        errorDetail.put("code","invalid_form");
        errorDetail.put("status", HttpStatus.UNPROCESSABLE_ENTITY.value());

        List<String> errors=new ArrayList<>();

        for (FieldError fieldError : exception.getBindingResult().getFieldErrors()){
            String message=messageSource.getMessage(fieldError, Locale.getDefault());
            errors.add(message);
        }

        return new ResponseEntity<>(errorDetail,HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
