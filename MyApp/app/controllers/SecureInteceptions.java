package controllers;

import models.Employee;
import play.cache.Cache;
import play.mvc.Before;
import play.mvc.Catch;
import play.mvc.Controller;

/**
 * Created by jsh3 on 17-6-30.
 * 检查session里面是否含有user
 */
public class SecureInteceptions extends Controller {

    @Catch(Exception.class)
    public static void returnErrorInfo() {
        System.err.println("some thing wrong happended");
        renderJSON(MyResp.buildErr("发生未知错误"));
    }

    //    现在模拟的是假设已经登录的情况，之后要把这个方法改成检查session然后跳转的操作
    @Before
    public static void setSession() {
        Employee employee = new Employee();
        employee.id = 1L;
        String session_id = session.getId();
        System.out.println("session_id" + session_id);
        Cache.set("1", employee);
    }
}
