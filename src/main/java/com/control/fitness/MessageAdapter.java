package com.control.fitness;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@Service
public class MessageAdapter {
	
	@Autowired
    private MessageSource messageSource;
	
	public String msg(String message){
		return messageSource.getMessage(message, null, LocaleContextHolder.getLocale());
	}
	
	public String msg(String message,Object[] params){
		return messageSource.getMessage(message, params, LocaleContextHolder.getLocale());
	}

}
