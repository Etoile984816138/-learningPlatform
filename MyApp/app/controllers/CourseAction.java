package controllers;

import com.alibaba.fastjson.JSON;
import play.mvc.*;

import java.util.*;

import models.*;

/**
 * Created by C_C on 2017/6/27.
 */
public class CourseAction extends Controller{
    //获取某页面最新课程
    public static void getNew(){
        int pageNum = Integer.parseInt(params.get("pageNum"));
        int pageSize = Integer.parseInt(params.get("pageSize"));

        String s_id = params.get("s_id");
        String g_id = params.get("g_id");
        String d_id = params.get("d_id");
        String title = params.get("title");

        Map map = queryByAll(pageNum,pageSize,s_id,g_id,d_id,title,"time");

        map.put("failure","");

        renderJSON(JSON.toJSONString(map));
    }
    //获取某页面最热课程
    public static void getHot(){
        int pageNum = Integer.parseInt(params.get("pageNum"));
        int pageSize = Integer.parseInt(params.get("pageSize"));

        String s_id = params.get("s_id");
        String g_id = params.get("g_id");
        String d_id = params.get("d_id");
        String title = params.get("title");

        Map map = queryByAll(pageNum,pageSize,s_id,g_id,d_id,title,"person");

        map.put("failure","");

        renderJSON(JSON.toJSONString(map));
    }

    //按某些条件查询
    private static Map queryByAll(int PageNum,int PageSize,String s_id,String g_id,String d_id,String title,String condition){
        int employee_id = Integer.parseInt(session.get("employeeID"));

        //获取该学员的权限列表
//        List <Authority> authorities = Power.find("SELECT p.authority FROM Power p WHERE p.employee.id = ?",employee_id).fetch();

        if("0".equals(s_id)) s_id = null;
        if("0".equals(g_id)) g_id = null;
        if("0".equals(d_id)) d_id = null;

        //根据条件获取课程列表
        List<Course> courses = Course.find("SELECT new Course(id,description,title,cover,person,authority) FROM Course.c " +
                "WHERE c.authority in (:authority) AND c.s_id = :s_id AND c.g_id = :g_id AND d_id = :d_id And c.title like:title " +
                "ORDER BY "+condition+" desc")
//                .setParameter("authority",authorities)
                .setParameter("s_id",s_id)
                .setParameter("g_id",g_id)
                .setParameter("d_id",d_id)
                .setParameter("title",title)
                .from((PageNum-1)*PageSize).fetch(PageNum*PageSize);

        //计算总页数
        long count = Course.count();
        int total = (int)Math.ceil(count/PageSize);


        Map map = new HashMap();
        map.put("success",courses);
        map.put("total",total);
        return map;
    }


    public static Course details() {
        return null;
    }
}
