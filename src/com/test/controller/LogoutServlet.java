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
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutServlet
 */
// @WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.setContentType("text/html");
        final PrintWriter out = response.getWriter();

        request.getRequestDispatcher("index.jsp")
            .include(request, response);

        final HttpSession session = request.getSession();
        session.invalidate();

        // if (session == null || session.getAttribute("userid") == null) {
        // response.sendRedirect("login.jsp"); // No logged-in user found, so redirect to login page.
        // }
        // else {
        // response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        // response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        // response.setDateHeader("Expires", 0);
        // // chain.doFilter(req, res);
        // }

        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

        out.print("You are successfully logged out!");

        out.close();

        // request.getRequestDispatcher("index.jsp")
        // .forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
