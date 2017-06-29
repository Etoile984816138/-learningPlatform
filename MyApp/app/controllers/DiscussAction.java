package controllers;

import com.alibaba.fastjson.JSON;
import play.mvc.*;

import java.text.SimpleDateFormat;
import java.util.*;

import models.*;

/**
 * Created by C_C on 2017/6/29.
 */
public class DiscussAction extends Controller{

    //获取某一课时的讨论区信息
    public static void get(int material_id){
        Map map = new HashMap();

        int employee_id = Integer.parseInt(session.get("employeeID"));
        List friends = Friend.find("employee_id1 = ? OR employee_id2 = ?",employee_id).fetch();


        List<Map> discusses = Discuss.find("SELECT new Map(e.port as port,e.name as name,d.type ad type," +
                "d.content as content,d.point_time as point_time,d.time as time,d.id as id,d.number ad number) " +
                "FROM Employee e,Discuss d WHERE e.id = d.employee_id AND d.material_id = mid " +
                "AND d.flag = 0" +
                "OR d.flag = 1 AND d.employee_id = eid")
                .setParameter("mid",material_id)
                .setParameter("eid",friends)
                .fetch();

        map.put("success",discusses);
        map.put("failure","");

        renderJSON(JSON.toJSONString(map));
    }

    //划重点或者提问
    public static  void add(){
        Map map = new HashMap();

        int type = Integer.parseInt(params.get("type"));
        int flag = Integer.parseInt(params.get("flag"));

        int material_id = Integer.parseInt(params.get("material_id"));

        String content = params.get("content");
        String point_time = params.get("point_time");

        int employee_id = Integer.parseInt(session.get("employeeID"));

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

        Discuss discuss = new Discuss(employee_id,material_id,type,flag,0,content,point_time,df.format(new Date()));
        discuss.save();

        map.put("success",true);
        map.put("failure","");

        renderJSON(JSON.toJSONString(map));
    }
}
