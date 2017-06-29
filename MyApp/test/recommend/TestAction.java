package recommend;

import org.junit.Test;
import play.mvc.Http;
import play.test.FunctionalTest;


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
