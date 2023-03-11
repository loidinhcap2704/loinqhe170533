/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.forStudent;

import controller.authentication.BaseRequiredAuthenticationController;
import dal.StudentAttendDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import model.StudentAttend;
import model.User;

/**
 *
 * @author nguye
 */
public class AttendReportCourseController extends BaseRequiredAuthenticationController {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, User user)
            throws ServletException, IOException {
        StudentAttendDBContext sadb = new StudentAttendDBContext();
        ArrayList<StudentAttend> courseList = sadb.chooseCourseToSeeAttendReport(user.getUsername());
        request.setAttribute("courseList", courseList);
        int cid = Integer.parseInt(request.getParameter("cid"));
        StudentAttendDBContext db = new StudentAttendDBContext();
        ArrayList<StudentAttend> list = db.getAttendReport(user.getUsername(), cid);
        Date date = new Date();
        java.sql.Date date1 = new java.sql.Date(date.getTime());
        request.setAttribute("date", date1);
        request.setAttribute("list", list);
        request.getRequestDispatcher("../view/forStudent/AttendReport.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, User user)
            throws ServletException, IOException {
        processRequest(request, response, user);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
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
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
