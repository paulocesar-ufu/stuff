package com.claro.cobillingweb.persistence.dao.internal;



import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.dao.BasicDAO;
import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.view.SccAssexuadosView;

public interface SccAssexuadosDAO extends BasicDAO<SccAssexuadosView> {
	
	List<SccAssexuadosView> gerarRelatorioAssexuados(Date dtInicial, Date dtFinal, String noArquivoGerado) throws DAOException;

}