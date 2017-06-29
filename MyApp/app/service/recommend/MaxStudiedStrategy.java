package service.recommend;

import models.Course;
import models.Study;

import java.util.List;

/**
 * Created by jsh3 on 17-6-28.
 * 下策
 */
@Deprecated
public class MaxStudiedStrategy {

    private static final String sql = "select count(*),s from Study s group by course_id" +
            " order by count(*) desc";

    public List<Course> getMaxStudiedCourse(int limit) {
        return Study.find(sql).fetch(limit);
    }

}
