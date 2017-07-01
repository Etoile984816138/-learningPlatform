package controllers;

import com.alibaba.fastjson.JSON;
import play.mvc.*;

import java.text.SimpleDateFormat;
import java.util.*;

import models.*;

/**
 * Created by C_C on 2017/6/29.
 */
public class CommentAction extends Controller {

    //评论
    public static  void add(){
        Map map = new HashMap();

        String content = params.get("content");

        //获取评论的讨论区信息
        int discuss_id = Integer.parseInt(params.get("discuss_id"));
        Discuss discuss = Discuss.findById(discuss_id);

        //获取当前登录员工信息
        int employee_id = Integer.parseInt(session.get("employeeID"));
        Employee employee = Employee.findById(employee_id);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        Comment comment = new Comment(0,content,df.format(new Date()),discuss.employee,employee,discuss);
        comment.save();

        map.put("success",true);
        map.put("failure","");

        renderJSON(JSON.toJSONString(map));
    }
}
