package com.uams;

import com.uams.util.DBUtil;
import com.uams.util.PasswordUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        String sql = "SELECT id, password, role FROM users WHERE username = ?";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String hashed = rs.getString("password");
                    String role = rs.getString("role");
                    int userId = rs.getInt("id");

                    if (PasswordUtil.check(password, hashed)) {
                        HttpSession session = req.getSession();
                        session.setAttribute("userId", userId);
                        session.setAttribute("username", username);
                        session.setAttribute("role", role);

                        // Redirect by role
                        switch (role) {
                            case "Admin":
                                resp.sendRedirect(req.getContextPath() + "/createSoftware.jsp");
                                return;
                            case "Manager":
                                resp.sendRedirect(req.getContextPath() + "/pendingRequests.jsp");
                                return;
                            default: // Employee
                                resp.sendRedirect(req.getContextPath() + "/requestAccess.jsp");
                                return;
                        }
                    } else {
                        req.setAttribute("error", "Invalid credentials.");
                        req.getRequestDispatcher("/login.jsp").forward(req, resp);
                    }
                } else {
                    req.setAttribute("error", "Invalid credentials.");
                    req.getRequestDispatcher("/login.jsp").forward(req, resp);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("error", "Database error.");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }
}
