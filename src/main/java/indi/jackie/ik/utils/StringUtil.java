package indi.jackie.ik.utils;

/**
 * @author jackie chen
 * @create 2016/7/17
 * @description String工具类
 */
public class StringUtil {

    /**
     * 判断字符串是否为空
     * @param target
     * @return true/false
     */
    public static boolean isEmpty(String target){
        if(null == target || "".equals(target)){
            return true;
        }
        return false;
    }

    /**
     * 去除两端空格
     * @param target
     * @return
     */
    public static String trimWhitespace(String target) {
        if(isEmpty(target)) {
            return target;
        } else {
            StringBuilder sb = new StringBuilder(target);

            while(sb.length() > 0 && Character.isWhitespace(sb.charAt(0))) {
                sb.deleteCharAt(0);
            }

            while(sb.length() > 0 && Character.isWhitespace(sb.charAt(sb.length() - 1))) {
                sb.deleteCharAt(sb.length() - 1);
            }

            return sb.toString();
        }
    }

    /**
     * 去除所有空格
     * @param target
     * @return
     */
    public static String trimAllWhitespace(String target) {
        if(isEmpty(target)) {
            return target;
        } else {
            StringBuilder sb = new StringBuilder(target);
            int index = 0;

            while(sb.length() > index) {
                if(Character.isWhitespace(sb.charAt(index))) {
                    sb.deleteCharAt(index);
                } else {
                    ++index;
                }
            }

            return sb.toString();
        }
    }

    /**
     * 解析string to boolean
     * @param value
     * @return
     */
    public static boolean parseBoolean(String value) {
        return value != null && value.equalsIgnoreCase("true");
    }
}
