package com.lcwd.hibr;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.lcwd.hibr.Util.HibernateUtil;
import com.lcwd.hibr.entities.Certificate;
import com.lcwd.hibr.entities.Student;

public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");
		System.out.println("Hello this is my hibernate crash cource ORM");

		Student student = new Student();
		student.setName("Gaurav Muley");
		student.setCollege("Z.P.Highscholl wadhona ramnath");
		student.setFathername("Arun");
		student.setPhone("7058567055");
		student.setAbout("He is clever and intelligent person in the entire college");
		student.setActive(true);
		
		Certificate certificate = new Certificate();
		certificate.setTitle("Java Developer Backend");
		certificate.setAbout("This is Java Certification");
		certificate.setLink("Link");
		certificate.setStudent(student);
		
		Certificate certificate1 = new Certificate();
		certificate1.setTitle("Spring boot Developer Backend");
		certificate1.setAbout("This is Spring boot Certification");
		certificate1.setLink("Link");
		certificate1.setStudent(student);
		
		
		student.getCertificates().add(certificate);
		student.getCertificates().add(certificate1);

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		// System.out.println(sessionFactory);

		Session session = sessionFactory.openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			session.persist(student);
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