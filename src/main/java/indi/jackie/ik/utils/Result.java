package indi.jackie.ik.utils;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jackie chen
 * @create 2017/6/26
 * @description 结果工具类
 */
public class Result {

    /**
     * 正确结果
     *
     * @param json 正确数据
     * @return map string
     */
    public static String successMessage(String json) {
        Map<String, String> result = new HashMap<String, String>();
        result.put("flag", "true");
        result.put("data", json);
        return new Gson().toJson(result);
    }

    /**
     * 错误结果
     *
     * @param errorMessage 错误描述
     * @param json 错误数据
     * @return map string
     */
    public static String errorMessage(String errorMessage, String json) {
        Map<String, String> result = new HashMap<String, String>();
        result.put("flag", "true");
        result.put("message", errorMessage);
        result.put("data", json);
        return new Gson().toJson(result);
    }
}
