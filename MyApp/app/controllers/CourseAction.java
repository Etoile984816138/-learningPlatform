package controllers;

import com.alibaba.fastjson.JSON;
import play.*;
import play.mvc.*;

import java.util.*;

import models.*;
import toolutils.ListSort;

/**
 * Created by C_C on 2017/6/27.
 */
public class CourseAction extends Controller{

    //获取某页面最新课程
    public static void GetNew(int PageNum,int PageSize,String s_id,String g_id,String d_id,String title){
        Map map = QueryByAll(PageNum,PageSize,s_id,g_id,d_id,title,"time","str");
        renderJSON(map);
    }
    //获取某页面最热课程
    public static void GetHot(int PageNum,int PageSize,String s_id,String g_id,String d_id,String title){
        Map map = QueryByAll(PageNum,PageSize,s_id,g_id,d_id,title,"click",null);
        renderJSON(map);
    }

    //按某些条件查询
    private static Map QueryByAll(int PageNum,int PageSize,String s_id,String g_id,String d_id,String title,String method,String type){
        int employee_id = Integer.parseInt(session.get("employeeID"));
        List GroupIDs = Power.find("SELECT g.group_id FROM Group g WHERE g.employee_id = ?",employee_id).fetch();
        //获取该学员的权限列表
        if("0".equals(s_id)) s_id = null;
        if("0".equals(g_id)) g_id = null;
        if("0".equals(d_id)) d_id = null;

        List<Course> Courses = Course.find("SELECT new Course(id,description,title,cover) FROM Course.c " +
                "WHERE c.power = ? AND c.s_id = ? AND c.g_id = ? AND d_id = ? And " +
                "c.title like:title",GroupIDs,s_id,g_id,d_id).setParameter("title",title).fetch();
        //根据条件获取课程列表
        int total = Courses.size()/PageSize;
        //计算页面总数
        new ListSort<Course>().sort(Courses,method,"desc",type);
        //按条件排序
        Courses = Courses.subList((PageNum-1)*PageSize,PageNum*PageSize);
        //获取某页面信息
        Map map = new HashMap();
        map.put("Courses",Courses);
        map.put("total",total);
        return map;
    }

}
