package controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jsh3 on 17-6-30.
 */
public class MyResp {

    public Object success;
    public List<String> errors = new ArrayList<String>(4);


    public static final MyResp buildSuc(Object o) {
        MyResp myResp = new MyResp();
        myResp.success = o;
        return myResp;
    }

    public static final MyResp buildErr(String... strings) {
        MyResp myResp = new MyResp();
        myResp.errors.addAll(Arrays.asList(strings));
        return myResp;
    }
}
