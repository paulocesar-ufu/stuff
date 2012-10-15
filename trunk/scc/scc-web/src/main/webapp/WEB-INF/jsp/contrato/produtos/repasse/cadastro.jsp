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
	$('#atualizar_button').hide();	
	$('#novo_button').click(novo);	
	$('#pesquisar_button').click(pesquisar);
	$('#salvar_button').click(salvar);
	$('#voltar_button').click(voltar);	
	$('#atualizar_button').click(atualizar);	
	$('#tabs').tabs();
});


function voltar()
{
	$("#form1").attr("action", "/scc/user/contrato/produtos/back.scc");	
	$('#form1').submit();
}

	
function salvar()
{
	var r=confirm("Salvar?");
	if (r==true)
		{		
		$('#operacao').val("CRUD.salvar");	
		$('#form1').submit();	
		}
}

function atualizar()
{
	var r=confirm("Atualizar Registro?");
	if (r==true)
		{		
		$('#operacao').val("CRUD.atualizar");	
		$('#form1').submit();	
		}
}

function remover(linha)
{
	var r=confirm("Remover?");
	if (r==true)
		{
		$('#itemSelecionado').val(linha);	
		$('#operacao').val("CRUD.remover_tabela");	
		$('#form1').submit();	
		}	
}


function editar(linha)
{
	$('#itemSelecionado').val(linha);	
	$('#operacao').val("CRUD.editar");	
	$('#salvar_button').hide();
	$('#atualizar_button').show();
	$('#form1').submit();
}

function novo()
{			
	$('#operacao').val("novo");
	$('#form1').submit();		
}

function pesquisar()
{
	$('#operacao').val("CRUD.pesquisar");	
	$('#form1').submit();
}

</script>





<div id="tabs">
<ul>
<li><a href="#tabs-1"><spring:message code="crud.titulo.pesquisar"/></a></li>
<li><a href="#tabs-2"><spring:message code="crud.titulo.editar"/></a></li>
</ul>
<form:form modelAttribute="filtro" method="post" action="/scc/user/contrato/produtos/repasse/execute.scc" id="form1">
<form:hidden path="operacao" id="operacao"/>
<form:hidden path="itemSelecionado" id="itemSelecionado"/>
<form:hidden path="entity.cdConfigRepasseCobranca" id="cdConfigRepasseCobranca"/>
<form:hidden path="entity.dtCriacao" id="dtCriacao"/>
<form:hidden path="entity.sccProdutoContratado.sccContratoCobl.cdContratoCobilling" />
<form:hidden path="entity.sccProdutoContratado.sccProdutoCobilling.cdProdutoCobilling" />
<div id="tabs-1">
<table width="100%" border="0" cellspacing="0" cellpadding="0" >
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
</table>
<br/>
<table  width="100%" border="0" cellspacing="0" cellpadding="0" >
 <tr><td>                            
<display:table style="width:90%"  name="sessionScope._DISPLAY_TAG_SPACE_1"   pagesize="20"  id="repasses" requestURI="/scc/user/contrato/produtos/repasse/tab1.scc" class="ui-state-default">
<display:column property="contrato" title="Contrato" />
<display:column property="produto" title="Produto" />
<display:column property="itemProduto" title="Item Produto" />
<display:column property="CDRRepasse" title="CDR Repasse" />
<display:column property="CDRCobranca" title="CDR Cobran�a" />
<display:column property="formaCobranca" title="Forma Cobran�a" />
<display:column property="formaPagamento" title="Forma Pagamento" />
<display:column property="formaRepasse" title="FormaRepasse" />
<display:column property="prestacaoServicos" title="Prestacao de Servi�os" />
<display:column property="calculoCobilling" title="C�lculo Cobilling" />
<display:column property="calculoRepasse" title="C�lculo Repasse" />
<display:column property="editar" title="Editar" />
<display:column property="remover" title="Remover" />
</display:table>
</td></tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
    <td colspan="3" class="TdFormularioUp">&nbsp;</td>    
    <td colspan="1" align="right" class="TdFormularioUp" nowrap="nowrap">
    <input id="novo_button" type="button" value=<spring:message code="crud.botao.novo"/> />    
    <input id="voltar_button" type="button" value=<spring:message code="comum.botao.voltar"/> />
    </td>
