package controllers;

import com.alibaba.fastjson.JSON;
import play.mvc.*;

import java.text.SimpleDateFormat;
import java.util.*;

import models.*;

/**
 * Created by C_C on 2017/7/1.
 */
public class HistoryAction extends Controller {

    //查询我的历史记录
    public static void myHistroy() {
        Map map = new HashMap();

        int pageNum = Integer.parseInt(params.get("pageNum"));
        int pageSize = Integer.parseInt(params.get("pageSize"));

        int employee_id = Integer.parseInt(session.get("employee_id"));

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String point_time = df.format(getPrevDay(new Date(),7));

        List<History> histories = History.find("employee = :employee AND time > :time ORDER BY time desc")
                .setParameter("employee",employee_id)
                .setParameter("time",point_time)
                .from((pageNum-1)*pageSize)
                .fetch(pageNum*pageSize);
        //计算总页数
        long count = History.count();
        int total = (int)Math.ceil(count/pageSize);

        map.put("total",total);
        map.put("success",GroupByTime(histories));
        map.put("failure","");

        renderJSON(JSON.toJSONString(map));
    }

    //记录历史
    public static void record(){
        Map map = new HashMap();

        String point = params.get("point");

        //获取该课时信息
        int lesson_id = Integer.parseInt(params.get("lesson_id"));
        Lesson lesson = Lesson.findById(lesson_id);

        //查询该员工信息
        int employee_id = Integer.parseInt(session.get("employeeID"));
        Employee employee = Employee.findById(employee_id);

        String cover = "";//历史记录封面

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        History history = new History(cover,df.format(new Date()),point, lesson,employee);

        map.put("success",true);
        map.put("failure","");

        renderJSON(JSON.toJSONString(map));
    }

    //根据时间分组排列
    private static List<Map> GroupByTime(List<History> histories){
        List<Map> list = new ArrayList<Map>();
        int index = 0;
        for(int i = 1 ; i < histories.size(); i++){
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
            if(df.format(histories.get(i).time).compareTo(df.format(histories.get(index).time)) != 0) {
                Map map = new HashMap();
                map.put("time",df.format(df.format(histories.get(index).time)));
                map.put("list", histories.subList(index, i));
                list.add(map);
                index = i;
            }
            if(i == histories.size()-1){
                Map map = new HashMap();
                map.put("time",df.format(df.format(histories.get(index).time)));
                map.put("list",histories.subList(index, i+1));
                list.add(map);
            }
        }
        return list;
    }

    //前N天时间
    public static Date getPrevDay(Date date,int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, - n);
        date = calendar.getTime();
        return date;
    }
}
