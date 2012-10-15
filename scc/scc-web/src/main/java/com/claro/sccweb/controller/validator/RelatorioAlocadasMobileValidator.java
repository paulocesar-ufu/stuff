/**
 * 
 */
package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.sccweb.form.RelatorioAlocadasMobileForm;

/**
 * @author 92031709
 *
 */
public class RelatorioAlocadasMobileValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {

		return clazz.equals(RelatorioAlocadasMobileForm.class);
	}

	@Override
	public void validate(Object _form, Errors errors) {
		
		RelatorioAlocadasMobileForm form = (RelatorioAlocadasMobileForm)_form;
		if ((form.getOperacao() != null) && (form.getOperacao().equalsIgnoreCase("pesquisar")))
		{
			if ((form.getDtInicial() == null) || (form.getDtInicial().equals("")))
				errors.rejectValue("dtInicial", "campoObrigatorio", "Campo Obrigatório");
			if ((form.getDtFinal() == null) || (form.getDtFinal().equals("")))
				errors.rejectValue("dtFinal", "campoObrigatorio", "Campo Obrigatório");
		}
	}
}