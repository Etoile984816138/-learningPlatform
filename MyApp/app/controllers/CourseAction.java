package controllers;

import com.alibaba.fastjson.JSON;
import play.mvc.*;

import java.util.*;

import models.*;

import static ch.lambdaj.Lambda.extract;
import static ch.lambdaj.Lambda.on;

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

        renderJSON(JSON.toJSONString(map));
    }

    //按某些条件查询
    private static Map queryByAll(int PageNum,int PageSize,String s_id,String g_id,String d_id,String title,String condition){
        int employee_id = Integer.parseInt(session.get("employee_id"));

        //获取该学员的具有权限观看的课程列表
        List<Course> myCourses = Power.find("SELECT p.course FROM Power p WHERE employee = ?",employee_id).fetch();

        //根据条件获取课程列表
        List<Course> courses = Course.find("SELECT new Course(id,description,title,cover,person,authority) FROM Course c " +
                "WHERE c.s_id = :s_id AND c.g_id = :g_id AND c.d_id = :d_id And c.title like:title AND" +
                "AND authority = 0 OR (authority = 1 AND c.id in :courses) OR (authority = 2 AND c.id not in :courses)" +
                "ORDER BY "+condition+" desc")
                .setParameter("s_id",s_id)
                .setParameter("g_id",g_id)
                .setParameter("d_id",d_id)
                .setParameter("title",title)
                .setParameter("courses",extract(myCourses, on(Course.class).id))
                .from((PageNum-1)*PageSize).fetch(PageNum*PageSize);

        //计算总页数
        long count = Course.count();
        int total = (int)Math.ceil(count/PageSize);


        Map map = new HashMap();
        map.put("success",courses);
        map.put("total",total);
        map.put("failure","");

        return map;
    }

}
