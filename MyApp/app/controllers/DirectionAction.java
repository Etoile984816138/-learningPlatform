package controllers;

import com.alibaba.fastjson.JSON;
import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

/**
 * Created by C_C on 2017/6/27.
 */
public class DirectionAction extends Controller {

    //获取所有课程方向信息
    public static void index(){
        Map map = new HashMap();

        List<Map> directions = Direction.find("SELECT new map(d.id as d_id,d.name as name) FROM Direction d").fetch();

        map.put("success",directions);
        map.put("failure","");

        renderJSON(JSON.toJSONString(map));
    }
}
