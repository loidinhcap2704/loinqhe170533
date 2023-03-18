/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.user;

import controller.authentication.BaseRequiredAuthenticatedController;
import dal.UserDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import model.Role;
import model.User;

/**
 *
 * @author nguye
 */
public class UserController extends BaseRequiredAuthenticatedController {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        UserDBContext userdb = new UserDBContext();
        user = userdb.get(user.getUsername(), "123456");
        boolean b = false;
        for (Role role : user.getRoles()) {
            if (req.getServletPath().equals(role.getUrl())) {
                b = true;
            }
        }
        if (b) {
            req.setAttribute("user", user);
            req.getRequestDispatcher("view/user.jsp").forward(req, resp);
        } else {
            resp.getWriter().print("BAKA");
        }
    }

}
