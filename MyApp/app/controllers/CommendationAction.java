package controllers;

import com.alibaba.fastjson.JSON;
import models.Course;
import models.Employee;
import play.cache.Cache;
import play.mvc.Controller;
import play.mvc.With;
import service.recommend.JDBCRecommendFactory;
import service.recommend.RecommendFactory;
import service.recommend.RecommendService;
import toolutils.ActionHelper;

import java.util.List;

/**
 * Created by jsh3 on 17-6-28.
 */
@With(SecureInteceptions.class)
public class CommendationAction extends Controller {

//最适合我， 这里只完成了一部分，还需要补充的,最好重写
    public static void suitForMe(int num) {
        RecommendFactory recommendFactory = new JDBCRecommendFactory();
        RecommendService service = recommendFactory.create();
        Employee employee = ActionHelper.getVisit();
        List<Course> courses = service.latestViewRec(employee, num);
        renderJSON(JSON.toJSONString(MyResp.buildSuc(courses)));
    }



}
