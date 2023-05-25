package assessment;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/AuthenticateServlet")
public class AuthenticateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection con = null;

	public AuthenticateServlet() {
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(
					"jdbc:postgresql://192.168.110.48:5432/plf_training?user=plf_training_admin&password=pff123");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		String password = request.getParameter("pass");
		credModel cm = new credModel();
		cm.setPass(password);
		cm.setUser(username);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("SELECT * FROM ak_credentials WHERE usern = ? AND pass = ?");
			ps.setString(1, cm.getUser());
			ps.setString(2, cm.getPass());
			rs = ps.executeQuery();
			if (rs.next()) {
				HttpSession session = request.getSession();
				session.setAttribute("user", username);
				out.print("Login Successful");
				response.sendRedirect("index.jsp");
			} else {
				out.print("Login failed");
				response.sendRedirect("login.html");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Close ResultSet, PreparedStatement, and Connection
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
