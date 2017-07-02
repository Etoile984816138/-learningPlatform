package controllers;

import com.alibaba.fastjson.JSON;
import models.Comment;
import models.Employee;
import models.New;
import play.mvc.Controller;
import service.news.BasicNewsService;
import service.news.NewsService;
import toolutils.ActionHelper;

import java.util.List;

/**
 * Created by jsh3 on 17-7-2.
 */
public class NewsAction extends Controller {

    private static NewsService service = new BasicNewsService();

    public static void comment(int pageNum, int pageSize) {
        Employee employee = ActionHelper.getVisit();
        List<Comment> comments = service.getToMeComments(employee, new Page(pageNum, pageSize));
        String result = JSON.toJSONString(MyResp.buildSuc(comments));
        renderJSON(result);
    }

    public static void friend(int pageNum, int pageSize) {
        getNew(new Page(pageNum, pageSize), 1);
    }

    public static void system(int pageNum, int pageSize) {
        getNew(new Page(pageNum, pageSize), 2);
    }

    public static void all(int pageNum, int pageSize) {
        Employee employee = ActionHelper.getVisit();
        Page page = new Page(pageNum, pageSize);
        List<New> news = service.getAll(employee, page);
        renderJSON(JSON.toJSONString(MyResp.buildSuc(news)));
    }

    private static void getNew(Page page, int type) {
        Employee employee = ActionHelper.getVisit();
        List<New> news = service.getNews(employee, page, type);
        String result = JSON.toJSONString(MyResp.buildSuc(news));
        renderJSON(result);
    }
}
