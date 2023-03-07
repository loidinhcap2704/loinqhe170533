/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.forStudent;

import controller.authentication.BaseRequiredAuthenticationController;
import dal.StudentTimetableDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import model.StudentAttend;
import model.User;

/**
 *
 * @author nguye
 */
public class TimetableController extends BaseRequiredAuthenticationController{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        Date date = new Date();
        Date[] weekday = WeekdayConverter.getWeekday(date);
        java.sql.Date start = new java.sql.Date(weekday[0].getTime());
        java.sql.Date end = new java.sql.Date(weekday[6].getTime());
        
        StudentTimetableDBContext db = new StudentTimetableDBContext();
        ArrayList<StudentAttend> timetable = db.getWeek(user.getUsername(),start , end);
        
        int[] slot = new int[]{1,2,3,4};
        int[] week = new int[]{1,2,3,4,5,6,7};
        
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
        
        StudentTimetableDBContext db = new StudentTimetableDBContext();
        ArrayList<StudentAttend> timetable = db.getWeek(user.getUsername(),start , end);
        
        int[] slot = new int[]{1,2,3,4};
        int[] week = new int[]{1,2,3,4,5,6,7};
        
        req.setAttribute("slot", slot);
        req.setAttribute("week", week);
        req.setAttribute("weekday", weekday);
        req.setAttribute("timetable", timetable);

        req.getRequestDispatcher("../view/forStudent/Timetable.jsp").forward(req, resp);
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
        return new java.util.Date[]{monday,tuesday,wednesday,thursday,friday, saturday, sunday};
    }
}