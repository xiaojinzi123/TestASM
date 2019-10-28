package com.xiaojinzi;

public class TestWrite {

    public static Api getApi(String name) {
        if ("user".equals(name)) {
            return new UserApi();
        }
        if ("music".equals(name)) {
            return new MusicApi();
        }
        if ("self".equals(name)) {
            return new SelfApi();
        }
        return null;
    }

}
