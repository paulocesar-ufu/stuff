package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.sccweb.form.ComposicaoProdutoForm;

public class ComposicaoProdutoValidator implements Validator {

	 
	public boolean supports(Class<?> clazz) {
		return clazz.equals(ComposicaoProdutoForm.class);
	}

	 
	public void validate(Object arg, Errors errors) {
		
	}

	
	
}