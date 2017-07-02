package dao;

import controllers.Page;
import models.Discuss;
import models.Employee;

import java.util.List;

/**
 * Created by jsh3 on 17-7-2.
 */
public class DiscussDAO {

    public static List<Discuss> myList(Employee employee, int type, int offset, int length) {
        return Discuss.em().createQuery("select d from Discuss d where d.employee = :e and d.isNote = :isNote", Discuss.class)
                .setParameter("e", employee)
                .setParameter("isNote", type)
                .setFirstResult(offset)
                .setMaxResults(length)
                .getResultList();
    }
}
