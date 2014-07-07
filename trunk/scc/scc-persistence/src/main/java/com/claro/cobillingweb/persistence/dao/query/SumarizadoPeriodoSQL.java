package com.claro.cobillingweb.persistence.dao.query;

public class SumarizadoPeriodoSQL {

	public static final String SQL = " SELECT "+
						" S.CD_STATUS_CDR,SUM(S.QT_CDRS),SUM(S.VL_LIQUIDO_CHAMADA),SUM(S.VL_BRUTO_CHAMADA),SUM(S.QT_DURACAO_REAL), "+ 
                        " SUM(S.QT_DURACAO_TARIFADA) , ST.DS_STATUS_CDR FROM SCC_ARQUIVO_SUMARIZADO S , SCC_STATUS_CDR ST WHERE TRUNC(S.DT_PROC_EXTERNA) BETWEEN :dataInicial AND :dataFinal AND "+
						" ST.CD_STATUS_CDR = S.CD_STATUS_CDR ";
	
	
	public static final String FILTRO_EOT_CLARO = " AND A.CD_EOT_CLARO = :cdEOTClaro ";
	
	public static final String FILTRO_EOT_CLARO_HOLDING = " AND A.CD_EOT_CLARO IN (SELECT CD_EOT FROM SCC_OPERADORA WHERE CD_EOT_HOLDING =  :cdEOTClaro) ";
	
	public static final String FILTRO_EOT_LD  = " AND A.CD_EOT_LD = :cdEOTLD ";
	
	public static final String FILTRO_DATA_INICIO  = " AND TRUNC(A.DT_PROC_EXTERNA) >= :dataInicial ";
	
	public static final String FILTRO_DATA_FIM  = " AND TRUNC(A.DT_PROC_EXTERNA) <= :dataFinal ";
	
	public static final String PROJECTIONS = "GROUP BY  S.CD_STATUS_CDR , ST.DS_STATUS_CDR "+
                         					" ORDER BY  S.CD_STATUS_CDR";
	
	public static final String SQL2 = "SELECT S.AA_CICLO AS AA_CICLO, "+
									  "       S.MM_CICLO AS MM_CICLO, "+
									  "       S.CD_STATUS_CDR AS CD_STATUS_CDR, "+
									  "       ST.DS_STATUS_CDR AS DS_STATUS_CDR, "+
									  "       SUM(S.QT_CDRS) AS QT_CDRS,  "+
									  "       SUM(S.VL_LIQUIDO_CHAMADA) AS VL_LIQUIDO_CHAMADA, "+
									  "       SUM(S.VL_BRUTO_CHAMADA) AS VL_BRUTO_CHAMADA,  "+
									  "       SUM(S.QT_DURACAO_REAL)AS QT_DURACAO_REAL ,  "+
									  "       SUM(S.QT_DURACAO_TARIFADA) AS QT_DURACAO_TARIFADA  "+ 
									  "FROM SCC_ARQUIVO_SUMARIZADO S,  "+
									  "SCC_ARQUIVO_COBILLING A , SCC_STATUS_CDR ST  "+
									  "WHERE A.CD_TIPO_ARQUIVO IN (5, 555) "+
									  "    AND S.SQ_ARQUIVO_ORIGINAL = A.SQ_ARQUIVO "+
									  "    AND S.CD_STATUS_CDR = ST.CD_STATUS_CDR ";
	
