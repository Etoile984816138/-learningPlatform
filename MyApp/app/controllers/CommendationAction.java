package controllers;

import com.alibaba.fastjson.JSON;
import models.Course;
import models.Employee;
import play.mvc.Controller;
import service.recommend.JDBCRecommendFactory;
import service.recommend.RecommendFactory;
import service.recommend.RecommendService;
import toolutils.ActionHelper;

import java.util.List;

/**
 * Created by jsh3 on 17-6-28.
 */
public class CommendationAction extends Controller {

    public static void suitForMe(int num) {
        RecommendFactory recommendFactory = new JDBCRecommendFactory();
        RecommendService service = recommendFactory.create();
        Employee employee = ActionHelper.getVisit();
        List<Course> courses = service.latestViewRec(employee, num);
        renderJSON(JSON.toJSONString(MyResp.buildSuc(courses)));
    }
}
