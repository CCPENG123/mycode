package cn.ccp.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    /**
     * 生成指定格式的时间
     * @param reg 时间格式 eg：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String formactDate(String reg){
        DateFormat format = new SimpleDateFormat(reg);
        Date date = new Date();
        return format.format(date);
    }
}
