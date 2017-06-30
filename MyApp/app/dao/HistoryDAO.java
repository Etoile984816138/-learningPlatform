package dao;

import models.Course;
import models.Employee;
import models.History;
import play.db.jpa.JPA;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;
import java.util.List;

/**
 * Created by jsh3 on 17-6-29.
 */
public class HistoryDAO {

    public static List<Course> latestViewSimilarCourse(Employee employee, int limit) {
        EntityManager entityManager = JPA.em();
        entityManager.createNativeQuery("SELECT FROM emplyee_course_auth ");
        return null;
    }


    public static History finallyVisit(Employee employee) {
        return History.find("select h from History h where h.employee = :employee order by time,id desc")
                .setParameter("employee", employee).first();
    }



    public static List<History> allVisit(Employee employee) {
        return History.find("select h from History h where h.employee = :employee order by time,id desc")
                .setParameter("employee", employee).fetch();
    }



   /* public static List<Course> mostViewsCourse(int limit) {
        return History.find("select count(*),* ")
    }*/


}
