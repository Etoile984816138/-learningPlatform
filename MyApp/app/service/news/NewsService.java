package service.news;

import controllers.Page;
import models.Comment;
import models.Employee;
import models.New;

import java.util.List;

/**
 * Created by jsh3 on 17-7-2.
 */
public interface NewsService {

    List<Comment> getToMeComments(Employee employee, Page page);

    List<New> getNews(Employee employee, Page page, int type);

    List<New> getAll(Employee employee, Page page);
}
