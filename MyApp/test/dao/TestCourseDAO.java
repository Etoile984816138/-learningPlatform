package dao;

import models.Course;
import models.Generic;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import play.test.Fixtures;
import play.test.UnitTest;

import java.util.List;

/**
 * Created by jsh3 on 17-6-30.
 */
public class TestCourseDAO extends TestDataTable {

    static Generic generic = new Generic();

    @BeforeClass
    public static void init() {
        generic.id = 1L;
    }

    @Test
    public void testByGeneric() {
        List<Course> courses = CourseDAO.byGeneric(1);
        assertEquals(courses.size(), 3);
    }

    @Test
    public void testByGenericAndNotEqual() {
        List<Course> courses = CourseDAO.byGenericAndNotEqual(1,2L);
        assertEquals(courses.size(), 2);
    }
}
