package com.claro.sccweb.service;

import java.util.Date;
import java.util.List;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.view.SccExtracaoCDRsView;

public interface SccExtracaoCDRsService {
	
	
	List<SccExtracaoCDRsView> gerarRelatorioExtracaoCDRs(Date dtInicial, Date dtFinal, String nuMsisdnOrigem) throws ServiceException, DAOException;

}