	public static final String SQL_ARRECADADO = " SELECT S.AA_CICLO AS AA_CICLO, " +
			" S.MM_CICLO AS MM_CICLO, " +
			" S.CD_STATUS_CDR AS CD_STATUS_CDR, " +
			" ST.DS_STATUS_CDR AS DS_STATUS_CDR, " +
			" SUM(S.QT_CDRS) AS QT_CDRS, " +
			" SUM(S.VL_LIQUIDO_CHAMADA) AS VL_LIQUIDO_CHAMADA, " +
			" SUM(S.VL_BRUTO_CHAMADA) AS VL_BRUTO_CHAMADA, " +
			" SUM(S.QT_DURACAO_REAL) AS QT_DURACAO_REAL, " +
			" SUM(S.QT_DURACAO_TARIFADA) AS QT_DURACAO_TARIFADA " +
			" FROM SCC_ARQUIVO_SUMARIZADO S, " +
			" SCC_ARQUIVO_COBILLING  A, " +
			" SCC_STATUS_CDR ST, " +
			" SCC_COMPOSICAO_PRODUTO CP, " +
			" SCC_PRODUTO_COBILLING PC " +
			" WHERE A.CD_TIPO_ARQUIVO IN (5, 555) " +
			" AND S.SQ_ARQUIVO_ORIGINAL = A.SQ_ARQUIVO " +
			" AND S.CD_STATUS_CDR = ST.CD_STATUS_CDR " +
			" AND CP.CD_COMPONENTE_PRODUTO = S.CD_COMPONENTE_PRODUTO " +
			" AND CP.CD_PRODUTO_COBILLING = PC.CD_PRODUTO_COBILLING " +
			" AND ((PC.CD_PRODUTO_COBILLING = :produto) " +
			" OR (CP.CD_COMPONENTE_PRODUTO IN (3,5,7,10,13,21,24))) ";
	
	public static final String SQL_DIF_ARRECADADO = " SELECT S.AA_CICLO AS AA_CICLO, " +
			" S.MM_CICLO AS MM_CICLO, " +
			" S.CD_STATUS_CDR AS CD_STATUS_CDR, " +
			" ST.DS_STATUS_CDR AS DS_STATUS_CDR, " +
			" SUM(S.QT_CDRS) AS QT_CDRS, " +
			" SUM(S.VL_LIQUIDO_CHAMADA) AS VL_LIQUIDO_CHAMADA, " +
			" SUM(S.VL_BRUTO_CHAMADA) AS VL_BRUTO_CHAMADA, " +
			" SUM(S.QT_DURACAO_REAL) AS QT_DURACAO_REAL, " +
			" SUM(S.QT_DURACAO_TARIFADA) AS QT_DURACAO_TARIFADA " +
			" FROM SCC_ARQUIVO_SUMARIZADO S, " +
			" SCC_ARQUIVO_COBILLING  A, " +
			" SCC_STATUS_CDR ST, " +
			" SCC_COMPOSICAO_PRODUTO CP, " +
			" SCC_PRODUTO_COBILLING PC " +
			" WHERE A.CD_TIPO_ARQUIVO IN (5, 555) " +
			" AND S.SQ_ARQUIVO_ORIGINAL = A.SQ_ARQUIVO " +
			" AND S.CD_STATUS_CDR = ST.CD_STATUS_CDR " +
			" AND CP.CD_COMPONENTE_PRODUTO = S.CD_COMPONENTE_PRODUTO " +
			" AND CP.CD_PRODUTO_COBILLING = PC.CD_PRODUTO_COBILLING " +
			" AND ((PC.CD_PRODUTO_COBILLING = :produto) " +
			" OR (CP.CD_COMPONENTE_PRODUTO NOT IN (3,5,7,10,13,21,24))) ";
	

//	public static final String SQL3 = "SELECT S.AA_CICLO AS AA_CICLO, "+
//			  "       S.MM_CICLO AS MM_CICLO, "+
//			  "       S.CD_STATUS_CDR AS CD_STATUS_CDR, "+
//			  "       ST.DS_STATUS_CDR AS DS_STATUS_CDR, "+
//			  "       SUM(S.QT_CDRS) AS QT_CDRS,  "+
//			  "       SUM(S.VL_LIQUIDO_CHAMADA) AS VL_LIQUIDO_CHAMADA, "+
//			  "       SUM(S.VL_BRUTO_CHAMADA) AS VL_BRUTO_CHAMADA,  "+
//			  "       SUM(S.QT_DURACAO_REAL)AS QT_DURACAO_REAL ,  "+
//			  "       SUM(S.QT_DURACAO_TARIFADA) AS QT_DURACAO_TARIFADA  "+ 
//			  "FROM SCC_ARQUIVO_SUMARIZADO S,  "+
//			  "SCC_ARQUIVO_COBILLING A , SCC_STATUS_CDR ST  "+
//			  "WHERE A.CD_TIPO_ARQUIVO IN (5, 555) "+
//		      "    AND s.CD_COMPONENTE_PRODUTO = cp.CD_COMPONENTE_PRODUTO "+
//		      "    AND cp.CD_PRODUTO_COBILLING =p.CD_PRODUTO_COBILLING "+
//		      "    AND cp.CD_PRODUTO_COBILLING =p.CD_PRODUTO_COBILLING "+
//		      "    AND ((p.CD_PRODUTO_COBILLING = :produto) "+
//		      "    OR (CP.CD_COMPONENTE_PRODUTO IN (3,5,7,10,13,21,24,82))) "+
//			  "    AND S.SQ_ARQUIVO_ORIGINAL = A.SQ_ARQUIVO "+
//			  "    AND S.CD_STATUS_CDR = ST.CD_STATUS_CDR ";
	
