package dao;

import models.Course;
import models.Generic;
import models.History;
import play.db.jpa.JPA;

import java.util.List;

/**
 * Created by jsh3 on 17-6-29.
 */
//    等待单元测试
public class CourseDAO {

    /*public static List<Course> RandomCourse(int size) {
        return HistoryDAO.latestViewCourse(size);
    }*/

    public static List<Course> byGeneric(int g_id) {
        return Course.find("g_id", g_id).fetch();
    }

    public static List<Course> byGenericAndNotEqual(int g_id, long course_id) {
        return Course.em().createQuery("select c from Course c where c.g_id = :g_id and c.id <> :id", Course.class)
                .setParameter("g_id", g_id)
                .setParameter("id", course_id)
                .getResultList();
    }

}
