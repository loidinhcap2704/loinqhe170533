/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.att.student;

import dal.att.AttendanceDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import model.att.Attendance;

/**
 *
 * @author nguye
 */
public class AttendReportController extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AttendanceDBContext attdb = new AttendanceDBContext();
        int sid = Integer.parseInt(req.getParameter("sid"));
        int gid = Integer.parseInt(req.getParameter("gid"));
        ArrayList<Attendance> atts = attdb.getAttendReport(sid, gid);
        req.setAttribute("atts", atts);
        req.getRequestDispatcher("../view/att/student/attendReport.jsp").forward(req, resp);
    }
    
    
}
