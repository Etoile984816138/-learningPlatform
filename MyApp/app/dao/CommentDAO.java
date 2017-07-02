package dao;

import models.Comment;
import models.Employee;
import models.New;

import java.util.List;

/**
 * Created by jsh3 on 17-7-2.
 */
public class CommentDAO {

    public static List<Comment> byEmployee(Employee employee, int offset, int length) {
        return New.em().createQuery("select c from Comment c where c.to = :e", Comment.class)
                .setParameter("e", employee)
                .setFirstResult(offset)
                .setMaxResults(length)
                .getResultList();
    }
}
