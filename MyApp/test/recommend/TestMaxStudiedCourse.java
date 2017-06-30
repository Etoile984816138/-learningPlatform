package recommend;

import models.Course;
import org.junit.Test;
import play.test.UnitTest;
import service.recommend.EasyRecParam;
import service.recommend.MaxStudiedCourse;
import service.recommend.RecommendExecutor;

import java.util.List;

/**
 * Created by jsh3 on 17-6-28.
 */
public class TestMaxStudiedCourse extends UnitTest {

    @Test
    public void testOtherUserStudyCourseEasyRec() {
        RecommendExecutor client = new MaxStudiedCourse();
        List<Course> courses = client.getRecommendations();
        System.out.println(courses);
        assertNotNull(courses);
    }


}
