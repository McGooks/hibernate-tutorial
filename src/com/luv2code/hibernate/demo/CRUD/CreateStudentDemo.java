package com.luv2code.hibernate.demo.CRUD;

import com.luv2code.hibernate.demo.entity.Student;
import com.luv2code.hibernate.demo.utils.DateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.text.ParseException;
import java.util.Date;

public class CreateStudentDemo {
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
            Student tempStudent = new Student("Glenn", "Marshall-Adams", "glenn.mars@1w.com", theDateOfBirth);
            // start the transaction
            session.beginTransaction();
            //save the student object
            System.out.println("Saving student");
            session.save(tempStudent);
            //commit the transaction
            session.getTransaction().commit();
            System.out.println("Complete");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
