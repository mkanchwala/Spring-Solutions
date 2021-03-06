package com.jellybelly.user.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jellybelly.user.beans.User;

@Repository
public class UserDAO extends BaseHibernateDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	/**
	 * @param id
	 * @return User
	 */
	public User getById(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		User category = (User) session.get(User.class, id);
		return category;
	}

	/**
	 * Hibernate Query to return User's List by UserName
	 * 
	 * @return List<User>
	 */
	@SuppressWarnings("unchecked")
	public List<User> getUsers(String username, String email){
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(User.class);
		if(username != null){
			criteria.add(Restrictions.eq("username", username));
		}
		if(email != null){
			criteria.add(Restrictions.eq("email", email));
		}
		criteria.addOrder(Order.asc("username"));
		return criteria.list();
	}
}
