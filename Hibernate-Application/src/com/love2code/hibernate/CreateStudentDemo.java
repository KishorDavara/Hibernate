/**
 * 
 */
package com.love2code.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.love2code.hibernate.entity.Student;

/**
 * @author KDavara
 *
 */
public class CreateStudentDemo {

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
			// create student object
			Student student = new Student("Kishor", "Davara", "kd@love2code.com");

			// start a transaction
			session.beginTransaction();

			// save the student object
			System.out.println("Saving the student object into the database");
			session.save(student);

			// commit transaction
			session.getTransaction().commit();

			System.out.println("Student data saved successfuly.");
		} finally {
			factory.close();
		}
		}
	}
