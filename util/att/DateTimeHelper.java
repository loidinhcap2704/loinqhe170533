/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util.att;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author sonnt
 */
public class DateTimeHelper {

    public static ArrayList<java.sql.Date> getListDates(java.sql.Date from, java.sql.Date to) {
        ArrayList<java.sql.Date> dates = new ArrayList<>();
        java.sql.Date loop = from;
        while (loop.compareTo(to) <= 0) {
            dates.add(loop);
            java.util.Date d = convertSqlToUtilDate(loop);
            d = addDays(d, 1);
            d = keepOnlyDatePart(d);
            loop = convertUtilToSqlDate(d);
        }
        return dates;
    }

    public static java.util.Date addDays(java.util.Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return calendar.getTime();
    }

    public static java.sql.Date convertUtilToSqlDate(java.util.Date date) {
        return new java.sql.Date(date.getTime());
    }

    public static java.util.Date convertSqlToUtilDate(java.sql.Date sqlDate) {
        return new java.util.Date(sqlDate.getTime());
    }

    public static java.util.Date keepOnlyDatePart(java.util.Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static LocalDate getMondayOfWeek(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        int daysUntilMonday = DayOfWeek.MONDAY.getValue() - dayOfWeek.getValue();
        if (daysUntilMonday > 0) {
            daysUntilMonday -= 7;
        }
        return date.plusDays(daysUntilMonday);
    }

    public static List<LocalDate> getAllMondaysOfYear(LocalDate monday) {
        List<LocalDate> mondays = new ArrayList<>();
        LocalDate date = LocalDate.of(monday.getYear(), 1, 1);
        while (date.getYear() == monday.getYear()) {
            if (date.getDayOfWeek() == DayOfWeek.MONDAY) {
                mondays.add(date);
            }
            date = date.plusDays(1);
        }
        return mondays;
    }

}
