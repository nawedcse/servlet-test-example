/*
 *
 */
package com.test.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.model.UserPojo;
import com.test.validator.UserLoginValidate;

public class LoginControllerServlet extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = -6289691916293971110L;

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {

        final PrintWriter out = resp.getWriter();
        try {
            final String uid = req.getParameter("userid");
            final int id = Integer.parseInt(uid);
            final String password = req.getParameter("password");

            final UserPojo pObject = new UserPojo();
            pObject.setId(id);
            pObject.setPass(password);

            List<UserPojo> list = new ArrayList<>();
            list = UserLoginValidate.getUsers(id, password);

            final HttpSession session = req.getSession();
            session.setAttribute("userid", uid);

            if (!(list.isEmpty())) {
                out.print("<h1 align='center'>Congrats!You've SuccessFully Logged In</h1>");
                out.print("<table align ='center' border='1' cellspacing='5' cellpadding='5'><tr><th>ID</th><th>NAME</th><th>Password</th><th>Email</th></tr>");
                for (final UserPojo i : list) {
                    // printing all the values in the list
                    out.print("<tr><td>" + i.getId() + "</td>");
                    out.print("<td>" + i.getUsername() + "</td>");
                    out.print("<td>" + i.getPass() + "</td>");
                    out.print("<td>" + i.getEmail() + "</td></tr>");
                }
                out.print("</table>");

                req.getRequestDispatcher("logout.jsp")
                    .include(req, resp);

                req.getRequestDispatcher("crud.jsp")
                    .include(req, resp);
            }
            else {
                req.getRequestDispatcher("login.jsp")
                    .include(req, resp);
                out.print("<p align='center'>User Does Not Exist! Please Register");
                out.print("<a href='index.jsp'>Register Here</a></p>");
            }
        }
        catch (final Exception e) {
            req.getRequestDispatcher("login.jsp")
                .include(req, resp);
            out.print("<p>Please Enter Valid Details To Login</p>");
        }

    }

}
