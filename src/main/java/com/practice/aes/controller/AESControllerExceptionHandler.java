package com.practice.aes.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.practice.aes.domain.model.Result;

@ControllerAdvice
public class AESControllerExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(AESController.class);
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		logger.debug("ExceptionHandler initialized");
	}

    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
	@ExceptionHandler(Exception.class)
    @ResponseBody
	public Result exception(Exception e) {
		logger.error(e.getMessage());
		return new Result("-1", "error");
	}

	@ModelAttribute
	public void modelAttribute() {
		logger.debug("ExceptionHandler model attributed");
	}
}
