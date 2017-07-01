package controllers;

import com.alibaba.fastjson.JSON;
import play.*;
import play.mvc.*;

import java.text.SimpleDateFormat;
import java.util.*;

import models.*;
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

        //获取改课程的总课时
        for(Study study:studies){
            study.total = 0;
            for(Chapter chapter : study.course.chapters){
                study.total += chapter.informations.size();
            }
        }

        map.put("success",studies);
        map.put("failure","");

        renderJSON(JSON.toJSONString(map));

    }
}
