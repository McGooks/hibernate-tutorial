package com.luv2code.hibernate.demo.CRUD;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UpdateStudentDemo {
    public static void main(String[] args) {
        //create session factory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).buildSessionFactory();
        // create session

        try (factory) {
            factory.getCurrentSession();
            Session session;
            int studentId = 1;
            //Create a new session and start transaction
            session = factory.getCurrentSession();
            session.beginTransaction();

            //retrieve student based on primary key
            System.out.println("\nGetting student with id: " + studentId);
            Student myStudent = session.get(Student.class, studentId);
            System.out.println("Updating Student");
            myStudent.setFirstName("Scooby");
            //commit transaction
            session.getTransaction().commit();

            //BULK UPDATE
            //Create a new session and start transaction
            session = factory.getCurrentSession();
            session.beginTransaction();

            //update email for all students
            System.out.println("Update email for all db records");
            session.createQuery("update Student set email='goo@fmail.com'").executeUpdate();
            //commit transaction
            session.getTransaction().commit();


            System.out.println("Complete");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
