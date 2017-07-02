package dao;

import models.Comment;
import models.Employee;
import models.New;

import java.util.List;

/**
 * Created by jsh3 on 17-7-2.
 */
public class NewsDAO {

    public static List<New> byEmployee(Employee employee, int type, int offset, int length) {
        return New.em().createQuery("select n from New n where n.to = :e and n.type = :type", New.class)
                .setParameter("e", employee)
                .setParameter("type", type)
                .setMaxResults(length)
                .setFirstResult(offset)
                .getResultList();
    }



}
