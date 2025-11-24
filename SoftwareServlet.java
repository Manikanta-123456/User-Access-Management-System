package com.uams;

import com.uams.util.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/software")
public class SoftwareServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Only Admin should access this; simple check:
        HttpSession session = req.getSession(false);
        if (session == null || !"Admin".equals(session.getAttribute("role"))) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Only Admin allowed");
            return;
        }

        String name = req.getParameter("name");
        String desc = req.getParameter("description");
        String[] accessLevels = req.getParameterValues("accessLevels"); // checkboxes

        String combined = "";
        if (accessLevels != null) {
            combined = String.join(",", accessLevels);
        }

        String sql = "INSERT INTO software (name, description, access_levels) VALUES (?, ?, ?)";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, desc);
            ps.setString(3, combined);
            ps.executeUpdate();
            resp.sendRedirect(req.getContextPath() + "/createSoftware.jsp?success=1");
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("error", "Error adding software");
            req.getRequestDispatcher("/createSoftware.jsp").forward(req, resp);
        }
    }
}
