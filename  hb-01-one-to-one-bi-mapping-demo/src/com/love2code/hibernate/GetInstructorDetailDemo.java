/**
 * 
 */
package com.love2code.hibernate;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.love2code.hibernate.entity.Instructor;
import com.love2code.hibernate.entity.InstructorDetail;
import com.love2code.hibernate.entity.Student;

/**
 * @author KDavara
 *
 */
public class GetInstructorDetailDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();

		// create a session
		Session session = factory.getCurrentSession();

		try {
			
			// start a transaction
			session.beginTransaction();
            
			//get the instructor detail object
			int id = 2;
			InstructorDetail instructorDetail = session.get(InstructorDetail.class, id);
			
			//print the instructor detail
			System.out.println("Instructor Detail "+instructorDetail);
			
			//print the associated instructor
			System.out.println("Associated instructor "+instructorDetail.getInstructor());
			 
			// commit transaction
			session.getTransaction().commit();

			System.out.println("Done.");
		}catch (Exception exc) {
            exc.printStackTrace();
        } finally {
			factory.close();
		}
		}
	}
