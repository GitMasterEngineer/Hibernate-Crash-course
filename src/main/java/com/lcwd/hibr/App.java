package com.lcwd.hibr;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.lcwd.hibr.Util.HibernateUtil;
import com.lcwd.hibr.entities.Student;

public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");
		System.out.println("Hello this is my hibernate crash cource ORM");

		Student student = new Student();
		student.setName("Kailas Wadvale");
		student.setCollege("Z.P.Highscholl wadhona ramnath");
		student.setFathername("Haridas");
		student.setPhone("9545629084");
		student.setAbout("He is clever and intelligent person in the entire college");
		student.setActive(true);

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		// System.out.println(sessionFactory);

		Session session = sessionFactory.openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			//session.persist(student);
			transaction.commit();
			System.out.println("Student save Successfully");

		} catch (Exception ex) {
			if(transaction!=null)
			{
				transaction.rollback();
			}
			ex.printStackTrace();

		}finally {
			session.close();
		}
	}
}