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
public class PrimaryKeyDemo {

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
			// create 3 student object
			Student student1 = new Student("John", "Doe", "john@love2code.com");
			Student student2 = new Student("Mary", "Public", "mary@love2code.com");
			Student student3 = new Student("Bonita", "Pal", "bonita@love2code.com");
			
			// start a transaction
			session.beginTransaction();

			// save the student object
			System.out.println("Saving the students object into the database");
			session.save(student1);
			session.save(student2);
			session.save(student3);

			// commit transaction
			session.getTransaction().commit();

			System.out.println("Students data saved successfuly.");
		} finally {
			factory.close();
		}

	}

}
