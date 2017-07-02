package service.course;

import models.*;

import java.util.List;

/**
 * Created by jsh3 on 17-7-2.
 */
public class BasicCourseService implements CourseService {

    public Course details(Employee employee) {

        Course course = Course.findById(employee.id);
        course.hasjoin = isJoin(course, employee);
        course.star = caculateStudiedInfo(employee, course);

        return null;
    }

    private int caculateStudiedInfo(Employee employee, Course course) {
        List<Chapter> chapters = course.chapters;
        int count = 0;
        for (Chapter chapter : chapters) {
            for (Information information : chapter.informations) {
                for (History history : information.histories) {
                    if (history.employee.equals(employee)) {
                        count ++ ;
                    }
                }
            }
        }
        return count;
    }

    private boolean isJoin(Course course, Employee employee) {
        List<Employee> employees = course.employees;
        boolean isjoin = false;
        for (Employee e : employees) {
            if (employee.equals(e)) {
                isjoin = true;
            }
        }
        return isjoin;
    }


}
