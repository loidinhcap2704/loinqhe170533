/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.att.student;

import controller.authentication.BaseRequiredAuthenticatedController;
import dal.att.AttendanceDBContext;
import dal.att.StudentDBContex;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import model.User;
import model.att.Attendance;
import model.att.Group;

/**
 *
 * @author nguye
 */
public class AttendReportController extends BaseRequiredAuthenticatedController{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        AttendanceDBContext attdb = new AttendanceDBContext();
        StudentDBContex db = new StudentDBContex();
        int sid = db.getStuId(user.getUserid());
        int gid = Integer.parseInt(req.getParameter("gid"));
        ArrayList<Attendance> atts = attdb.getAttendReport(sid, gid);
        req.setAttribute("atts", atts);
        req.getRequestDispatcher("../view/att/student/attendReport.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        AttendanceDBContext attdb = new AttendanceDBContext();
        StudentDBContex db = new StudentDBContex();
        int sid = db.getStuId(user.getUserid());
        StudentDBContex db2 = new StudentDBContex();
        ArrayList<Group> groups = db2.getCouAttRep(sid);
        int gid;
        try{
            gid = Integer.parseInt(req.getParameter("gid"));
        }catch(Exception e){
            gid = groups.get(0).getId();
        }
        req.setAttribute("gid", gid);
        req.setAttribute("groups", groups);
        ArrayList<Attendance> atts = attdb.getAttendReport(sid, gid);
        req.setAttribute("atts", atts);
        req.getRequestDispatcher("../view/att/student/attendReport.jsp").forward(req, resp);
    }
    
    
}
