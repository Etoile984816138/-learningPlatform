package service.discuss;

import controllers.Page;
import dao.DiscussDAO;
import models.Discuss;
import models.Employee;

import java.util.List;

/**
 * Created by jsh3 on 17-7-2.
 */
public class BasicDiscussService implements DiscussService {


    @Override
    public List<Discuss> myDisscusses(Employee employee, int type, Page page) {
        int offset = page.caculateOffset();
        int length = page.pageNum;
        return DiscussDAO.myList(employee, type, offset, length);
    }
}
