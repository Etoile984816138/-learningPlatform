package service.discuss;

import controllers.Page;
import models.Discuss;
import models.Employee;

import java.util.List;

/**
 * Created by jsh3 on 17-7-2.
 */
public interface DiscussService {

    List<Discuss> myDisscusses(Employee employee, int type, Page page);
}
