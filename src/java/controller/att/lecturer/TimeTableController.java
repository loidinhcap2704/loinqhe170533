/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.att.lecturer;

import controller.authentication.BaseRequiredAuthenticatedController;
import dal.att.LecturerDBContext;
import dal.att.TimeSlotDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.User;
import model.att.Session;
import model.att.TimeSlot;
import util.att.DateTimeHelper;

/**
 *
 * @author sonnt
 */
public class TimeTableController extends BaseRequiredAuthenticatedController {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @param user
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, User user)
    throws ServletException, IOException {
        LecturerDBContext lecdb = new LecturerDBContext();
        int lid = lecdb.getLid(user.getUserid());
        LocalDate thismonday = LocalDate.parse(request.getParameter("from"));
        System.out.println(thismonday);
        Date from = Date.valueOf(request.getParameter("from"));
        Date to = Date.valueOf(thismonday.with(DayOfWeek.SUNDAY));
        ArrayList<Date> dates = DateTimeHelper.getListDates(from, to);
        TimeSlotDBContext dbSlot = new TimeSlotDBContext();
        ArrayList<TimeSlot> slots = dbSlot.all();
        LecturerDBContext lecDb = new LecturerDBContext();
        ArrayList<Session> sessions = lecDb.getSessions(lid);
        
        //get all week of the year
        List<LocalDate> mondays = DateTimeHelper.getAllMondaysOfYear(thismonday);
        List<LocalDate> sundays = new ArrayList<>();
        for(LocalDate monday: mondays){
            LocalDate sunday = monday.plusDays(6);
            sundays.add(sunday);
        }
        
        request.setAttribute("slots", slots);
        request.setAttribute("dates", dates);
        request.setAttribute("sessions", sessions);
        request.setAttribute("thismonday", thismonday);
        request.setAttribute("mondays", mondays);
        request.setAttribute("sundays", sundays);
        request.getRequestDispatcher("../view/att/lecturer/timetable.jsp").forward(request, response);
        
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @param user
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, User user)
    throws ServletException, IOException {
        LecturerDBContext lecdb = new LecturerDBContext();
        int lid = lecdb.getLid(user.getUserid());
        LocalDate ld = LocalDate.now();
        Date from = Date.valueOf(ld.with(DayOfWeek.MONDAY));
        Date to = Date.valueOf(ld.with(DayOfWeek.SUNDAY));
        ArrayList<Date> dates = DateTimeHelper.getListDates(from, to);
        TimeSlotDBContext dbSlot = new TimeSlotDBContext();
        ArrayList<TimeSlot> slots = dbSlot.all();
        LecturerDBContext lecDb = new LecturerDBContext();
        ArrayList<Session> sessions = lecDb.getSessions(lid);
        
        //take week of the year
        LocalDate now = LocalDate.now();
        LocalDate thismonday = DateTimeHelper.getMondayOfWeek(now);
        List<LocalDate> mondays = DateTimeHelper.getAllMondaysOfYear(thismonday);
        List<LocalDate> sundays = new ArrayList<>();
        for(LocalDate monday: mondays){
            LocalDate sunday = monday.plusDays(6);
            sundays.add(sunday);
        }
        
        request.setAttribute("slots", slots);
        request.setAttribute("dates", dates);
        request.setAttribute("sessions", sessions);
        request.setAttribute("thismonday", thismonday);
        request.setAttribute("mondays", mondays);
        request.setAttribute("sundays", sundays);
        request.getRequestDispatcher("../view/att/lecturer/timetable.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, User user)
    throws ServletException, IOException {
        processRequest(request, response, user);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
