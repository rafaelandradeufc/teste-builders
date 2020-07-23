package br.com.plataform.builders.controller.exception.interceptor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.plataform.builders.controller.exception.ClientExistsException;
import br.com.plataform.builders.controller.exception.ClientNotFoundException;
import br.com.plataform.builders.model.CustomException;

@ControllerAdvice
public class InterceptorException extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ClientNotFoundException.class)
	public ResponseEntity<Object> handleClientNotFoundException(ClientNotFoundException ex) {
		CustomException responseException = new CustomException(ex.getMessage(), ex.getController(), ex.getPath());
		return new ResponseEntity<Object>(responseException, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ClientExistsException.class)
	public ResponseEntity<Object> handleClientExistsException(ClientExistsException ex) {
		CustomException responseException = new CustomException(ex.getMessage(), ex.getController(), ex.getPath());
		return new ResponseEntity<Object>(responseException, HttpStatus.CONFLICT);
	}

}
