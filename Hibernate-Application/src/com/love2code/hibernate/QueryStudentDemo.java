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

			System.out.println("\n\nDisplay students who have last name of Doe");
			displayStudents(students);

			// query students: lastName = 'Doe' or firstName = 'Daffy'
			students = session.createQuery("from Student s where s.lastName='Doe' OR s.firstName = 'Daffy'").getResultList();

			System.out.println("\n\nDisplay students who have last name of Doe OR first name of Daffy");
			displayStudents(students);


			// query students where email like '%love2code.com'
			students = session.createQuery("from Student s where s.email LIKE '%love2code.com'").getResultList();

			System.out.println("\n\nDisplay students whose email ends with love2code.com");
			displayStudents(students);

						
			// commit transaction
			session.getTransaction().commit();
		} finally {
			factory.close();
		}
	}

	/**
	 * display student data
	 * 
	 * @param students
	 */
	private static void displayStudents(List<Student> students) {
		for (Student student : students) {
			System.out.println(student);
		}
	}
}
