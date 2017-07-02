package service.course;

import models.*;

import java.util.List;

/**
 * Created by jsh3 on 17-7-2.
 */
public class BasicCourseService implements CourseService {

    public Course details(Employee employee, long id) {
        Course course = Course.findById(id);
        if (course != null) {
            course.hasjoin = isJoin(course, employee);
            course.hasStudy = caculateStudiedInfo(employee, course);
            course.total = course.lessons.size();
        } else {
            course = new Course();
            course.id = id;
        }
        return course;
    }




    private int caculateStudiedInfo(Employee employee, Course course) {
        List<Lesson> lessons = course.lessons;
        int count = 0;
        for (Lesson lesson : lessons) {
            for (History history : lesson.histories) {
                if (history.employee.equals(employee)) {
                    count++;
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
