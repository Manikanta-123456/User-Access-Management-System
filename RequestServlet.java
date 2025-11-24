package com.uams;

import com.uams.util.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.*;

@WebServlet("/request")
public class RequestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // populate software dropdown
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT id, name FROM software ORDER BY name");
             ResultSet rs = ps.executeQuery()) {

            req.setAttribute("softwareList", rsToHtmlList(rs)); // small helper to create list (see below)
            req.getRequestDispatcher("/requestAccess.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("error", "DB error loading software.");
            req.getRequestDispatcher("/requestAccess.jsp").forward(req, resp);
        }
    }

    private String rsToHtmlList(ResultSet rs) throws SQLException {
        StringBuilder sb = new StringBuilder();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            sb.append("<option value=\"").append(id).append("\">").append(name).append("</option>");
        }
        return sb.toString();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Employee posts a request
        HttpSession session = req.getSession(false);
        if (session == null || !"Employee".equals(session.getAttribute("role"))) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Only Employee allowed");
            return;
        }

        int userId = (int) session.getAttribute("userId");
        String softwareIdStr = req.getParameter("softwareId");
        String accessType = req.getParameter("accessType");
        String reason = req.getParameter("reason");

        if (softwareIdStr == null || accessType == null) {
            req.setAttribute("error", "Select software and access type");
            doGet(req, resp);
            return;
        }

        int softwareId = Integer.parseInt(softwareIdStr);

        String sql = "INSERT INTO requests (user_id, software_id, access_type, reason, status) VALUES (?, ?, ?, ?, 'Pending')";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, softwareId);
            ps.setString(3, accessType);
            ps.setString(4, reason);
            ps.executeUpdate();
            resp.sendRedirect(req.getContextPath() + "/requestAccess.jsp?success=1");
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("error", "Error submitting request");
            doGet(req, resp);
        }
    }
}
