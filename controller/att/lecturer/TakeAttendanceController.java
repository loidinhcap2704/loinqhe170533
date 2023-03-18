/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.att.lecturer;

import dal.att.AttendanceDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.att.Student;
import model.att.Attendance;

/**
 *
 * @author sonnt
 */
public class TakeAttendanceController extends HttpServlet {
   
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        AttendanceDBContext db = new AttendanceDBContext();
        ArrayList<Attendance> atts = db.getAttsBySessionID(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("atts", atts);
        request.getRequestDispatcher("../view/att/lecturer/att.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String[] sids = request.getParameterValues("sid");
        int sessionid = Integer.parseInt(request.getParameter("sessionid"));
        ArrayList<Attendance> atts = new ArrayList<>();
        for (String sid : sids) {
            Student s = new Student();
            s.setId(Integer.parseInt(sid));
            Attendance a = new Attendance();
            a.setId(Integer.parseInt(request.getParameter("aid"+s.getId())));
            a.setStudent(s);
            a.setStatus(request.getParameter("status"+sid).equals("present"));
            a.setDescription(request.getParameter("description"+sid));
            atts.add(a);
        }
        AttendanceDBContext db = new AttendanceDBContext();
        db.updateAtts(atts, sessionid);
        response.sendRedirect("takeattend?id="+sessionid);
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
