package com.uams;

import com.uams.util.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.*;

@WebServlet("/approval")
public class ApprovalServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Only Manager can view pending requests
        HttpSession session = req.getSession(false);
        if (session == null || !"Manager".equals(session.getAttribute("role"))) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Only Manager allowed");
            return;
        }

        String sql =
            "SELECT r.id, u.username as employee, s.name as software, r.access_type, r.reason, r.status " +
            "FROM requests r " +
            "JOIN users u ON r.user_id = u.id " +
            "JOIN software s ON r.software_id = s.id " +
            "WHERE r.status = 'Pending' ORDER BY r.created_at";

        try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            StringBuilder table = new StringBuilder();
            while (rs.next()) {
                int id = rs.getInt("id");
                String emp = rs.getString("employee");
                String sw = rs.getString("software");
                String at = rs.getString("access_type");
                String reason = rs.getString("reason");
                table.append("<tr>")
                     .append("<td>").append(id).append("</td>")
                     .append("<td>").append(emp).append("</td>")
                     .append("<td>").append(sw).append("</td>")
                     .append("<td>").append(at).append("</td>")
                     .append("<td>").append(reason == null ? "" : reason).append("</td>")
                     .append("<td>")
                     .append("<form method='post' style='display:inline'>")
                     .append("<input type='hidden' name='requestId' value='").append(id).append("'/>")
                     .append("<button name='action' value='approve'>Approve</button>")
                     .append("</form>")
                     .append("<form method='post' style='display:inline;margin-left:5px'>")
                     .append("<input type='hidden' name='requestId' value='").append(id).append("'/>")
                     .append("<button name='action' value='reject'>Reject</button>")
                     .append("</form>")
                     .append("</td>")
                     .append("</tr>");
            }
            req.setAttribute("pendingTable", table.toString());
            req.getRequestDispatcher("/pendingRequests.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("error", "Error loading pending requests");
            req.getRequestDispatcher("/pendingRequests.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || !"Manager".equals(session.getAttribute("role"))) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Only Manager allowed");
            return;
        }

        String action = req.getParameter("action");
        String requestIdStr = req.getParameter("requestId");
        if (action == null || requestIdStr == null) {
            resp.sendRedirect(req.getContextPath() + "/pendingRequests.jsp");
            return;
        }

        int requestId = Integer.parseInt(requestIdStr);
        String newStatus = "Rejected";
        if ("approve".equals(action)) newStatus = "Approved";

        String sql = "UPDATE requests SET status = ? WHERE id = ?";
        try (Connection conn = DBUtil.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, newStatus);
            ps.setInt(2, requestId);
            ps.executeUpdate();
            resp.sendRedirect(req.getContextPath() + "/pendingRequests.jsp?updated=1");
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("error", "Error updating request");
            doGet(req, resp);
        }
    }
}
