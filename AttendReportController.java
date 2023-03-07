/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.forStudent;

import controller.authentication.BaseRequiredAuthenticationController;
import dal.StudentAttendDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import model.StudentAttend;
import model.User;

/**
 *
 * @author nguye
 */
public class AttendReportController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StudentAttendDBContext db = new StudentAttendDBContext();
        ArrayList<StudentAttend> list = db.getAttendReport("loinq101", 4);
        if (list.size() == 0) {
            resp.sendRedirect("../index.html");
        } else {
            req.setAttribute("list", list);
            req.getRequestDispatcher("../view/forStudent/AttendReport.jsp").forward(req, resp);
        }
    }
}
