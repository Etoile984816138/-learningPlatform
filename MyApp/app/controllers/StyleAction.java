package controllers;

import com.alibaba.fastjson.JSON;
import play.*;
import play.mvc.*;

import java.util.*;

import models.*;
import toolutils.ListSort;
/**
 * Created by C_C on 2017/6/27.
 */
public class StyleAction extends Controller{

    //获取所有课程分类信息
    public static void index(int generic_id){
        List<Map> styles = Generic.find("SELECT new map(s.id as s_id,s.name as name) FROM Style s").fetch();
        renderJSON(JSON.toJSONString(styles));
    }

    //根据课程类别获取课程分类信息
    public static void findByGeneric(int generic_id) {
        List<Map> styles = Generic.find("SELECT new map(s.id as s_id,s.name as name) FROM Style s WHERE s.g_id = ?", generic_id).fetch();
        renderJSON(JSON.toJSONString(styles));
    }
}
