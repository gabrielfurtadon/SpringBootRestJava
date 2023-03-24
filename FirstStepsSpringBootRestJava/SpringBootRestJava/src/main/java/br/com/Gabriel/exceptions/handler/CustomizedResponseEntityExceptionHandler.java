package br.com.Gabriel.exceptions.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.Gabriel.exceptions.ExceptionResponse;
import br.com.Gabriel.exceptions.UnsuportedMathOperationException;

@ControllerAdvice // ANOTATION PARA SEMPRE QUE PRECISA CONCENTRAR ALGUM TRATAMENTO QUE ESTEJA ESPALHADO EM TODOS OS CONTROLLERS
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{

	//METODO PARA EXCEÇÕES MAIS GENERICAS (TIPO 500)
	@ExceptionHandler(Exception.class) // Exception.class = a mais generica
	public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	//METODO PARA EXCEÇÕES MAIS ESPECIFICA QUE CRIAMOS
	@ExceptionHandler(UnsuportedMathOperationException.class) 
	public final ResponseEntity<ExceptionResponse> handleBadRequestExceptions(Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	
}
