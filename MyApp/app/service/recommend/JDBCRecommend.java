package service.recommend;

import dao.CourseDAO;
import models.Course;
import models.Employee;

import java.util.Collections;
import java.util.List;

/**
 * Created by jsh3 on 17-6-29.
 */
public class JDBCRecommend implements RecommendService {


    @Override
    public List<Course> latestViewRec(Employee employee, int limit) {
        return null;
    }

    @Override
    public List<Course> randomRec(Employee employee, int limit) {
        return null;
//        return CourseDAO.RandomCourse(limit);
    }

    @Override
    public List<Course> departmentRec(Employee employee, int limit) {
        return null;
    }

//    先不忙写
    @Override
    public List<Course> maxViewRec(Employee employee, int limit) {
        return Collections.emptyList();
    }
}
