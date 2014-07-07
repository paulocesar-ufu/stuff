package com.claro.cobillingweb.persistence.dao.internal.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import com.claro.cobillingweb.persistence.dao.DAOException;
import com.claro.cobillingweb.persistence.dao.impl.HibernateBasicDAOImpl;
import com.claro.cobillingweb.persistence.dao.internal.SccStatusArquivoDAO;
import com.claro.cobillingweb.persistence.entity.SccStatusArquivo;

public class SccStatusArquivoDAOImpl extends HibernateBasicDAOImpl<SccStatusArquivo> implements  SccStatusArquivoDAO  {

	 
	@SuppressWarnings({ "unused", "unchecked" })
	public List<SccStatusArquivo> getAll() throws DAOException {		
		try {
			List<SccStatusArquivo> resultList = new ArrayList<SccStatusArquivo>();			
			Query query = getSessionFactory().getCurrentSession().getNamedQuery("SccStatusArquivo.GET_ALL");			
			return query.list();		
		} catch (Exception e){
				throw new DAOException(e.getMessage(),"SccStatusArquivo.GET_ALL");
		}
	}
	
	
	
	@SuppressWarnings("unchecked")
	public List<SccStatusArquivo> findByStatus() throws DAOException {		
		try {
			String[] value ={ "OK","NO","RP"};
					
			Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(SccStatusArquivo.class);
			criteria.add(Restrictions.in("cdStatusArquivo",value ));
			return criteria.list();
		} catch (Exception e){
				throw new DAOException(e.getMessage(),"SccStatusArquivo.GET_ALL");
				}
		}
	}

