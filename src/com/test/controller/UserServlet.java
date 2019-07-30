/*
 * 
 */
package com.test.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.dao.UserDAO;
import com.test.model.User;

@WebServlet("/")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;

    @Override
    public void init() {
        this.userDAO = new UserDAO();
    }

    @Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final String action = request.getServletPath();

        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertUser(request, response);
                    break;
                case "/delete":
                    deleteUser(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateUser(request, response);
                    break;
                default:
                    listUser(request, response);
                    break;
            }
        }
        catch (final SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listUser(final HttpServletRequest request, final HttpServletResponse response) throws SQLException, IOException, ServletException {
        final List<User> listUser = this.userDAO.selectAllUsers();
        request.setAttribute("listUser", listUser);
        final RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(final HttpServletRequest request, final HttpServletResponse response) throws SQLException, ServletException, IOException {
        final int id = Integer.parseInt(request.getParameter("id"));
        final User existingUser = this.userDAO.selectUser(id);
        final RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        request.setAttribute("user", existingUser);
        dispatcher.forward(request, response);

    }

    private void insertUser(final HttpServletRequest request, final HttpServletResponse response) throws SQLException, IOException {
        final String name = request.getParameter("name");
        final String email = request.getParameter("email");
        final String country = request.getParameter("country");
        final User newUser = new User(name, email, country);
        this.userDAO.insertUser(newUser);
        response.sendRedirect("list");
    }

    private void updateUser(final HttpServletRequest request, final HttpServletResponse response) throws SQLException, IOException {
        final int id = Integer.parseInt(request.getParameter("id"));
        final String name = request.getParameter("name");
        final String email = request.getParameter("email");
        final String country = request.getParameter("country");

        final User book = new User(id, name, email, country);
        this.userDAO.updateUser(book);
        response.sendRedirect("list");
    }

    private void deleteUser(final HttpServletRequest request, final HttpServletResponse response) throws SQLException, IOException {
        final int id = Integer.parseInt(request.getParameter("id"));
        this.userDAO.deleteUser(id);
        response.sendRedirect("list");

    }

}
