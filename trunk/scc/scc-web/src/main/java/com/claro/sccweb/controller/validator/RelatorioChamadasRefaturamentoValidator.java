/**
 * 
 */
package com.claro.sccweb.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.claro.sccweb.form.RelatorioChamadasRefaturamentoForm;

/**
 * @author 92031709
 *
 */
public class RelatorioChamadasRefaturamentoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {

		return clazz.equals(RelatorioChamadasRefaturamentoForm.class);
	}

	@Override
	public void validate(Object _form, Errors errors) {
		
		RelatorioChamadasRefaturamentoForm form = (RelatorioChamadasRefaturamentoForm)_form;
		if ((form.getOperacao() != null) && (form.getOperacao().equalsIgnoreCase("pesquisar")))
		{
			if ((form.getDtInicial() == null) || (form.getDtInicial().equals("")))
				errors.rejectValue("dtInicial", "campoObrigatorio", "Campo Obrigat�rio");
			if ((form.getDtFinal() == null) || (form.getDtFinal().equals("")))
				errors.rejectValue("dtFinal", "campoObrigatorio", "Campo Obrigat�rio");
		}
	}
}