package com.jellybelly.user.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jellybelly.user.beans.BaseBean;

/**
 * @author mkanchwala
 * 
 */
@Repository
@Transactional
public class BaseHibernateDAO {

	@Autowired
	private SessionFactory sessionFactory;

	private Logger logger = Logger.getLogger(BaseHibernateDAO.class);

	public void insert(BaseBean baseBean) {
		Session session = sessionFactory.getCurrentSession();
		session.save(baseBean);
		logger.debug(baseBean.getClass());
	}

	public void delete(BaseBean baseBean) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(baseBean);
		logger.debug(baseBean.getClass());
	}
	
	public void update(BaseBean baseBean) {
		Session session = sessionFactory.getCurrentSession();
		session.update(baseBean);
		logger.debug(baseBean.getClass());
	}
}