</tr>
</table>
</div>
<div id="tabs-2" style="height: 500px;">
<table width="100%" border="0" cellspacing="0" cellpadding="0" >

<tr>
<td width="15%">Contrato:</td>
<td>
<form:input path="entity.sccProdutoContratado.sccContratoCobl.dsContratoCobilling" readonly="true"/>

</td></tr>

<tr><td width="15%">Produto:</td>
<td>
<form:input path="entity.sccProdutoContratado.sccProdutoCobilling.noProdutoCobilling" readonly="true"/>
</td></tr>

<tr><td width="15%">Item Produto:</td>
<td>
<form:select path="entity.sccComposicaoProduto.sccItemCobilling.cdItemCobilling" items="${itemsCobilling}" itemLabel="noItemCobilling" itemValue="cdItemCobilling"/>
</td></tr>


<tr><td width="15%">Status CDR Repasse:</td>
<td>
<form:select path="entity.statusCdrRepasse.cdStatusCdr" items="${cdrsRepasse}" itemLabel="label" itemValue="key"/>
</td></tr>


<tr><td width="15%">Status CDR Cobran�a:</td>
<td>
<form:select path="entity.statusCdrCobranca.cdStatusCdr" items="${cdrsCobranca}" itemLabel="label" itemValue="key"/>
</td></tr>


<tr><td width="15%">Forma de Cobran�a:</td>
<td>
<form:select path="entity.inFormaCobrancaCobilling" items="${formasCobranca}" itemLabel="label" itemValue="key"/>
</td></tr>


<tr><td width="15%">Forma de Pagamento:</td>
<td>
<form:select path="entity.inFormaPagamento" items="${formasPagamento}" itemLabel="label" itemValue="key"/>
</td></tr>

<tr><td width="15%">Forma Repasse: </td>
<td>
<form:select path="entity.inFormaRepasse" items="${formasRepasse}" itemLabel="label" itemValue="key"/>
</td></tr>

<tr><td width="15%">Cobran�a de Presta��o de Servi�os:</td>
<td>
<form:checkbox path="entity.fgCobrarPrestacaoServico" value="S" label="Sim" id="fgCobrarPrestacaoServico"/>
<form:checkbox path="entity.fgCobrarPrestacaoServico" value="N" label="N�o" id="fgCobrarPrestacaoServico"/>
</td></tr>

<tr><td width="15%">Calculo Cobilling:</td>
<td>
<form:input path="entity.vlCalculoCobilling" size="14" maxlength="7"/>
</td></tr>

<tr><td width="15%">Calculo Repasse:</td>
<td>
<form:input path="entity.vlCalculoRepasse" size="14" maxlength="7"/>
</td></tr>

</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
    <td colspan="3" class="TdFormularioUp">&nbsp;</td>    
    <td colspan="1" align="right" class="TdFormularioUp" nowrap="nowrap">
    <input id="atualizar_button" type="button" value=<spring:message code="crud.botao.atualizar"/> />    
    <input id="salvar_button" type="button" value=<spring:message code="crud.botao.salvar"/> />
    </td>
</tr>
</table>
</div>
</form:form>
</div>
<script>
$(function() {
	var op = $('#operacao').val();
	if (op == 'CRUD.editar'){
		$('#tabs').tabs('select',1);
		$('#salvar_button').hide();
		$('#atualizar_button').show();
	}
	else if (op == 'novo'){
		$('#tabs').tabs('select',1);
		$('#atualizar_button').hide();
		$('#salvar_button').show();
	}
	else{
		$('#tabs').tabs('select',0);
	}
});
</script>