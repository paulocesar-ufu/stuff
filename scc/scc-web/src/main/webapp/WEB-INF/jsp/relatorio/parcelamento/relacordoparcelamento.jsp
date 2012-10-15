<%@ page session="true" isELIgnored="false"%>
<%@ taglib uri="/tags/ajaxtags" prefix="ajax"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="/tags/spring-form"%>
<%@ taglib prefix="spring" uri="/tags/spring"%>
<link rel="stylesheet" href="/scc/css/jquery-ui-1.8.18.custom.css"
	type="text/css" />
<link rel="stylesheet" href="/scc/css/table_jui.css" type="text/css">
<%@ taglib uri="/tags/displaytag" prefix="display"%>

<script>
	$(document).ready(function(){	
		$("#dataInicial").mask("99/99/9999");	
		$("#dataFinal").mask("99/99/9999");
		$( "#dataInicial" ).datepicker();
		$( "#dataFinal" ).datepicker();		
		$('#pesquisar_button').click(pesquisar)
		$('#excel_button').click(excel)
		$("#tipoRelatorio").change(trocaTipoRelatorio); 
		$('#tabs').tabs();
	});

	$(document).ready(function() {

		$("#tipoRelatorio").change(trocaTipoRelatorio);
		$('#pesquisar_button').click(pesquisar);
		$('#excel_button').click(excel);
		$('#excel_button').attr('disabled', 'disabled');
		$('#tabs').tabs();
	});

	function pesquisar() {
		$('#pesquisar_button').attr('disabled', 'disabled');
		$('#excel_button').attr('disabled', 'disabled');
		$('#operacao').val("pesquisar");
		$('#form1').submit();
	}

	function excel() {
		$('#operacao').val("excel");
		$('#form1').submit();
	}

	function desaativarNumeroContaAcordo(){
		$("#conta").attr("disabled",true); 
		$("#acordo").attr("disabled",true); 
		
	}

	function ativarNumeroContaAcordo(){
		$("#conta").removeAttr("disabled"); 
		$("#acordo").removeAttr("disabled"); 
	}

	
	function trocaTipoRelatorio() {
		var sel = $("#tipoRelatorio option:selected");
		if (sel.val() == "S" || (sel.val() =="A"))
			desaativarNumeroContaAcordo();
		else
			ativarNumeroContaAcordo();
	}

	function ativarDesativarNumFatura(){
		if (  document.forms[0].tipoConsulta[0].checked  ){
			document.forms[0].numFatura.disabled = true;
			document.forms[0].numFatura.value = '';
		}else{
			document.forms[0].numFatura.disabled = false;
		}
	}						


	function mostraHoldingClaro() {
		$.ajax({
			url : "/scc/user/recepcao_transmissao/holding/json.scc",
			dataType : "json",
			success : function(data) {
				var name, select, option;
				select = document.getElementById('cdEOTClaro');
				select.options.length = 0;
				for (name in data) {
					if (data.hasOwnProperty(name)) {
						select.options.add(new Option(data[name], name));
					}
				}
			}
		});
	}

	function mostraOperadoraClaro() {
		$.ajax({
			url : "/scc/user/recepcao_transmissao/operadoras/json.scc",
			dataType : "json",
			success : function(data) {
				var name, select, option;
				select = document.getElementById('cdEOTClaro');
				select.options.length = 0;
				for (name in data) {
					if (data.hasOwnProperty(name)) {
						select.options.add(new Option(data[name], name));
					}
				}
			}
		});
	}
</script>

