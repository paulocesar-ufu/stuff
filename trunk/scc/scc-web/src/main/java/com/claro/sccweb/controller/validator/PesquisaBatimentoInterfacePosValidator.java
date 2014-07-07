package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.sccweb.controller.validator.util.ValidatorUtil;
import com.claro.sccweb.form.PesquisaBatimentoInterfacePosForm;

public class PesquisaBatimentoInterfacePosValidator implements Validator {

	 
	public boolean supports(Class<?> clazz) {
		return clazz.equals(PesquisaBatimentoInterfacePosForm.class);
	}

	 
	public void validate(Object arg, Errors errors) {
		PesquisaBatimentoInterfacePosForm form = (PesquisaBatimentoInterfacePosForm)arg;
		if ((form.getOperacao() != null) && (form.getOperacao().equalsIgnoreCase("pesquisar")))
		{
			ValidatorUtil.verificaCampoObrigatorio("dataInicial", form.getDataInicial(), errors);
			ValidatorUtil.verificaCampoObrigatorio("dataFinal", form.getDataFinal(), errors);
			ValidatorUtil.verificaPeriodo("dataFinal", form.getDataInicial(), form.getDataFinal(), errors);	
		}
	}

	
	
}