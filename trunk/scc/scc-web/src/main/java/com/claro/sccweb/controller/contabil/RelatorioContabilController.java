package com.claro.sccweb.controller.contabil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.entity.SccMotivoRejeicao;
import com.claro.cobillingweb.persistence.entity.SccOperadora;
import com.claro.cobillingweb.persistence.view.RelContabilView;
import com.claro.sccweb.controller.BaseOperationController;
import com.claro.sccweb.controller.util.BasicIntegerItem;
import com.claro.sccweb.controller.util.BasicStringItem;
import com.claro.sccweb.controller.validator.RelatorioContabilValidator;
import com.claro.sccweb.decorator.rownum.entity.RelContabilViewDecorator;
import com.claro.sccweb.form.BaseForm;
import com.claro.sccweb.form.RelatorioContabilForm;

@Controller
@RequestMapping(value="/user/contabil/relatorio")
public class RelatorioContabilController extends BaseOperationController<RelatorioContabilForm>{

	private final RelatorioContabilValidator validator = new RelatorioContabilValidator();
	
	public ModelAndView gerar(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute(FORM_NAME)  BaseForm _form,BindingResult bindingResult,Model model) throws Exception
	{
		ModelAndView mav = new ModelAndView(getViewName());
		RelatorioContabilForm form = (RelatorioContabilForm)_form;
		Date dataInicial = calculaDataInicialPeriodo(form.getMes(), form.getAno());
		Date dataFinal = calculaDataFinalPeriodo(form.getMes(), form.getAno());
		List<RelContabilView> rows = getServiceManager().getRepassePosService().geraRelatorioContabil(form.getCdEOTLD(), form.getCdEOTClaro(), form.getCdMotivoRejeicao(), dataInicial, dataFinal, form.getTipoOperadora().equals("H"));
		List<RelContabilViewDecorator> decoratorList = new ArrayList<RelContabilViewDecorator>(rows.size());
		for (int i=0;i<rows.size();i++)
			{
			RelContabilViewDecorator decorator = new RelContabilViewDecorator(rows.get(i), i);
			decoratorList.add(decorator);
			}
		cacheMyForm(getClass(), form);
		mav.addObject(FORM_NAME, form);
		storeInSession(getClass(), DISPLAY_TAG_SPACE_1, decoratorList, request);
		return mav;
	}
	
	public ModelAndView excel(HttpServletRequest request, HttpServletResponse response,@Valid @ModelAttribute(FORM_NAME)  BaseForm _form,BindingResult bindingResult,Model model) throws Exception
	{
		ModelAndView mav = new ModelAndView("relatorio_contabil_filtro_excel");
		return mav;
	}
	
	 
	protected String getViewName() {
		return "relatorio_contabil_filtro";
	}

	 
	protected RelatorioContabilForm getForm() {
		return new RelatorioContabilForm();
	}

	 
	protected Validator getValidator() {		
		return this.validator;
	}
	
	@ModelAttribute("operadorasClaro")
	public List<SccOperadora> populaOperadorasClaro() throws Exception {
		List<SccOperadora> comboList = new ArrayList<SccOperadora>();
		SccOperadora allValues = new SccOperadora();
		allValues.setCdEot(BasicDAO.GET_ALL_STRING);
		allValues.setDsOperadora("Todas");
		comboList.add(0,allValues);
		comboList.addAll(getServiceManager().getPesquisaDominiosService().pequisaOperadorasClaroComM());
		return comboList;
	}
	/*
	@ModelAttribute("operadorasClaro")
	public List<SccOperadora> populaOperadoras() throws Exception
	{
		return super.populaOperadorasClaro(false);
	}
	*/
	
	@ModelAttribute("operadorasExternas")
	public List<SccOperadora> populaOperadorasExternas() throws Exception {
		List<SccOperadora> comboList = new ArrayList<SccOperadora>();
		SccOperadora allValues = new SccOperadora();
		allValues.setCdEot(BasicDAO.GET_ALL_STRING);
		allValues.setDsOperadora("Todas");
		comboList.add(0,allValues);
		comboList.addAll(getServiceManager().getPesquisaDominiosService().pesquisaOperadorasExternas());
		return comboList;
	}
	/*
	@ModelAttribute("operadorasExternas")
	public List<SccOperadora> populaOperadorasExternas() throws Exception
	{
		return super.populaOperadorasExternas(false);
	}
	*/
	
	@ModelAttribute("motivos")	
	public  List<BasicStringItem> populaMotivos() throws Exception
	{
		List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
		List<SccMotivoRejeicao> rows = getServiceManager().getPesquisaDominiosService().getAllMotivosRejeicao();
		for (SccMotivoRejeicao row : rows) {
			comboList.add(new BasicStringItem(row.getCdMotivoRejeicao(), row.getCdMotivoRejeicao()+" "+row.getDsMotivoRejeicao()));
		}
		return comboList;
	}
	
	@ModelAttribute("meses")
	public List<BasicIntegerItem> populaMeses() 
	{
		return _populaComboMeses();
	}
	
	@ModelAttribute("tiposOperadora")
	public List<BasicStringItem> populaTiposOperadoras() throws Exception
	{
		List<BasicStringItem> comboList = new ArrayList<BasicStringItem>();
		comboList.add(new BasicStringItem("O", "Operadora"));
		comboList.add(new BasicStringItem("H", "Holding"));
		return comboList;
	}
}