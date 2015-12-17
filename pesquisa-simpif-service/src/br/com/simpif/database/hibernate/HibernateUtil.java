package br.com.simpif.database.hibernate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
	private static Logger logger = LogManager.getLogger(HibernateUtil.class);
	
	private static final SessionFactory sessionFactory = buildSessionFactory();
	  
    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
        	logger.info("Criação da SessionFactory com o hibernate.cfg.xml");
        	SessionFactory sessionFatory = new Configuration().configure().buildSessionFactory();
            return sessionFatory;
            
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
        	logger.error("Problema na abertura da conexão.");
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
  
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
  
    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }
}
