package com.lcwd.hibr.Util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
		private static SessionFactory sessionFactory;
		
		static {
			try {
				if(sessionFactory==null) {
					sessionFactory  = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
				}
			}catch(Exception ex) {
				throw new RuntimeException("Error is creating session factory:"+ex.getMessage());
			}
		}
		public static SessionFactory getSessionFactory(){
			return sessionFactory;
		}
}
