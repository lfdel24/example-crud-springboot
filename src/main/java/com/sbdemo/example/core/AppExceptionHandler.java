package com.sbdemo.example.core;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author leo Convierte las exepciones java a errores http
 */
@ControllerAdvice
public class AppExceptionHandler extends RuntimeException {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({Exception.class})
    @ResponseBody
    public ErrorMessage internalServerError(
            Exception e) {
        return new ErrorMessage(e, HttpStatus.INTERNAL_SERVER_ERROR);

    }

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    @ResponseBody
//    public ErrorMessage validationException(
//            MethodArgumentNotValidException e) {
//        Map<String, String> errors = new HashMap<>();
//        e.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);
//        });
//        System.out.println("alndandkjansdkjan llego aca");
//        return new ErrorMessage(e, HttpStatus.BAD_REQUEST + " " + errors.toString(), HttpStatus.BAD_REQUEST.value());
//
//    }
}