	public static final String SQL_AGRUPADO =	" SELECT CD_STATUS_CDR,  "+
			"		DS_STATUS_CDR,   "+
			"   	SUM(QT_CDRS),   "+
			"		SUM(VL_LIQUIDO_CHAMADA),   "+
			"		SUM(VL_BRUTO_CHAMADA),  "+
			"		SUM(QT_DURACAO_REAL),   "+
			"		SUM(QT_DURACAO_TARIFADA) "+
			"FROM( "+
			"		SELECT 	S.AA_CICLO AS AA_CICLO,  "+
			"				S.MM_CICLO AS MM_CICLO,  "+
			"   			S.CD_STATUS_CDR AS CD_STATUS_CDR, "+
			"				ST.DS_STATUS_CDR AS DS_STATUS_CDR, "+
			"               SUM(S.QT_CDRS) AS QT_CDRS,  "+
			"				SUM(S.VL_LIQUIDO_CHAMADA) AS VL_LIQUIDO_CHAMADA,  "+
			"				SUM(S.VL_BRUTO_CHAMADA) AS VL_BRUTO_CHAMADA,  "+
			"				SUM(S.QT_DURACAO_REAL)AS QT_DURACAO_REAL ,  "+
			"				SUM(S.QT_DURACAO_TARIFADA) AS QT_DURACAO_TARIFADA  "+
			"		FROM SCC_ARQUIVO_SUMARIZADO S, SCC_ARQUIVO_COBILLING A, SCC_STATUS_CDR ST "+
			"		WHERE A.CD_TIPO_ARQUIVO IN (5, 555) "+
			"				AND TRUNC(A.DT_PROC_EXTERNA) BETWEEN :dataInicial  AND  :dataFinal"+
			"				AND S.SQ_ARQUIVO_ORIGINAL = A.SQ_ARQUIVO "+
			"				AND S.CD_STATUS_CDR = ST.CD_STATUS_CDR ";

	
	public static final String SQL_ARRECADADO_AGRUPADO = " SELECT CD_STATUS_CDR, " +
			" DS_STATUS_CDR, " +
			" SUM(QT_CDRS), " +
			" SUM(VL_LIQUIDO_CHAMADA), " +
			" SUM(VL_BRUTO_CHAMADA), " +
			" SUM(QT_DURACAO_REAL), " +
			" SUM(QT_DURACAO_TARIFADA) " +
			" FROM (SELECT S.AA_CICLO AS AA_CICLO, " +
			" S.MM_CICLO AS MM_CICLO, " +
			" S.CD_STATUS_CDR AS CD_STATUS_CDR, " +
			" ST.DS_STATUS_CDR AS DS_STATUS_CDR, " +
			" SUM(S.QT_CDRS) AS QT_CDRS, " +
			" SUM(S.VL_LIQUIDO_CHAMADA) AS VL_LIQUIDO_CHAMADA, " +
			" SUM(S.VL_BRUTO_CHAMADA) AS VL_BRUTO_CHAMADA, " +
			" SUM(S.QT_DURACAO_REAL) AS QT_DURACAO_REAL, " +
			" SUM(S.QT_DURACAO_TARIFADA) AS QT_DURACAO_TARIFADA " +
			" FROM SCC_ARQUIVO_SUMARIZADO S, " +
			" SCC_ARQUIVO_COBILLING A, " +
			" SCC_STATUS_CDR ST, " +
			" SCC_COMPOSICAO_PRODUTO CP, " +
			" SCC_PRODUTO_COBILLING PC " +
			" WHERE A.CD_TIPO_ARQUIVO IN (5, 555) " +
			" AND TRUNC(A.DT_PROC_EXTERNA) BETWEEN :dataInicial  AND  :dataFinal"+
			" AND S.SQ_ARQUIVO_ORIGINAL = A.SQ_ARQUIVO " +
			" AND S.CD_STATUS_CDR = ST.CD_STATUS_CDR " +
			" AND CP.CD_COMPONENTE_PRODUTO = S.CD_COMPONENTE_PRODUTO " +
			" AND CP.CD_PRODUTO_COBILLING = PC.CD_PRODUTO_COBILLING " +
			" AND ((PC.CD_PRODUTO_COBILLING = :produto) " +
			" OR (CP.CD_COMPONENTE_PRODUTO IN (3,5,7,10,13,21,24))) ";
	

