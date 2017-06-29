package controllers;

import com.alibaba.fastjson.JSON;
import play.mvc.*;

import java.util.*;

import models.*;
import toolutils.txtUtils;

/**
 * Created by C_C on 2017/6/29.
 */
public class MaterialAction extends Controller{

    //获取某一课时的资料信息
    public static void get(int material_id){
        Map map = new HashMap();

        Material material = Material.findById(material_id);

        map.put("failure","");

        String filepath = material.subtitle;
        if(filepath != null && !"".equals(filepath)){
            String result = txtUtils.readFile(filepath);
            if("404".equals(result))
                map.put("failure","找不到字幕文件");
            else if("500".equals(result))
                map.put("failure","读取字幕文件内容发生错误");
            else
                material.subtitle = result;

        }

        map.put("success",material);

        renderJSON(JSON.toJSONString(map));
    }
}
