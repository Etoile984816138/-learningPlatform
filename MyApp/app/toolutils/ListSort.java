package toolutils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by C_C on 2017/6/24.
 */
public class ListSort<T>{

    public void sort(List list,final String method) {//method 比较的GET方法名称）
        Collections.sort(list,new Comparator<T>(){
            public int compare(Object ObjectA,Object ObjectB) {
                try {
                    Method MethodA = ((T) ObjectA).getClass().getMethod(method, null);
                    Method MethodB = ((T) ObjectB).getClass().getMethod(method, null);
                    String a = MethodA.invoke(((T) ObjectA), null).toString();
                    String b = MethodB.invoke(((T) ObjectB), null).toString();

                    if(Double.parseDouble(a)>Double.parseDouble(b))
                        return 1;
                    else if(Double.parseDouble(a)<Double.parseDouble(b))
                        return -1;
                    else
                        return 0;

                } catch (NoSuchMethodException e) {
                    System.out.println(e);
                } catch (IllegalAccessException e) {
                    System.out.println(e);
                } catch (InvocationTargetException e) {
                    System.out.println(e);
                }
                return 0;
            }
        });
    }


    public void sort(List list,final String method,final String sort) {//method 比较的GET方法名称，sort（null为正序，“desc”为倒叙）
        Collections.sort(list,new Comparator<T>(){
            public int compare(Object ObjectA,Object ObjectB) {
                try {
                    Method MethodA = ((T) ObjectA).getClass().getMethod(method, null);
                    Method MethodB = ((T) ObjectB).getClass().getMethod(method, null);
                    String a = MethodA.invoke(((T) ObjectA), null).toString();
                    String b = MethodB.invoke(((T) ObjectB), null).toString();
                    if ("desc".equals(sort)) { //倒序
                        if(Double.parseDouble(b)>Double.parseDouble(a))
                            return 1;
                        else if(Double.parseDouble(b)<Double.parseDouble(a))
                            return -1;
                        else
                            return 0;
                    }
                    else { //正序
                        if(Double.parseDouble(a)>Double.parseDouble(b))
                            return 1;
                        else if(Double.parseDouble(a)<Double.parseDouble(b))
                            return -1;
                        else
                            return 0;
                    }
                } catch (NoSuchMethodException e) {
                    System.out.println(e);
                } catch (IllegalAccessException e) {
                    System.out.println(e);
                } catch (InvocationTargetException e) {
                    System.out.println(e);
                }
                return 0;
            }
        });
    }

    public void sort(List list,final String method,final String sort,final String type) {//method 比较的GET方法名称，sort（null为正序，“desc”为倒叙，type（null为double型，“Str”为String型））
        Collections.sort(list,new Comparator<T>(){
            public int compare(Object ObjectA,Object ObjectB) {
                try {
                    Method MethodA = ((T) ObjectA).getClass().getMethod(method, null);
                    Method MethodB = ((T) ObjectB).getClass().getMethod(method, null);
                    String a = MethodA.invoke(((T) ObjectA), null).toString();
                    String b = MethodB.invoke(((T) ObjectB), null).toString();
                    if ("desc".equals(sort)) { //倒序
                        if("Str".equals(type))
                            return b.compareTo(a);
                        else{
                            if(Double.parseDouble(b)>Double.parseDouble(a))
                                return 1;
                            else if(Double.parseDouble(b)<Double.parseDouble(a))
                                return -1;
                            else
                                return 0;
                        }

                    }
                    else { //正序
                        if("String".equals(type))
                            return a.compareTo(b);
                        else{
                            if(Double.parseDouble(a)>Double.parseDouble(b))
                                return 1;
                            else if(Double.parseDouble(a)<Double.parseDouble(b))
                                return -1;
                            else
                                return 0;
                        }
                    }
                } catch (NoSuchMethodException e) {
                    System.out.println(e);
                } catch (IllegalAccessException e) {
                    System.out.println(e);
                } catch (InvocationTargetException e) {
                    System.out.println(e);
                }
                return 0;
            }
        });
    }
}
