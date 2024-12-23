package com.Abdul.Security.exception;

import com.Abdul.Security.Dtos.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalEceptionHandler
{
        @ExceptionHandler(ResourceNotFoundException.class)
       public ResponseEntity<ApiResponse> resourceNotFoundException(ResourceNotFoundException ex)
       {
           String message = ex.getMessage();
           ApiResponse apiResponse = new ApiResponse(message, false);
           return  new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
       }

       @ExceptionHandler(EmailNotFoundException.class)
       public ResponseEntity<ApiResponse> responseResponseEntity( EmailNotFoundException em)
       {
           String message = em.getMessage();
           ApiResponse apiResponse = new ApiResponse(message, false);
           return  new ResponseEntity<>(apiResponse,HttpStatus.NOT_FOUND);
       }

}
