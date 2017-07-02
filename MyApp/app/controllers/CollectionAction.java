package controllers;

import com.alibaba.fastjson.JSON;
import models.Collection;
import play.*;
import play.mvc.*;

import java.text.SimpleDateFormat;
import java.util.*;

import models.*;
/**
 * Created by C_C on 2017/7/1.
 */
public class CollectionAction extends Controller {


    public static void myCollect(){
        Map map = new HashMap();

        int pageNum = Integer.parseInt(params.get("pageNum"));
        int pageSize = Integer.parseInt(params.get("pageSize"));

        //获取该员工id
        int employee_id = Integer.parseInt(session.get("employeeID"));

        List<Collection> collections = Collection.find("employee = ?",employee_id)
                .from((pageNum-1)*pageSize)
                .fetch(pageNum*pageSize);

        //计算总页数
        long count = Collection.count();
        int total = (int)Math.ceil(count/pageSize);

        map.put("total",total);
        map.put("success",collections);
        map.put("failure","");

        renderJSON(JSON.toJSONString(map));
    }

    public static void operate(int course_id){
        Map map = new HashMap();

        //获取该员工id
        int employee_id = Integer.parseInt(session.get("employeeID"));

        //查询是否已收藏
        List<Collection> collections = Collection.find("employee = :employee AND course = :course")
                .setParameter("employee",employee_id)
                .setParameter("course",course_id)
                .fetch();

        if(collections == null){//若为空，则收藏
            //获取该员工信息
            Employee employee = Employee.findById(employee_id);

            //获取该课程信息
            Course course = Course.findById(course_id);

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            Collection collection = new Collection(df.format(new Date()),employee,course);
        }else{//若不为空，则取消收藏
            collections.get(0).delete();
        }

        map.put("success",true);
        map.put("failure","");

        renderJSON(JSON.toJSONString(map));
    }
}
