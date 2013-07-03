/**
 * 
 */
package com.claro.cobillingweb.persistence.dao.query;

/**
 * @author 92031709
 *
 */
public class SccArquivosNaoProcessadosDAONativeSQL {
	
	public static final String SQL = "SELECT " +
											 " RI.DT_RELATORIO AS dtRelatorio, "+
											 " RI.NO_ARQUIVO_REFERENCIA AS noArquivoRetorno, "+
											 " RI.QT_REGISTROS AS qtChamadas, "+
											 " RI.QT_DURACAO_TARIFADA AS qtMinutoTarifados, "+
											 " RI.VL_LIQUIDO AS vlLiquido, "+
											 " RI.DT_PROC_CLARO AS dtRecebimento "+											 
									 " FROM "+
											 " SCC_RELATORIO_SUMARIZADO RS, SCC_RELATORIO_ITEM RI "+
									 " WHERE  RI.DT_RELATORIO = RS.DT_RELATORIO "+
									   " AND  RI.SQ_RELATORIO_SUMARIZADO = RS.SQ_RELATORIO_SUMARIZADO "+
									   " AND  RS.DT_RELATORIO >= :dtInicial "+
									   " AND  RS.DT_RELATORIO <= :dtFinal "+
									   " AND  RS.SQ_RELATORIO = 1";									 
									 	
	public static final String FILTRO_NO_ARQUIVO_GERADO = " AND RI.NO_ARQUIVO_REFERENCIA = :noArquivoGerado ";
	
	public static final String SQL_GROUP = " ORDER BY RI.DT_RELATORIO, RI.DT_PROC_CLARO";
						
}
