package service.recommend;

import models.Course;

import java.util.List;

/**
 * Created by jsh3 on 17-6-28.
 */
public interface RecommendExecutor {

    List<Course> getRecommendations();

}
