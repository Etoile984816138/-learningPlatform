package service.news;

import controllers.Page;
import dao.CommentDAO;
import dao.NewsDAO;
import models.Comment;
import models.Employee;
import models.New;

import java.util.Collections;
import java.util.List;

/**
 * Created by jsh3 on 17-7-2.
 */
public class BasicNewsService implements NewsService {

    //    评论区通知
    public List<Comment> getToMeComments(Employee employee, Page page) {
        int offset = page.caculateOffset();
        return CommentDAO.byEmployee(employee, offset, page.pageSize);
    }


    public List<New> getNews(Employee employee, Page page, int type) {
        int offset = page.caculateOffset();
        return NewsDAO.byEmployee(employee, type, offset, page.pageSize);
    }

    //    所有消息
    public List<New> getAll(Employee employee, Page page) {
        List<Comment> comments = getToMeComments(employee, page);
        List<New> news1 = getNews(employee, page, 1);
        List<New> news2 = getNews(employee, page, 2);
        news1.addAll(news2);
        for (Comment comment : comments) {
            news1.add(comment.createNew());
        }
        Collections.sort(news1);
        return news1;
    }


}
