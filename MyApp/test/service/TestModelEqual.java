package service;

import models.Course;
import org.junit.Test;
import play.test.UnitTest;

/**
 * Created by jsh3 on 17-7-2.
 */
public class TestModelEqual extends UnitTest {

    @Test
    public void testEqual() {
        Course course = new Course();
        course.id = 1L;
        Course course1 = new Course();
        course1.id = 1L;
        assertEquals(course, course1);
    }

}
