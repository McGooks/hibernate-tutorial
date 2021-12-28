package com.luv2code.hibernate.demo.entity;

import com.luv2code.hibernate.demo.utils.DateUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.text.ParseException;
import java.util.Date;

public class PrimaryKeyDemo {
    public static void main(String[] args) {
        //create session factory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).buildSessionFactory();
        // create session

        try (factory) {
            Session session = factory.getCurrentSession();
            String theDateOfBirthStr = "14/06/1985";
            Date theDateOfBirth = DateUtils.parseDate(theDateOfBirthStr);
            //create 3 student object
            System.out.println("Creating 3 new student objects");
            Student tempStudent1 = new Student("Gary", "Marshall-Adams", "gary.mars@1w.com", theDateOfBirth);
            Student tempStudent2 = new Student("Mark", "McFarland", "Mark.mcfar@1w.com", theDateOfBirth);
            Student tempStudent3 = new Student("Justin", "Marshall-Adams", "Justin.mars@1w.com", theDateOfBirth);
            // start the transaction
            session.beginTransaction();
            //save the student object
            System.out.println("Saving student");
            session.save(tempStudent1);
            session.save(tempStudent2);
            session.save(tempStudent3);
            //commit the transaction
            session.getTransaction().commit();
            System.out.println("Complete");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
