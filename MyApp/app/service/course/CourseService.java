package service.course;

import models.Course;
import models.Employee;

/**
 * Created by jsh3 on 17-7-2.
 */
public interface CourseService {

    Course details(Employee employee,  long id);
}
