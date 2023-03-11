/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.student;

import controller.authentication.BaseRequiredAuthenticationController;
import dal.DBContext;
import dal.DeptDBContext;
import dal.StudentDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.ArrayList;
import model.Department;
import model.Student;
import model.User;

/**
 *
 * @author sonnt
 */
public class UpdateController extends BaseRequiredAuthenticationController {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response,User user)
    throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        StudentDBContext stuDB = new StudentDBContext();
        Student student = stuDB.get(id);
        request.setAttribute("student", student);
        
        DBContext<Department> db = new DeptDBContext();
        ArrayList<Department> depts = db.all();
        request.setAttribute("depts", depts);
        
        request.getRequestDispatcher("../view/student/update.jsp").forward(request, response);
        
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response,User user)
    throws ServletException, IOException {
        String raw_id = request.getParameter("id");
        String raw_name = request.getParameter("name");
        String raw_gender = request.getParameter("gender");
        String raw_dob = request.getParameter("dob");
        String raw_did = request.getParameter("did");
        
        //validate data here
        
        Student s = new Student();
        Department d = new Department();
        d.setDid(Integer.parseInt(raw_did));
        s.setDept(d);
        s.setSid(Integer.parseInt(raw_id));
        s.setSname(raw_name);
        s.setGender(raw_gender.equals("male"));
        s.setDob(Date.valueOf(raw_dob));
        
        StudentDBContext db = new StudentDBContext();
        db.update(s);
        response.sendRedirect("list");
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
