package controllers;

import com.alibaba.fastjson.JSON;
import play.mvc.*;

import java.text.SimpleDateFormat;
import java.util.*;

import models.*;

import static ch.lambdaj.Lambda.index;
import static ch.lambdaj.Lambda.on;

/**
 * Created by C_C on 2017/7/1.
 */
public class StudyAction extends Controller {

    //查询我的学习记录
    public static void myStudy() {
        Map map = new HashMap();

        int pageNum = Integer.parseInt(params.get("pageNum"));
        int pageSize = Integer.parseInt(params.get("pageSize"));

        int employee_id = Integer.parseInt(session.get("employee_id"));

        List<Study> studies = Study.find("employee = ?",employee_id)
                .setParameter("employee",employee_id)
                .from((pageNum-1)*pageSize)
                .fetch(pageNum*pageSize);

        List<History> histories = History.find("employee = ?",employee_id).fetch();
        Map<Course,List<History>> mapByLesson = index(histories, on(History.class).lesson.course);

        //获取改课程的总课时
        for(Study study:studies){
            study.total = study.course.lessons.size();
            study.hasStudy = mapByLesson.get(study.course).size();
        }

        //计算总页数
        long count = Study.count();
        int total = (int)Math.ceil(count/pageSize);

        map.put("total",total);
        map.put("success",studies);
        map.put("failure","");

        renderJSON(JSON.toJSONString(map));

    }

    //课程参加
    public static void enter(int course_id){
        Map map = new HashMap();

        //获取改成员的信息
        int employee_id = Integer.parseInt(session.get("employee_id"));
        Employee employee = Employee.findById(employee_id);

        //获取改课程信息
        Course course = Course.findById(course_id);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        Study study = new Study(df.format(new Date()),employee,course);
        study.save();

        map.put("success",true);
        map.put("failure","");

        renderJSON(JSON.toJSONString(map));
    }
}
