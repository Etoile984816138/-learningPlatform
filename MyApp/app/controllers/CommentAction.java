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

        int discuss_id = Integer.parseInt(params.get("discuss_id"));
        String content = params.get("content");

        int employee_id = Integer.parseInt(session.get("employeeID"));

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        Comment comment = null;
//        Comment comment = new Comment(discuss_id,employee_id,content,df.format(new Date()));
        comment.save();

        Discuss discuss = Discuss.findById(discuss_id);
//        discuss.number ++;
        discuss.save();

        map.put("success",true);
        map.put("failure","");

        renderJSON(JSON.toJSONString(map));
    }
}
