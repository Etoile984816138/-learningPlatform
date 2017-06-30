package recommend;

import models.Course;
import org.junit.Before;
import org.junit.Test;
import play.test.UnitTest;
import service.recommend.MaxStudiedStrategy;
import service.recommend.RandomStrategy;

import java.util.List;

/**
 * Created by jsh3 on 17-6-28.
 */
public class TestRecommendStrategy extends UnitTest {


    @Test
    public void testOtherUserStudyCourseEasyRec() {
        MaxStudiedStrategy maxStudiedStrategy = new MaxStudiedStrategy();
        List<Course> courses = maxStudiedStrategy.getMaxStudiedCourse(1);
        assertTrue(courses.size() == 1);
    }

    @Before
    public void addCourse() {
        new Course(0,1,2,3,4,"","","","").save();
        new Course(0,1,2,3,4,"","","","").save();
        new Course(0,1,2,3,4,"","","","").save();
        new Course(0,1,2,3,4,"","","","").save();
        new Course(0,1,2,3,4,"","","","").save();
    }

    @Test
    public void testRandomCourseRecommend() {
        RandomStrategy randomStrategy = new RandomStrategy();
        List<Course> courses = randomStrategy.randomRecommend(3);
        System.out.println(courses);
        assertTrue(courses.size() <= 3);
    }

}
