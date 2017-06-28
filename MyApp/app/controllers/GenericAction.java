package controllers;

import com.alibaba.fastjson.JSON;
import play.*;
import play.mvc.*;

import java.util.*;

import models.*;
/**
 * Created by C_C on 2017/6/27.
 */
public class GenericAction extends Controller{

    //获取所有课程分类信息
    public static void index(){
        Map map = new HashMap();

        List<Map> generics = Generic.find("SELECT new map(g.id as g_id,g.name as name) FROM Generic g").fetch();

        map.put("success",generics);
        map.put("failure","");

        renderJSON(JSON.toJSONString(map));
    }

    //根据方向获得课程类别信息
    public static void findByDirection(int direction_id) {
        Map map = new HashMap();

        List<Map> generics = Generic.find("SELECT new map(g.id as g_id,g.name as name) FROM Generic g WHERE g.d_id = ?",direction_id).fetch();

        map.put("success",generics);
        map.put("failure","");

        renderJSON(JSON.toJSONString(map));
    }
}
