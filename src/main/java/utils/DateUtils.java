package utils;

import java.sql.Date;

public class DateUtils {
    public static Date javaToSqlDate(java.util.Date javaDate){
        return new Date(javaDate.getTime());
        
    }
}
