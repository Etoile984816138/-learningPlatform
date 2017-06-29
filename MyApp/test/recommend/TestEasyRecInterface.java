package recommend;

import models.Course;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import play.test.FunctionalTest;
import play.test.UnitTest;
import service.recommend.AbstarctEasyRec;
import service.recommend.HighRateCourseStrategy;
import service.recommend.OtherUserStudyStrategy;
import service.recommend.SimilarCourseStrategy;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * Created by jsh3 on 17-6-29.
 */
public class TestEasyRecInterface extends UnitTest {

    CloseableHttpClient client;

    @BeforeClass
    public void init() {
        client = HttpClients.createDefault();
    }

    @Test
    public void testOtherUserAlsoStudy() {
        AbstarctEasyRec easyRecParam = new OtherUserStudyStrategy(1,5,"770");
        List<Course> courses = easyRecParam.doGet(client);
        assertTrue(courses.size() == 0);
    }

    @Test
    public void testHighRateCourse() {
        AbstarctEasyRec easyRecParam = new HighRateCourseStrategy(1,5,"770");
        List<Course> courses = easyRecParam.doGet(client);
        assertTrue(courses.size() == 0);
    }

    @Test
    public void testSimilarCourse() {
        AbstarctEasyRec easyRecParam = new SimilarCourseStrategy(1,5,"770");
        List<Course> courses = easyRecParam.doGet(client);
        System.out.println(courses);
        assertTrue(courses.size() == 0);
    }

    @AfterClass
    public void closeClient() throws IOException {
        client.close();
    }
}
