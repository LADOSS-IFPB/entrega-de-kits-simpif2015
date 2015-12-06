package br.com.simpif.database;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import br.com.simpif.database.hibernate.HibernateUtil;
import br.com.simpif.entities.User;

public class UserDAO {
	
	public void update(User user){
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try{
			session.beginTransaction();
			session.merge(user);
			session.getTransaction().commit();
			
		}catch(HibernateException hexp){
			session.getTransaction().rollback();
			System.err.println(hexp.getLocalizedMessage());
		}finally{
			session.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<User> getAll(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<User> users = null;
		
		try{
			session.beginTransaction();
			Query query = session.getNamedQuery("User.getAll");
			users = query.list();
			session.getTransaction().commit();
			
		}catch(HibernateException hexp){
			session.getTransaction().rollback();
			System.err.println(hexp.getLocalizedMessage());
		}finally{
			session.close();
		}

		return users;
	}
	
	public void insert(User user) throws HibernateException{
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		try{
			session.beginTransaction();
			session.save(user);
			session.getTransaction().commit();
			
		}catch(HibernateException hexp){
			session.getTransaction().rollback();
			System.err.println(hexp.getLocalizedMessage());
			throw hexp;
		}finally{
			session.close();
		}
	}
	
//	public List<User> getNotDelivered(){
//		Session session = HibernateUtil.getSessionFactory().openSession();
//		session.beginTransaction();
//
//		Query query = session.createQuery("from User where isDelivered = :isDelivered");
//		query.setParameter("isDelivered", false);
//
//		@SuppressWarnings("unchecked")
//		List<User> list = (List<User>)query.list();
//		
//		session.getTransaction().commit();
//		session.close();
//
//		return list;
//	}
//	
//	public List<User> getDelivered(){
//		Session session = HibernateUtil.getSessionFactory().openSession();
//		session.beginTransaction();
//
//		Query query = session.createQuery("from User where isDelivered = :isDelivered");
//		query.setParameter("isDelivered", true);
//
//		@SuppressWarnings("unchecked")
//		List<User> list = (List<User>)query.list();
//		
//		session.getTransaction().commit();
//		session.close();
//
//		return list;
//	}
//	
}
