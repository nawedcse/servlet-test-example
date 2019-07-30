/*
 *
 */
package com.test.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.dao.UserDAO;
import com.test.model.UserPojo;

public class RegistrationControllerServlet extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = -1275583720932344916L;

    private UserDAO userDAO;

    @Override
    public void init() {
        this.userDAO = new UserDAO();
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        final PrintWriter out = resp.getWriter();

        try {
            final String userid = req.getParameter("userid");
            final int id = Integer.parseInt(userid);
            final String username = req.getParameter("username"); // getting all the values from user
            final String pass = req.getParameter("password");
            final String email = req.getParameter("email");
            // getting all the values from index.jsp

            final UserPojo pObject = new UserPojo();
            pObject.setId(id);
            pObject.setUsername(username); // setting up the received values from index.jsp to setters and getters
            pObject.setPass(pass);
            pObject.setEmail(email);

            final int status = this.userDAO.save(pObject);

            if (status > 0) {
                out.print("<h2 align='center'>SuccessFully Registered</h2>"); // if successfully executes save method
                out.print("<a align='center'href='login.jsp'>Login Here</a>");
            }
            else {
                req.getRequestDispatcher("index.jsp")
                    .include(req, resp);
                out.print("<p>User Already Exists with Same User Details</p>");
            }
        }
        catch (final Exception e) {
            req.getRequestDispatcher("index.jsp")
                .include(req, resp);
            out.print("<p>Enter Valid Details to Register</p>");
        }
    }
}
