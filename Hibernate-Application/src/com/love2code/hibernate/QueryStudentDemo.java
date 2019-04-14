/**
 * 
 */
package com.love2code.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.love2code.hibernate.entity.Student;

/**
 * @author KDavara
 *
 */
public class QueryStudentDemo {

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
			// start a transaction
			session.beginTransaction();

			// query students
			List<Student> students = session.createQuery("from Student").getResultList();

			// display the students
			displayStudents(students);
			
			// query students: lastName = 'Doe'
			students = session.createQuery("from Student s where s.lastName='Doe'").getResultList();

			System.out.println("Display students who have last name of Doe");
			displayStudents(students);
			
			// commit transaction
			session.getTransaction().commit();
		} finally {
			factory.close();
		}
	}

	/** 
	 * display student data
	 * @param students
	 */
	private static void displayStudents(List<Student> students) {
		for (Student student : students) {
			System.out.println(student);
		}
	}
}
