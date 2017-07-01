package controllers;

import com.alibaba.fastjson.JSON;
import play.mvc.*;

import java.text.SimpleDateFormat;
import java.util.*;

import models.*;

import static ch.lambdaj.Lambda.extract;
import static ch.lambdaj.Lambda.on;

/**
 * Created by C_C on 2017/6/29.
 */
public class DiscussAction extends Controller{

    //获取当前课时的讨论区信息
    public static void getByInformation(int information_id,int type){
        Map map = new HashMap();

        int employee_id = Integer.parseInt(session.get("employee_id"));

        //排列出当前我的好友列表
        List<Employee> myfriends = Friend.find("SELECT f.to FROM Friend f WHERE f.from ",employee_id).fetch();
        List<Employee> from = Friend.find("SELECT f.from FROM Friend f WHERE f.to ",employee_id).fetch();
        myfriends.addAll(from);

        List<Discuss> discusses = Discuss.find("information = :m_id AND isNote = 0 AND type = :type " +
                "AND flag = 0 OR (flag = 1 AND employee in (:employee))")
                .setParameter("m_id",information_id)
                .setParameter("type",type)
                .setParameter("employee",myfriends)
                .fetch();

        map.put("success",discusses);
        map.put("failure","");

        renderJSON(JSON.toJSONString(map));
    }

    //获取当前课程的讨论区信息
    public static void getByCourse(int course_id,int type){
        Map map = new HashMap();

        int pageNum = Integer.parseInt(params.get("pageNum"));
        int pageSize = Integer.parseInt(params.get("pageSize"));

        int employee_id = Integer.parseInt(session.get("employee_id"));

        //排列出当前我的好友列表
        List<Employee> myfriends = Friend.find("SELECT f.to FROM Friend f WHERE f.from ",employee_id).fetch();
        List<Employee> from = Friend.find("SELECT f.from FROM Friend f WHERE f.to ",employee_id).fetch();
        myfriends.addAll(from);

        //查询该课程信息
        Course course = Course.findById(course_id);

        //提取出改课程的所有课时信息
        List<Information> informations = new ArrayList<Information>();
        for(Chapter chapter : course.chapters){
            informations.addAll(chapter.informations);
        }

        //按条件查询当前页面讨论信息
        List<Discuss> discusses = Discuss.find("information in :informations AND isNote = 1 AND type = :type " +
                "AND flag = 0 OR (flag = 1 AND employee in (:employee))")
                .setParameter("informations",extract(informations, on(Information.class).id))
                .setParameter("type",type)
                .setParameter("employee",extract(myfriends, on(Friend.class).id))
                .from((pageNum-1)*pageSize).fetch(pageNum*pageSize);

        //计算总页数
        long count = Discuss.count();
        int total = (int)Math.ceil(count/pageSize);

        map.put("success",discusses);
        map.put("total",total);
        map.put("failure","");

        renderJSON(JSON.toJSONString(map));
    }

    //划重点或者提问
    public static  void note(){
        Map map = new HashMap();

        int type = Integer.parseInt(params.get("type"));
        int flag = Integer.parseInt(params.get("flag"));
        int information_id = Integer.parseInt(params.get("information_id"));

        String content = params.get("content");
        String point_time = params.get("point_time");

        String title = content;

        //查询该课时信息
        Information information = Information.findById(information_id);

        //查询该员工信息
        int employee_id = Integer.parseInt(session.get("employeeID"));
        Employee employee = Employee.findById(employee_id);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        Discuss discuss = new Discuss(type,flag,0,0,0,0,title,content,point_time,
                df.format(new Date()),employee,information,null);

        discuss.save();

        map.put("success",true);
        map.put("failure","");

        renderJSON(JSON.toJSONString(map));
    }

    //发表讨论区主题帖
    public static  void publish(){
        Map map = new HashMap();

        int type = Integer.parseInt(params.get("type"));
        int isNick = Integer.parseInt(params.get("isNick"));//是否匿名（0-是；1-不是）
        int information_id = Integer.parseInt(params.get("information_id"));

        String content = params.get("content");
        String title = params.get("title");

        //查询该课时信息
        Information information = Information.findById(information_id);

        //查询该员工信息
        int employee_id = Integer.parseInt(session.get("employeeID"));
        Employee employee = Employee.findById(employee_id);


        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        Discuss discuss = new Discuss(type,0,1,isNick,0,0,title,content,null,df.
                format(new Date()),employee,information,null);

        discuss.save();

        map.put("success",true);
        map.put("failure","");

        renderJSON(JSON.toJSONString(map));
    }
}
