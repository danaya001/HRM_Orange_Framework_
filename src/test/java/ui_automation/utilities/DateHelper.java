package ui_automation.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper {
    public static String getTodaysDate(String format) {
        Date todaysDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        String getTodayDate = formatter.format(todaysDate).toString();
        return getTodayDate;
    }

}
