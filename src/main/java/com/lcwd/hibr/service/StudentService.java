package com.lcwd.hibr.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.hibernate.query.criteria.JpaCriteriaQuery;

import com.lcwd.hibr.Util.HibernateUtil;
import com.lcwd.hibr.entities.Student;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class StudentService {

	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	// svae method for save data into database
	public void saveStudent(Student student) {
		try (Session session = sessionFactory.openSession()) {
			Transaction beginTransaction = session.beginTransaction();
			beginTransaction.commit();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// get by id
	public Student getById(long studentId) {
		try (Session session = sessionFactory.openSession()) {
			Student student = session.get(Student.class, studentId);
			return student;

		} catch (Exception ex) {
			ex.printStackTrace();
			// by chance any error will occour in try block. then object we get null
			return null;
		}
	}

	// update API

	public Student updateStudent(long studentId, Student student) {
		try (Session session = sessionFactory.openSession()) {
			Transaction beginTransaction = session.beginTransaction();
			Student oldStudent = session.get(Student.class, studentId);

			if (oldStudent != null) {
				oldStudent.setName(student.getName());
				oldStudent.setFathername(student.getFathername());
				oldStudent.setCollege(student.getCollege());
				oldStudent.setStudentId(student.getStudentId());
				oldStudent = session.merge(oldStudent);
			}
			beginTransaction.commit();
			return oldStudent;
		}

	}

	// delete Student

	public void deleteStudent(long studentId) {
		try (Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			Student student = session.get(Student.class, studentId);
			if (student != null) {
				session.remove(student);
			}
			transaction.commit();
		}
	}

	// HQL [JPA] Hibernate query language only apply on hibernate--> native query .
	// database independent ahe
	// get all student using hql
	public List<Student> getAllStudentHQL() {
		try (Session session = sessionFactory.openSession()) {
			String getHQL = "FROM Student";
			Query<Student> query = session.createQuery(getHQL, Student.class);
			return query.list();

		}
	}

	// get student by name
	public Student getStduentByNameHQL(String name) {
		try (Session session = sessionFactory.openSession()) {
			String getbyNameHql = "FROM Student WHERE name = :stduentName";
			Query<Student> query = session.createQuery(getbyNameHql, Student.class);
			query.setParameter("studentName", name);
			return query.uniqueResult();
		}
	}

	// Criteria API
	// get all student of same college
	public List<Student> getStudentByCriteria(String college) {
		try (Session session = sessionFactory.openSession()) {
			HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Student> query = criteriaBuilder.createQuery(Student.class);
			Root<Student> root = query.from(Student.class);
			query.select(root).where(criteriaBuilder.equal(root.get("college"), college));
			Query<Student> query2 = session.createQuery(query);
			return query2.getResultList();
		}
	}
	
	//Pagination 
	public List<Student> getStudentWithPagination(int pageNo, int pageSize){
		try(Session session=sessionFactory.openSession()){
			String pagiQuery="FROM Student";
			Query<Student> query = session.createQuery(pagiQuery, Student.class);
			query.setFirstResult((pageNo-1)*pageSize);
			query.setMaxResults(pageSize);
			return query.list();
		}
	}
}
