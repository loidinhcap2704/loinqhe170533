/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.forLecturer;

import controller.authentication.BaseRequiredAuthenticationController;
import dal.DBContext;
import dal.StudentDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import model.Student;
import model.User;

/**
 *
 * @author nguye
 */
public class AttendController extends BaseRequiredAuthenticationController{

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
        DBContext<Student> db = new StudentDBContext();
        ArrayList<Student> list1 = db.all();
        ArrayList<Student> list2 = new ArrayList<>();
        for(int i=0;i<29;i++){
            list2.add(list1.get(i));
        }
        list2.add(list1.get(100));
        req.setAttribute("students", list2);
        req.getRequestDispatcher("../view/forLecturer/Attend.jsp").forward(req, resp);
    }
}
