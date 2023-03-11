/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.forStudent;

import controller.authentication.BaseRequiredAuthenticationController;
import dal.StudentTimetableDBContext;
import dal.TimeSlotDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import model.StudentAttend;
import model.TimeSlot;
import model.User;

/**
 *
 * @author nguye
 */
public class TimetableController extends BaseRequiredAuthenticationController {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        String dateString = req.getParameter("date");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(dateString);
        } catch (ParseException ex) {
        }
        Date[] weekday = WeekdayConverter.getWeekday(date);
        java.sql.Date start = new java.sql.Date(weekday[0].getTime());
        java.sql.Date end = new java.sql.Date(weekday[6].getTime());

        //all monday of the year
        ArrayList<Date> monday = getAllMondaysInYear(weekday[0]);
        //all sunday
        ArrayList<Date> sunday = new ArrayList<>();
        for (Date d : monday) {
            Date sun = getNextSunday(d);
            sunday.add(sun);
        }
        //get index of monday
        int[] length = new int[monday.size()];
        for (int i = 0; i < monday.size(); i++) {
            length[i] = i;
        }

        StudentTimetableDBContext db = new StudentTimetableDBContext();
        ArrayList<StudentAttend> timetable = db.getWeek(user.getUsername(), start, end);

        TimeSlotDBContext slotdb = new TimeSlotDBContext();
        ArrayList<TimeSlot> slot = slotdb.all();
        int[] week = new int[]{1, 2, 3, 4, 5, 6, 7};

        req.setAttribute("get", date);
        req.setAttribute("monday", monday);
        req.setAttribute("sunday", sunday);
        req.setAttribute("length", length);
        req.setAttribute("slot", slot);
        req.setAttribute("week", week);
        req.setAttribute("weekday", weekday);
        req.setAttribute("timetable", timetable);

        req.getRequestDispatcher("../view/forStudent/Timetable.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        Date date = new Date();
        Date[] weekday = WeekdayConverter.getWeekday(date);
        java.sql.Date start = new java.sql.Date(weekday[0].getTime());
        java.sql.Date end = new java.sql.Date(weekday[6].getTime());

        //all monday of the year
        ArrayList<Date> monday = getAllMondaysInYear(weekday[0]);
        //all sunday
        ArrayList<Date> sunday = new ArrayList<>();
        for (Date d : monday) {
            Date sun = getNextSunday(d);
            sunday.add(sun);
        }
        //get index of monday
        int[] length = new int[monday.size()];
        for (int i = 0; i < monday.size(); i++) {
            length[i] = i;
        }
        StudentTimetableDBContext db = new StudentTimetableDBContext();
        ArrayList<StudentAttend> timetable = db.getWeek(user.getUsername(), start, end);

        TimeSlotDBContext slotdb = new TimeSlotDBContext();
        ArrayList<TimeSlot> slot = slotdb.all();
        int[] week = new int[]{1, 2, 3, 4, 5, 6, 7};

        
        req.setAttribute("get", weekday[0]);
        req.setAttribute("monday", monday);
        req.setAttribute("sunday", sunday);
        req.setAttribute("length", length);
        req.setAttribute("slot", slot);
        req.setAttribute("week", week);
        req.setAttribute("weekday", weekday);
        req.setAttribute("timetable", timetable);

        req.getRequestDispatcher("../view/forStudent/Timetable.jsp").forward(req, resp);
    }

    public static ArrayList<Date> getAllMondaysInYear(Date startingMonday) {
        ArrayList<Date> mondays = new ArrayList<>();

        // Convert the starting Monday to a LocalDate
        LocalDate startingMondayLocalDate = startingMonday.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        // Get the first day of the year and the first Monday on or after it
        LocalDate firstDayOfYear = LocalDate.of(startingMondayLocalDate.getYear(), 1, 1);
        LocalDate firstMondayOfYear = firstDayOfYear.with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY));

        // Loop through all the Mondays in the year and add them to the list
        LocalDate monday = firstMondayOfYear;
        while (monday.getYear() == startingMondayLocalDate.getYear()) {
            // Convert the LocalDate to a Date and add it to the list
            mondays.add(Date.from(monday.atStartOfDay(ZoneId.systemDefault()).toInstant()));
            monday = monday.plusWeeks(1);
        }

        return mondays;
    }

    public static Date getNextSunday(Date monday) {
        // convert java.util.Date to LocalDate
        LocalDate localMonday = monday.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        // calculate the date of the following Sunday
        LocalDate localSunday = localMonday.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));

        // convert LocalDate back to java.util.Date
        return Date.from(localSunday.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}

class DateConverter {

    public static java.util.Date sqlDateToUtilDate(Date sqlDate) {
        // Create a calendar object and set its time to the SQL date
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sqlDate);

        // Get the year, month, and day from the calendar object
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Create a java.util.Date object with only the year, month, and day
        java.util.Date utilDate = new java.util.Date(year - 1900, month, day);

        // Return the java.util.Date object
        return utilDate;
    }
}

class WeekdayConverter {

    public static java.util.Date[] getWeekday(java.util.Date date) {
        // Create a calendar object and set its time to the input date
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        // Calculate the Monday and Sunday of the week
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        calendar.add(Calendar.DATE, Calendar.MONDAY - dayOfWeek);
        java.util.Date monday = calendar.getTime();
        calendar.add(Calendar.DATE, 1);
        java.util.Date tuesday = calendar.getTime();
        calendar.add(Calendar.DATE, 1);
        java.util.Date wednesday = calendar.getTime();
        calendar.add(Calendar.DATE, 1);
        java.util.Date thursday = calendar.getTime();
        calendar.add(Calendar.DATE, 1);
        java.util.Date friday = calendar.getTime();
        calendar.add(Calendar.DATE, 1);
        java.util.Date saturday = calendar.getTime();
        calendar.add(Calendar.DATE, 1);
        java.util.Date sunday = calendar.getTime();
        // Return an array containing the Monday and Sunday
        return new java.util.Date[]{monday, tuesday, wednesday, thursday, friday, saturday, sunday};
    }
}
