package com.ccm.base.utils;

/**
 * @Auther: Cassidy ccm
 * @Email: isCassidy@163.com
 * @Date: 2018/8/16 14:17
 * @Description:
 */
public class RegexUtil {

    public final static String REG_EMAIL = "^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$";

    public final static String REG_DATE = "[1-2][0-9]{3}[\\-](([0][1-9])|([1][0-2]))[\\-](([0][1-9])|([1-2][0-9])|([3][0-1]))";
    public final static String REG_HH24 = "(([0-1][0-9])|([2][0-3]))";//HH24
    public final static String REG_HH24_MI = REG_HH24 + "(:)" + "([0-5][0-9])";//HH24:MI
    public final static String REG_24_MI_SS = "("+"(([0-1][0-9])|([2][0-3]))" + "(:)" + "([0-5][0-9])" + "(:)" + "([0-5][0-9])"+")";

    
    public final static String REG_YYYY = "[1-2][0-9]{3}";//YYYY
    public final static String REG_YYYY_MM = "[1-2][0-9]{3}[\\-](([0][1-9])|([1][0-2]))";//YYYY-MM
    public final static String REG_YYYY_MM_DD = REG_DATE;//YYYY_MM_DD
    public final static String REG_YYYY_MM_DD_HH24 = REG_DATE + " " + REG_HH24;//YYYY-MM-DD HH24
    public final static String REG_YYYY_MM_DD_HH24_MI = REG_DATE + " " + REG_HH24_MI;//YYYY-MM-DD HH24:MI
    public final static String REG_YYYY_MM_DD_HH24_MI_SS = "(" + REG_YYYY_MM_DD + ")" + " " + REG_24_MI_SS;//YYYY-MM-DD HH24:MI:SS

    public static boolean isDateTime(String timeStr){
        if (timeStr == null){
            return false;
        }
        return timeStr.matches(REG_YYYY_MM_DD_HH24_MI_SS);
    }

}
