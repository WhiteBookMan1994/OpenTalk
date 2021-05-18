package hutool;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author dingchenchen
 * @since 2021/3/25
 */
public class DateTest {
    private static ThreadLocal<SimpleDateFormat> SIMPLE_DATE_FORMAT = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));

    private static final String regex = "\\/|\\.|年|月|日";
    public static void main(String[] args) {
        String s1 = "2021-08-02";
        Date d1 = parseDate(s1);
        System.out.println(d1);
        String s2 = "2021-08-02";
        Date d2 = parseDate(s2);
        System.out.println(d2);
        System.out.println(DateUtil.between(d1,d2, DateUnit.DAY));
        System.out.println(DateUtil.beginOfDay(new Date()));
    }

    public static Date parseDate(String dateStr) {
        if (dateStr == null || dateStr.length() == 0) {
            return null;
        }
        Date date = null;
        try {
            date = SIMPLE_DATE_FORMAT.get().parse(dateStr.replaceAll(regex, "-"));
            return date;
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
