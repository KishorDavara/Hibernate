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
public class ReadStudentDemo {

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
			Student student = new Student("Daffy", "Duck", "daffy@love2code.com",new Date());

			// start a transaction
			session.beginTransaction();

			// save the student object
			System.out.println("Saving the student object into the database");
			System.out.println(student);
			session.save(student);

			// commit transaction	
			session.getTransaction().commit();

			//read the student object from the database using primary key
			System.out.println("Student saved. Generated Key "+ student.getId());
			
			//now get a new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			//Retrieve student based on id: primary key
			System.out.println("Getting Student with Id "+ student.getId());
			
			Student myStudent = session.get(Student.class, student.getId());
			
			System.out.println("Student data retrieved successfully "+myStudent);

			//commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done.!");
		} finally {
			factory.close();
		}
		}
	}
