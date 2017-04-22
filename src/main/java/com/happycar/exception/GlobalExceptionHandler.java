package com.happycar.exception;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	private Logger logger = Logger.getLogger(GlobalExceptionHandler.class);
	@ExceptionHandler(RuntimeException.class)
	public void exceptionHandler(RuntimeException e, HttpServletResponse response) {
		logger.error("系统发生异常",e);
	}
}