<div id="tabs">
	<ul>
		<li><a href="#tabs-1"><spring:message code="crud.titulo.pesquisar" /></a></li>
	</ul>
	<form:form modelAttribute="filtro" method="post" action="/scc/user/relatorio/parcelamento/acordo/execute.scc" id="form1">
		<form:hidden path="operacao" id="operacao" />
		<form:hidden path="itemSelecionado" id="itemSelecionado" />

		
		<div id="tabs-1">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" id="filtroPesquisaTable">
			
				<tr>
					<td width="50%"><spring:message code="relatorio.parcelamento.acordo.label.tipo.relatorio" /></td>
					<td><form:select path="tipoRelatorio" id="tipoRelatorio" items="${tipoRelatorio}" itemLabel="label" itemValue="key" /></td>
				</tr>
			

				<tr>
					<td width="50%"><spring:message code="relatorio.parcelamento.acordo.label.operadorald" /></td>
					<td id="cdEOTLD"><form:select path="operadoraLd" id="cdEOTLD" items="${operadorasExternas}" itemLabel="dsOperadora" itemValue="cdCsp" /></td>
				</tr>
				
				<tr>
					<td width="50%"><spring:message code="relatorio.parcelamento.acordo.label.operadora.claro" /></td>
					<td id="comboOperadoraClaro"><form:select path="operadoraClaro" id="cdEOTClaro" items="${operadorasClaro}" itemLabel="dsOperadora" itemValue="cdEot" /></td>
				</tr>

				<tr>
    				<td width="50%"><spring:message code="relatorio.parcelamento.acordo.label.status"/></td>
    				<td ><form:select path="status" items="${status}" itemLabel="label" itemValue="key" /></td>
				</tr>
				
				<tr>
					<td width="50%"><spring:message code="relatorio.parcelamento.acordo.label.numero.conta"/></td>
					<td id="numeroconta"><form:input path="numeroConta" id="conta" disabled="true"/>
				</tr>			
				
				<tr>
					<td width="50%"><spring:message code="relatorio.parcelamento.acordo.label.numero.acordo"/></td>
					<td id="numeroAcordo"><form:input path="numeroAcordo" id="acordo" disabled="true"/>
				</tr>			
				
				
				<tr>
    				<td width="50%"><spring:message code="relatorio.parcelamento.acordo.label.datainicial"/></td>
    				<td><form:input id="dataInicial" path="dataInicialPeriodo" />
    				<form:errors path="dataInicialPeriodo" /></td>
				</tr>

				<tr>
    				<td width="50%"><spring:message code="relatorio.parcelamento.acordo.label.datafinal"/></td>
    				<td>
    					<form:input id="dataFinal" path="dataFinalPeriodo" />
    					<form:errors path="dataFinalPeriodo" />
    				</td>
				</tr>
			</table>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td colspan="3" class="TdFormularioUp">&nbsp;</td>
					<td colspan="1" align="right" class="TdFormularioUp" nowrap="nowrap">
						<input id="pesquisar_button" type="button" value=<spring:message code="comum.botao.pesquisar"/> />
						<input id="excel_button" type="button" value=<spring:message code="comum.botao.excel"/> />
					</td>
				</tr>
			</table>
			<br />
			<c:choose>
				<c:when test="${form1.tipoRelatorio eq 'S'}">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
								<display:table style="width:90%" name="sessionScope._DISPLAY_TAG_SPACE_1" pagesize="20" id="repasses" requestURI="/scc/user/relatorio/parcelamento/acordo/tab1.scc" class="ui-state-default">
									<display:column property="operadoraLd" title="Operadora LD"/>
									<display:column property="operadoraClaro" title="Operadora Claro"/>
									<display:column property="dataAcordo" title="Data de Acordo"/>
									<display:column property="status" title="Status"/>
									<display:column property="qtdParcelas" title="Quantidade de Parcelas"/>
									<display:column property="valorAcordado" title="Valor total do Acordo"/>
									<display:column property="vlParcelaOperadora" title="Valor Operadora LD" style="text-align:right"/>
								</display:table>
							</td>
						</tr>
					</table>				
				</c:when>
				<c:otherwise>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
								<display:table style="width:90%" name="sessionScope._DISPLAY_TAG_SPACE_1" pagesize="20" id="repasses" requestURI="/scc/user/relatorio/parcelamento/acordo/tab1.scc" class="ui-state-default">
											<display:column property="operadoraLd" title="Operadora LD"/>
											<display:column property="operadoraClaro" title="Operadora Claro"/>
											<display:column property="dataAcordo" title="Data de Acordo"/>
											<display:column property="numConta" title="Numero da Conta"/>
											<display:column property="numAcordoParcelamento" title="Acordo"/>
											<display:column property="status" title="Status"/>
											<display:column property="numFatura" title="Numero da Fatura" style="text-align:right"/>
											<display:column property="valorAcordado" title="Valor total Acordado"/>
											<display:column property="qtdParcelas" title="Quantidade de Parcelas" style="text-align:right"/>
											
								</display:table>
							</td>
						</tr>
					</table>
				
				</c:otherwise>
			</c:choose>
		</div>
	</form:form>
</div>
<script>
	$(document).ready(function(){
		$('#pesquisar_button').removeAttr('disabled');
		$('#excel_button').removeAttr('disabled');
	});
</script>
