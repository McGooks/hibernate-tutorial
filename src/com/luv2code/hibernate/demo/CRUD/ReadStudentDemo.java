package com.luv2code.hibernate.demo.CRUD;

import com.luv2code.hibernate.demo.entity.Student;
import com.luv2code.hibernate.demo.utils.DateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Date;

public class ReadStudentDemo {
    public static void main(String[] args) {
        //create session factory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).buildSessionFactory();
        // create session

        try (factory) {
            Session session = factory.getCurrentSession();
            String theDateOfBirthStr = "14/06/1985";
            Date theDateOfBirth = DateUtils.parseDate(theDateOfBirthStr);
            //create a student object
            System.out.println("Create a new student object");
            Student tempStudent = new Student("Daffy", "Duck", "Dafty.Duck@1w.com", theDateOfBirth);
            // start the transaction
            session.beginTransaction();
            //save the student object
            System.out.println("Saving student");
            System.out.println(tempStudent);
            session.save(tempStudent);
            //commit the transaction
            session.getTransaction().commit();
            //Find primary key set by hibernate
            System.out.println("Saved student generated id: " + tempStudent.getId());
            //Create a new session and start transaction
            session = factory.getCurrentSession();
            session.beginTransaction();
            //retrieve student based on primary key
            System.out.println("\nGetting student with id: " + tempStudent.getId());
            Student myStudent = session.get(Student.class, tempStudent.getId());
            System.out.println("Get Complete: " + myStudent);
            //commit transaction
            session.getTransaction().commit();

            if (myStudent == null) {
                throw new Exception("User Not found");
            }

            System.out.println("Complete");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
