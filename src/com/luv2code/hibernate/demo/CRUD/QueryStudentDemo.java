package com.luv2code.hibernate.demo.CRUD;

import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class QueryStudentDemo {
    public static void main(String[] args) {
        //create session factory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).buildSessionFactory();
        // create session

        try (factory) {
            Session session = factory.getCurrentSession();
            // start the transaction
            session.beginTransaction();
            //query students
            List<Student> theStudents = session.createQuery("from Student").getResultList();
            //display the students
            System.out.println("List of Students: ");
            DisplayStudents(theStudents);
            System.out.println("------------");
            //query students: lastname = 'duck'
            theStudents = session.createQuery("from Student s where s.lastName='Duck'").getResultList();
            System.out.println("Students with lastName 'Duck': ");
            DisplayStudents(theStudents);
            System.out.println("------------");
            //query students: lastName = 'Marshall-Adams' or firstName = 'Gary'
            theStudents = session.createQuery("from Student s where s.lastName='Marshall-Adams' OR s.firstName='Gary'").getResultList();
            System.out.println("Students with lastName 'Duck' OR firstName 'Daffy: ");
            DisplayStudents(theStudents);
            System.out.println("------------");
            //query students: email is like @1w
            theStudents = session.createQuery("from Student s where s.email like '%1w.com'").getResultList();
            System.out.println("Students with email like @1w: ");
            DisplayStudents(theStudents);
            System.out.println("------------");
            //commit the transaction
            session.getTransaction().commit();
            System.out.println("Complete");
        }
    }

    private static void DisplayStudents(List<Student> theStudents) {
        for (Student tempStudent : theStudents) {
            System.out.println(tempStudent);
        }
    }
}
