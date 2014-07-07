<%@ page session="true" isELIgnored="false"%>
<%@ taglib uri="/tags/ajaxtags" prefix="ajax" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="/tags/spring-form"%>
<%@ taglib prefix="spring" uri="/tags/spring"%>
<link rel="stylesheet" href="/scc/css/jquery-ui-1.8.18.custom.css" type="text/css"/>
<link rel="stylesheet" href="/scc/css/table_jui.css" type="text/css">
<%@ taglib uri="/tags/displaytag" prefix="display" %>

<script>
$(document).ready(function(){
	$('#tabs').tabs();
	$("#anoRepasse").mask("9999");	
	$('#cdEOTLD').change(selecionaLD);
	$('#cdTipoContrato').change(selecionaTipoContrato);
	$('#cdProdutoCobilling').change(selecionaProdutoPos);
	//$('#cdProdutoPrePago').change(selecionaProdutoPre);
	
	$('#pesquisar_button').click(function(){
		$('#operacao').val("pesquisar");
		$('#form1').submit();
	});

	$('#atualizar_button').click(function(){
		$('#operacao').val("atualizar_status");
		$('#form1').submit();
		
	})
	
	$('cdProdutoPrePago').hide();
		
});



function selecionaLD()
{
if ($("#cdTipoContrato").val() == "POS")
	{
	 $("#cdProdutoCobilling").empty().append('<option selected="selected" value="0">Carregando...</option>');
	 $("#periodo").empty().append('<option selected="selected" value="0">Selecione...</option>');
	 var sel = $("#cdEOTLD option:selected");
	 
	  $.ajax({   
		 url: "/scc/user/pagamento/relatorio/json/lista_produtos_pos/"+sel.val()+".scc",	 
		 dataType: "json",   success: function(data) 
		   	{     
			var name, select, option;        
		    select = document.getElementById('cdProdutoCobilling');      
		        select.options.length = 0;         
		        for (name in data) 
		           {       
		           if (data.hasOwnProperty(name)) {         
				select.options.add(new Option(data[name], name));  
		            }}}});    
	}
else
	{
	$("#cdProdutoPrepago").empty().append('<option selected="selected" value="0">Carregando...</option>');
	 $("#periodo").empty().append('<option selected="selected" value="0">Selecione...</option>');
	 var sel = $("#cdEOTLD option:selected");
	 
	  $.ajax({   
		 url: "/scc/user/pagamento/relatorio/json/lista_produtos_pre/"+sel.val()+".scc",	 
		 dataType: "json",   success: function(data) 
		   	{     
			var name, select, option;        
		    select = document.getElementById('cdProdutoPrepago');      
		        select.options.length = 0;         
		        for (name in data) 
		           {       
		           if (data.hasOwnProperty(name)) {         
				select.options.add(new Option(data[name], name));  
		            }}}});
	}
}

function selecionaProdutoPos()
{

var sel = $("#cdProdutoCobilling option:selected");
var eot = $("#cdEOTLD option:selected");	
$("#cdPeriodicidade").empty().append('<option selected="selected" value="0">Carregando...</option>');
$.ajax({   
	 url: "/scc/user/pagamento/relatorio/json/lista_periodos/"+sel.val()+"/"+eot.val()+".scc",	 
	 dataType: "json",   success: function(data) 
	   	{     
		var name, select, option;        
	    select = document.getElementById('cdPeriodicidade');      
	        select.options.length = 0;         
	        for (name in data) 
	           {       
	           if (data.hasOwnProperty(name)) {         
			select.options.add(new Option(data[name], name));  
	            }}}});

}

function selecionaTipoContrato()
{
var tpContrato = $("#cdTipoContrato").val();
if (tpContrato == 'PRE')
	{
	$('#cdProdutoCobilling').attr("disabled","true"); 
	$('#cdPeriodicidade').attr("disabled","true");
	$('cdProdutoCobilling').hide();
	$('cdProdutoPrePago').show();
	}
else
	{
	$('#cdProdutoCobilling').removeAttr('disabled');
	$('#cdPeriodicidade').removeAttr('disabled');
	$('cdProdutoCobilling').show();
	$('cdProdutoPrePago').hide();
	}
}

$('body').delegate('.update_chk', 'click', function(){
	
});

//$('body').delegate('.update_pgto_repasse', 'click', function(){
	
//	$('#atualizar_button').click(function(){
//		$('#operacao').val("atualizar_status");
//		$('#form1').submit();
//	});

	
//});

</script>

