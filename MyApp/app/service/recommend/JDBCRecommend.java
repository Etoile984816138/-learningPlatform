package service.recommend;

import dao.CourseDAO;
import dao.HistoryDAO;
import models.Course;
import models.Employee;
import models.History;

import java.util.Collections;
import java.util.List;

/**
 * Created by jsh3 on 17-6-29.
 */
public class JDBCRecommend implements RecommendService {


    @Override
    public List<Course> latestViewRec(Employee employee, int limit) {
        History history = HistoryDAO.finallyVisit(employee);
        // 根据gid
        List<Course> courses = Collections.EMPTY_LIST;
        if (history != null) {
            int gid = history.lesson.course.g_id;
            long courseId = history.lesson.course.id;
            courses = CourseDAO.byGenericAndNotEqual(gid, courseId);
        }
        return courses;
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
