package recommend;

import models.Course;
import org.junit.Test;
import play.mvc.Http;
import play.test.FunctionalTest;
import service.recommend.MaxStudiedCourse;
import service.recommend.RecommendExecutor;

import java.util.List;


/**
 * Created by jsh3 on 17-6-28.
 */
public class TestAction extends FunctionalTest {

    @Test
    public void test200() {
        Http.Response response = GET("/course/recommendation");
        assertStatus(200, response);
    }


    @Test
    public void testHighRateItem() {

    }

    @Test
    public void testRecommendSimilarCourse() {

    }
}
