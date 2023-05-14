package org.douban.util;

import io.javalin.http.Context;

public class ParamUtil {
    public static int queryIntParamWithDefault(Context ctx, String param, int defaultValue) {
        try {
            String str = ctx.queryParam(param);
            if (str == null) {
                return defaultValue;
            }
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            ctx.result("Invalid Input!").status(400);
            return -1;
        }
    }
}
