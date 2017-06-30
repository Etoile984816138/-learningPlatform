package toolutils;

import models.Employee;
import play.cache.Cache;

/**
 * Created by jsh3 on 17-6-30.
 */
public class ActionHelper {

    public static Employee getVisit() {
//        Employee employee = new Employee();
//        employee.id = 1L;
//        return employee;
        return Cache.get("1", Employee.class);
    }
}
