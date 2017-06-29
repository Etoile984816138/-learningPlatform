package service.recommend;

import models.Course;

import java.util.List;

/**
 * Created by jsh3 on 17-6-29.
 */
public interface RecommendService {

    List<Course> latestViewRec(String userid);
    List<Course> randomRec(String userid);
    List<Course> departmentRec(String userid);
    List<Course> maxViewRec(String userid);


}
