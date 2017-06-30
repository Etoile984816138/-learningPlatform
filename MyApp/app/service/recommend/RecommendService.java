package service.recommend;

import models.Course;
import models.Employee;

import java.util.List;

/**
 * Created by jsh3 on 17-6-29.
 */
public interface RecommendService {

    List<Course> latestViewRec(Employee employee, int limit);
    List<Course> randomRec(Employee employee, int limit);
    List<Course> departmentRec(Employee employee, int limit);
    List<Course> maxViewRec(Employee employee, int limit);


}
