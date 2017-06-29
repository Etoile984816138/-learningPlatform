package toolutils;

import java.util.List;

/**
 * Created by C_C on 2017/6/28.

 * 定义一个分页对象
 * @author acer
 *
 */
public class Pager<T> {
    public int page;//当前页码
    public int total;//总页码
    public int rows;//总条数

    public String hql;//分页语句

    public List<T> list;//返回的数据集合

    public Pager(int page, int total, int rows, String hql, List<T> list) {
        this.page = page;
        this.total = total;
        this.rows = rows;
        this.hql = hql;
        this.list = list;
    }
}
