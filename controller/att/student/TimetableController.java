/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.att.student;

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
import model.att.Attendance;
import model.att.TimeSlot;
import util.att.DateTimeHelper;

/**
 *
 * @author nguye
 */
public class TimetableController extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int sid = Integer.parseInt(req.getParameter("sid"));
        StudentDBContex studb = new StudentDBContex();
        LocalDate ld = LocalDate.now();
        Date from = Date.valueOf(ld.with(DayOfWeek.MONDAY));
        Date to = Date.valueOf(ld.with(DayOfWeek.SUNDAY));
        ArrayList<Date> dates = DateTimeHelper.getListDates(from, to);
        TimeSlotDBContext dbSlot = new TimeSlotDBContext();
        ArrayList<TimeSlot> slots = dbSlot.all();
        ArrayList<Attendance> timetable = studb.timetable(sid);
        
        req.setAttribute("slots", slots);
        req.setAttribute("dates", dates);
        req.setAttribute("timetable", timetable);
        req.getRequestDispatcher("../view/att/student/Timetable.jsp").forward(req, resp);
    }
    
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        int sid = Integer.parseInt(req.getParameter("sid"));
        StudentDBContex studb = new StudentDBContex();
        LocalDate ld = LocalDate.now();
        Date from = Date.valueOf(ld.with(DayOfWeek.MONDAY));
        Date to = Date.valueOf(ld.with(DayOfWeek.SUNDAY));
        ArrayList<Date> dates = DateTimeHelper.getListDates(from, to);
        TimeSlotDBContext dbSlot = new TimeSlotDBContext();
        ArrayList<TimeSlot> slots = dbSlot.all();
        ArrayList<Attendance> timetable = studb.timetable(sid);
        
        req.setAttribute("slots", slots);
        req.setAttribute("dates", dates);
        req.setAttribute("timetable", timetable);
        req.getRequestDispatcher("../view/att/student/Timetable.jsp").forward(req, resp);
    }
}
