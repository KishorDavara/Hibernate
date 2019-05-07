/**
 * 
 */
package com.love2code.hibernate;

import java.util.Date;

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
			// create a student object
            System.out.println("creating a new student object ...");

            String theDateOfBirthStr = "31/12/1998";

            Student student = new Student("Paully", "Doe", "paul@luv.com");
            
			// start a transaction
			session.beginTransaction();

			// save the student object
			System.out.println("Saving the student object into the database");
			session.save(student);

			// commit transaction
			session.getTransaction().commit();

			System.out.println("Student data saved successfuly.");
		}catch (Exception exc) {
            exc.printStackTrace();
        } finally {
			factory.close();
		}
		}
	}
