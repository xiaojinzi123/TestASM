package com.xiaojinzi;

import java.lang.reflect.Method;

public class CheckTest {

    public static void main(String[] args) {
        try {
            Class<?> clazz = Class.forName("com.xiaojinzi.ApiUtil");
            String name = clazz.getName();
            System.out.println("name = " + name);
            Method getApiMethod = clazz.getMethod("getApi", String.class);
            Object apiObject = getApiMethod.invoke(null,"user");
            System.out.println("apiObject = " + apiObject);
            System.out.println("生成的类验证成功");
        } catch (Exception e) {
            System.out.println("生成的类有问题");
        }
    }

}
