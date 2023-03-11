/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.authentication;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import model.User;

/**
 *
 * @author sonnt
 */
public abstract class BaseRequiredAuthenticationController extends HttpServlet {

    private boolean isAuthenticated(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        return user != null;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(isAuthenticated(req))
        {
            //do business
            doPost(req, resp, (User) req.getSession().getAttribute("user"));
        }
        else
        {
            resp.getWriter().println("access denied!");
        }
    }
    
    protected abstract void doPost(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(isAuthenticated(req))
        {
            //do business
            doGet(req, resp, (User) req.getSession().getAttribute("user"));
        }
        else
        {
            resp.getWriter().println("access denied!");
        }
    }
    protected abstract void doGet(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException;

}
