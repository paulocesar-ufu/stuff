/**
 * 
 */
package com.claro.sccweb.service;

import java.util.Collection;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.filtro.SccFinanceiroFiltro;
import com.claro.cobillingweb.persistence.view.SccFinanceiroView;

/**
 * @author 93046251
 *
 */
public interface SccFinanceiroService {
	
	Collection<SccFinanceiroView> findByFiltro(SccFinanceiroFiltro filtro)throws ServiceException, DAOException;
	

}