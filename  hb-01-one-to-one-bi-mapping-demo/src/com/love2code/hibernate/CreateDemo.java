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
public class CreateDemo {

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
			//create the objects
			Instructor instructor = new Instructor("Kishor", "Davara", "kd@love2code.com");
			
			InstructorDetail instructorDetail = new InstructorDetail("http://www.love2code.com/youtube", "Love to code");

			//associate the objects
			instructor.setInstructorDetail(instructorDetail);
			
			// start a transaction
			session.beginTransaction();

			//save the instructor
			//Note:This will also save the instuctor detail object because of CascadeType.ALL
			System.out.println("Saving Instructor "+instructor);
			session.save(instructor);
			
			// commit transaction
			session.getTransaction().commit();

			System.out.println("Data saved successfuly.");
		}catch (Exception exc) {
            exc.printStackTrace();
        } finally {
			factory.close();
		}
		}
	}
