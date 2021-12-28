package com.luv2code.hibernate.demo.CRUD;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteStudentDemo {
    public static void main(String[] args) {
        //create session factory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).buildSessionFactory();
        // create session

        try (factory) {
            factory.getCurrentSession();
            Session session;
            int studentId = 4;
            //Create a new session and start transaction
            session = factory.getCurrentSession();
            session.beginTransaction();

            System.out.println("\nGetting student with id: " + studentId);
            Student myStudent = session.get(Student.class, studentId);

            //delete student based on studentId
            System.out.println("Deleting record:");
            session.delete(myStudent);
//            session.createQuery("delete from Student where id="+studentId).executeUpdate();
            //commit transaction
            session.getTransaction().commit();


            System.out.println("Complete");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