<div id="tabs">
<ul>
<li><a href="#tabs-1"><spring:message code="repasse_pos_consulta.filtro.titulo"/></a></li>
</ul>
<form:form modelAttribute="filtro" method="post" action="execute.scc" id="form1">
<form:hidden path="operacao" id="operacao"/>

<div id="tabs-1">
<table width="100%" border="0" cellspacing="0" cellpadding="0" >
<tr>
    <td width="10%"><spring:message code="pagamento_operadoraClaro"/>:</td>
    <td ><form:select path="cdEOT" id="cdEOT" items="${operadorasClaro}" itemLabel="dsOperadoraForCombos" itemValue="cdEot" />
    <form:errors path="cdEOT" /></td>
</tr>


<tr>
    <td width="10%"><spring:message code="pagamento_operadoraLD"/>:</td>
    <td ><form:select path="cdEOTLD" id="cdEOTLD" items="${operadorasExternas}" itemLabel="dsOperadoraForCombos" itemValue="cdEot" />
    <form:errors path="cdEOTLD" /></td>
</tr>
<tr>
	<td width="10%"><spring:message code="pagamento_produto"/>:</td>
    <td ><form:select path="cdProdutoCobilling" id="cdProdutoCobilling" items="${produtos}" itemLabel="noProdutoCobilling" itemValue="cdProdutoCobilling" />
   
    <form:errors path="cdProdutoCobilling" /></td>
</tr>


<tr>
	<td width="10%"><spring:message code="pagamento_tipoContrato"/>:</td>
    <td ><form:select path="cdTipoContrato" id="cdTipoContrato" items="${tiposContrato}" itemLabel="label" itemValue="key" />

</tr>


<tr>    
    <td width="10%"><spring:message code="pagamento_periodo"/>:</td>
    <td ><form:select path="cdPeriodicidade" id="cdPeriodicidade" items="${periodos}" itemLabel="noPeriodicidadeRepasse" itemValue="cdPeriodicidadeRepasse" />
    
</tr>
<tr>    
    <td width="10%"><spring:message code="pagamento_mes"/>:</td>
    <td ><form:select path="mesRepasse" id="mesRepasse" items="${meses}" itemLabel="label" itemValue="key" />
    <form:errors path="mesRepasse" /></td>
</tr>
<tr>
	<td width="10%"><spring:message code="pagamento_ano"/>:</td>
    <td ><form:input path="anoRepasse" id="anoRepasse" size="4" maxlength="4"/>
    <form:errors path="anoRepasse" /></td>
</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
    <td colspan="3" class="TdFormularioUp">&nbsp;</td>    
    <td colspan="1" align="right" class="TdFormularioUp" nowrap="nowrap">
    <input id="pesquisar_button" type="button" value=<spring:message code="comum.botao.pesquisar"/> />
    </td>
</tr>
</table>

<table>
<display:table  
	name="requestScope.filtro.listPagamentoSAPDecorator"
	id="repasses"  
	pagesize="10"  
	style="width:90%"
	requestURI="/scc/user/pagamento/relatorio/tab1.scc" 
	class="ui-state-default"
>
	<display:column property="operadoraClaro" title="Operadora Claro" />
	<display:column property="operadoraLD" title="Operadora LD" />
	<display:column property="nuRepasse" title="Repasse" />
	<display:column property="uf" title="UF" />
	<display:column property="vlrRepasse" title="Valor do Repasse" />
	<display:column property="dtRepasse" title="Dt Pagamento" />
	<display:column property="sqArquivoRemessaSap" title="Arq. Remessa SAP" />
	<display:column property="sqArquivoRetornoSap" title="Arq. Retorno SAP" />
	<display:column property="dtPagamentoSap" title="Dt. Pagamento SAP" />
	<display:column property="vlPagamentoSap" title="Vlr. Pagamento SAP" />
	<display:column>
		<input type="hidden" value="${repasses.nuRepasse}" />
	</display:column>
	
	<display:column title="lancar">				
		<input type="hidden" name="nuRepasse" value="${repasses.nuRepasse }"  />
	
		<input type="checkbox" name="lancadosSelecionados" value="${repasses.nuRepasse }" <c:if test="${repasses.lancadoChecked}"> checked="checked" </c:if> />
	</display:column>

</display:table>
<br>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
				    <td colspan="3" class="TdFormularioUp">&nbsp;</td>    
				    <td colspan="1" align="right" class="TdFormularioUp" nowrap="nowrap">
					    <input id="atualizar_button" type="button" class="update_pgto_repasse" value=<spring:message code="crud.botao.atualizar"/> />


			    	</td>
				</tr>
			</table>

</table>

</div>
</form:form>
</div>