	public static final String SQL_DIF_ARRECADADO_AGRUPADO = " SELECT CD_STATUS_CDR, " +
			" DS_STATUS_CDR, " +
			" SUM(QT_CDRS), " +
			" SUM(VL_LIQUIDO_CHAMADA), " +
			" SUM(VL_BRUTO_CHAMADA), " +
			" SUM(QT_DURACAO_REAL), " +
			" SUM(QT_DURACAO_TARIFADA) " +
			" FROM (SELECT S.AA_CICLO AS AA_CICLO, " +
			" S.MM_CICLO AS MM_CICLO, " +
			" S.CD_STATUS_CDR AS CD_STATUS_CDR, " +
			" ST.DS_STATUS_CDR AS DS_STATUS_CDR, " +
			" SUM(S.QT_CDRS) AS QT_CDRS, " +
			" SUM(S.VL_LIQUIDO_CHAMADA) AS VL_LIQUIDO_CHAMADA, " +
			" SUM(S.VL_BRUTO_CHAMADA) AS VL_BRUTO_CHAMADA, " +
			" SUM(S.QT_DURACAO_REAL) AS QT_DURACAO_REAL, " +
			" SUM(S.QT_DURACAO_TARIFADA) AS QT_DURACAO_TARIFADA " +
			" FROM SCC_ARQUIVO_SUMARIZADO S, " +
			" SCC_ARQUIVO_COBILLING A, " +
			" SCC_STATUS_CDR ST, " +
			" SCC_COMPOSICAO_PRODUTO CP, " +
			" SCC_PRODUTO_COBILLING PC " +
			" WHERE A.CD_TIPO_ARQUIVO IN (5, 555) " +
			" AND TRUNC(A.DT_PROC_EXTERNA) BETWEEN :dataInicial  AND  :dataFinal"+
			" AND S.SQ_ARQUIVO_ORIGINAL = A.SQ_ARQUIVO " +
			" AND S.CD_STATUS_CDR = ST.CD_STATUS_CDR " +
			" AND CP.CD_COMPONENTE_PRODUTO = S.CD_COMPONENTE_PRODUTO " +
			" AND CP.CD_PRODUTO_COBILLING = PC.CD_PRODUTO_COBILLING " +
			" AND ((PC.CD_PRODUTO_COBILLING = :produto) " +
			" OR (CP.CD_COMPONENTE_PRODUTO NOT IN (3,5,7,10,13,21,24))) ";
	
			
	public static final String SQL_AGRUPADO2 =	" GROUP BY S.AA_CICLO, S.MM_CICLO, S.CD_STATUS_CDR, ST.DS_STATUS_CDR "+
			"		ORDER BY S.AA_CICLO, "+
			"           S.MM_CICLO, "+
			"           S.CD_STATUS_CDR) R "+
			"GROUP BY CD_STATUS_CDR, DS_STATUS_CDR "+
			"ORDER BY CD_STATUS_CDR ";

//	public static final String SQL_AGRUPADO_PROD =	" SELECT CD_STATUS_CDR,  "+
//			"		DS_STATUS_CDR,   "+
//			"   	SUM(QT_CDRS),   "+
//			"		SUM(VL_LIQUIDO_CHAMADA),   "+
//			"		SUM(VL_BRUTO_CHAMADA),  "+
//			"		SUM(QT_DURACAO_REAL),   "+
//			"		SUM(QT_DURACAO_TARIFADA) "+
//			"FROM( "+
//			"		SELECT 	S.AA_CICLO AS AA_CICLO,  "+
//			"				S.MM_CICLO AS MM_CICLO,  "+
//			"   			S.CD_STATUS_CDR AS CD_STATUS_CDR, "+
//			"				ST.DS_STATUS_CDR AS DS_STATUS_CDR, "+
//			"               SUM(S.QT_CDRS) AS QT_CDRS,  "+
//			"				SUM(S.VL_LIQUIDO_CHAMADA) AS VL_LIQUIDO_CHAMADA,  "+
//			"				SUM(S.VL_BRUTO_CHAMADA) AS VL_BRUTO_CHAMADA,  "+
//			"				SUM(S.QT_DURACAO_REAL)AS QT_DURACAO_REAL ,  "+
//			"				SUM(S.QT_DURACAO_TARIFADA) AS QT_DURACAO_TARIFADA  "+
//			"		FROM SCC_ARQUIVO_SUMARIZADO S, SCC_ARQUIVO_COBILLING A, SCC_STATUS_CDR ST "+
//			"            SCC_COMPOSICAO_PRODUTO cp , SCC_PRODUTO_COBILLING p "+
//			"		WHERE A.CD_TIPO_ARQUIVO IN (5, 555) "+
//			"				AND A.DT_PROC_EXTERNA BETWEEN :dataInicial  AND  :dataFinal "+
//		    "               AND s.CD_COMPONENTE_PRODUTO = cp.CD_COMPONENTE_PRODUTO "+
//		    "               AND cp.CD_PRODUTO_COBILLING =p.CD_PRODUTO_COBILLING "+
//		    "               AND cp.CD_PRODUTO_COBILLING =p.CD_PRODUTO_COBILLING "+
//		    "    			AND ((p.CD_PRODUTO_COBILLING = :produto ) "+
//		    "    			OR (CP.CD_COMPONENTE_PRODUTO IN (3,5,7,10,13,21,24,82))) "+
//		    "				AND S.SQ_ARQUIVO_ORIGINAL = A.SQ_ARQUIVO "+
//			"				AND S.CD_STATUS_CDR = ST.CD_STATUS_CDR ";
	
	
	public static final String PROJECTIONS2 = "GROUP BY S.AA_CICLO, S.MM_CICLO, S.CD_STATUS_CDR, ST.DS_STATUS_CDR ORDER BY S.AA_CICLO, S.MM_CICLO, S.CD_STATUS_CDR";
	public static final String ORDERBY = "  ORDER BY S.AA_CICLO, S.MM_CICLO, S.CD_STATUS_CDR ";


}