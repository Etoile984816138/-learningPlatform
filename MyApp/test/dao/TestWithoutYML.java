package dao;

import models.Course;
import org.junit.Test;
import play.test.UnitTest;

/**
 * Created by jsh3 on 17-7-2.
 */
public class TestWithoutYML extends UnitTest {

    @Test
    public void testRate() {
        Course course = new Course();
        int rate = RateDAO.averageRate(course);
        assertTrue(rate == 0);
    }



}
