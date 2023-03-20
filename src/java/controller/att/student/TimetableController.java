/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.att.student;

import controller.authentication.BaseRequiredAuthenticatedController;
import dal.att.StudentDBContex;
import dal.att.TimeSlotDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.User;
import model.att.Attendance;
import model.att.TimeSlot;
import util.att.DateTimeHelper;

/**
 *
 * @author nguye
 */
public class TimetableController extends BaseRequiredAuthenticatedController{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        processRequest(req, resp, user);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        StudentDBContex db = new StudentDBContex();
        int sid = db.getStuId(user.getUserid());
        StudentDBContex studb = new StudentDBContex();
        LocalDate ld = LocalDate.now();
        Date from = Date.valueOf(ld.with(DayOfWeek.MONDAY));
        Date to = Date.valueOf(ld.with(DayOfWeek.SUNDAY));
        ArrayList<Date> dates = DateTimeHelper.getListDates(from, to);
        TimeSlotDBContext dbSlot = new TimeSlotDBContext();
        ArrayList<TimeSlot> slots = dbSlot.all();
        ArrayList<Attendance> timetable = studb.timetable(sid);
        
        //get all week of the year
        LocalDate now = LocalDate.now();
        LocalDate thismonday = DateTimeHelper.getMondayOfWeek(now);
        List<LocalDate> mondays = DateTimeHelper.getAllMondaysOfYear(thismonday);
        List<LocalDate> sundays = new ArrayList<>();
        for(LocalDate monday: mondays){
            LocalDate sunday = monday.plusDays(6);
            sundays.add(sunday);
        }
        
        req.setAttribute("slots", slots);
        req.setAttribute("dates", dates);
        req.setAttribute("timetable", timetable);
        req.setAttribute("thismonday", thismonday);
        req.setAttribute("mondays", mondays);
        req.setAttribute("sundays", sundays);
        req.getRequestDispatcher("../view/att/student/Timetable.jsp").forward(req, resp);
    }
    
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException{
        StudentDBContex db = new StudentDBContex();
        int sid = db.getStuId(user.getUserid());
        
        StudentDBContex studb = new StudentDBContex();
        LocalDate thismonday = LocalDate.parse(req.getParameter("from"));
        System.out.println(thismonday);
        Date from = Date.valueOf(req.getParameter("from"));
        Date to = Date.valueOf(thismonday.with(DayOfWeek.SUNDAY));
        ArrayList<Date> dates = DateTimeHelper.getListDates(from, to);
        TimeSlotDBContext dbSlot = new TimeSlotDBContext();
        ArrayList<TimeSlot> slots = dbSlot.all();
        ArrayList<Attendance> timetable = studb.timetable(sid);
        
        //get all week of the year
        List<LocalDate> mondays = DateTimeHelper.getAllMondaysOfYear(thismonday);
        List<LocalDate> sundays = new ArrayList<>();
        for(LocalDate monday: mondays){
            LocalDate sunday = monday.plusDays(6);
            sundays.add(sunday);
        }
        
        req.setAttribute("slots", slots);
        req.setAttribute("dates", dates);
        req.setAttribute("timetable", timetable);
        req.setAttribute("thismonday", thismonday);
        req.setAttribute("mondays", mondays);
        req.setAttribute("sundays", sundays);
        req.getRequestDispatcher("../view/att/student/Timetable.jsp").forward(req, resp);
    }
}
