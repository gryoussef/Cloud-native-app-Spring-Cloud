package ma.s.gcm.utils;

import
        java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;


public class Utils {
    private static String DATE_FORMAT = "yyyy-MM-dd";

     public  static Date toDate(String stringDate) throws ParseException {
         return toDate(stringDate, DATE_FORMAT);
     }

    public  static Date toDate(String stringDate, String dateFormat) throws ParseException {
        return new SimpleDateFormat(DATE_FORMAT).parse(stringDate);
    }

    public static LocalDate convertToLocalDate(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static Date convertToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

}
