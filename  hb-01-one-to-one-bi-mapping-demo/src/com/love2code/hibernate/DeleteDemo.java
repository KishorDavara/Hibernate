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
public class DeleteDemo {

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

			//get the instructor by primary key/id
			int instructorID  = 1;
			Instructor instructor = session.get(Instructor.class, instructorID);
			
			System.out.println("Found instructor "+ instructor);
			
			//delete the instructor
			if(instructor != null) {
				System.out.println("Deleting "+instructor);
				
				//Note:this will also delete associated detail object because of CascadeType.ALL
				session.delete(instructor);
			}
			
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
