package com.uams;

import com.uams.util.DBUtil;
import com.uams.util.PasswordUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/signup.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String role = "Employee"; // default

        if (username == null || username.isBlank() || password == null || password.isBlank()) {
            req.setAttribute("error", "Username and password required.");
            req.getRequestDispatcher("/signup.jsp").forward(req, resp);
            return;
        }

        String hashed = PasswordUtil.hash(password);

        String sql = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, hashed);
            ps.setString(3, role);
            ps.executeUpdate();
            resp.sendRedirect(req.getContextPath() + "/login");
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("error", "Error creating user. Maybe username already exists.");
            req.getRequestDispatcher("/signup.jsp").forward(req, resp);
        }
    }
}
