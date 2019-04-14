package com.love2code.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.love2code.hibernate.entity.Student;

/**
 * @author KDavara
 *
 */
public class UpdateStudentDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// create a session
		Session session = factory.getCurrentSession();

		try {
			
			int studentId = 4;

			// start a transaction
			session.beginTransaction();
			
			//Retrieve student based on id: primary key
			System.out.println("Getting Student with Id "+ studentId);
			
			Student myStudent = session.get(Student.class, studentId);

			System.out.println("Updating Student with id "+studentId);
			myStudent.setFirstName("Scooby");
			
			// commit transaction	
			session.getTransaction().commit();
			
			//update the email Id
			session = factory.getCurrentSession();
			
			// start a transaction
			session.beginTransaction();
			
			session.createQuery("update Student s set s.email='scooby@love2code.com' where s.id="+ studentId).executeUpdate();

			// commit transaction	
			session.getTransaction().commit();

			System.out.println("Done.!");
		} finally {
			factory.close();
		}
		}
	}
