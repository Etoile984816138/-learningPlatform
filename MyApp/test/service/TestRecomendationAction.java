package service;

import com.alibaba.fastjson.JSON;
import controllers.CommendationAction;
import controllers.MyResp;
import models.Course;
import org.junit.Before;
import org.junit.Test;
import play.mvc.Http;
import play.test.Fixtures;
import play.test.FunctionalTest;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.List;


/**
 * Created by jsh3 on 17-6-30.
 */
public class TestRecomendationAction extends FunctionalTest {



    @Test
    public void testSuitForMeAction() throws UnsupportedEncodingException {
        Http.Response response = GET("http://localhost:9000/course/recommendation?num=5");
        assertIsOk(response);
    }


}
