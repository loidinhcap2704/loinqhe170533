/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.forLecturer;

import controller.authentication.BaseRequiredAuthenticationController;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import model.User;

/**
 *
 * @author nguye
 */
public class TimetableController extends BaseRequiredAuthenticationController{
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response,User user)
    throws ServletException, IOException {
        processRequest(request, response,user);
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
        processRequest(request, response,user);
    }
    
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException{
        
        req.getRequestDispatcher("../view/forLecturer/Timetable.jsp").forward(req, resp);
    }
}
