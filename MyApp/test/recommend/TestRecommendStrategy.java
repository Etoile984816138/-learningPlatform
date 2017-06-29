package recommend;

import models.Course;
import org.junit.Test;
import play.test.UnitTest;
import service.recommend.MaxStudiedStrategy;

import java.util.List;

/**
 * Created by jsh3 on 17-6-28.
 */
public class TestRecommendStrategy extends UnitTest {


    @Test
    public void testOtherUserStudyCourseEasyRec() {
        MaxStudiedStrategy maxStudiedStrategy = new MaxStudiedStrategy();
        List<Course> courses = maxStudiedStrategy.getMaxStudiedCourse(1);
        assertTrue(courses.size() == 0);
    }

    @Test
    public void testRandomCourseRecommend() {

    }

}
