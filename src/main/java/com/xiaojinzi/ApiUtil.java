package com.xiaojinzi;

public class ApiUtil {
    public ApiUtil() {
    }

    public static Api getApi(String var0) {
        if ("user".equals(var0)) {
            return new UserApi();
        } else if ("self".equals(var0)) {
            return new SelfApi();
        } else {
            return "music".equals(var0) ? new MusicApi() : null;
        }
    }
}