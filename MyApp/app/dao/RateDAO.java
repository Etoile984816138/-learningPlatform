package dao;

import models.Course;
import models.CourseRate;

/**
 * Created by jsh3 on 17-7-2.
 */
public class RateDAO {

    public static int averageRate(Course course) {
        return CourseRate.em().createQuery("select avg(c.rate) from CourseRate c where c.course = :course")
                .setParameter("course", course)
                .getFirstResult();
    }
}
