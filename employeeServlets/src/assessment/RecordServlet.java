package assessment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RecordServlet")
public class RecordServlet extends HttpServlet {
	/**
	 * 
	 */
	StudentDAL sd = new StudentDAL();
	private static final long serialVersionUID = 1L;
	private List<Student> students = new ArrayList<>();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");

		if (action == null) {
			response.sendRedirect("index.jsp"); // Redirect to the main page if no action is specified
		} else {
			switch (action) {
			case "create":
				sendCreateForm(response);
				break;
			case "read":
				readStudents(request, response);
				break;
			case "update":
				sendUpdateForm(request, response);
				break;
			case "delete":
				sendDeleteForm(request, response);
				break;
			default:
				response.sendRedirect("index.jsp"); // Redirect to the main page for unknown actions
				break;
			}
		}
	}

	// Send the form for creating a new student
	private void sendCreateForm(HttpServletResponse response) throws IOException {
		String htmlResponse = "<h2>Create Student</h2>" + "<form action='ActionServlet' method='post'>"
				+ "  Name: <input type='text' name='name'><br><br>" + "  Age: <input type='number' name='age'><br><br>"
				+ "  Gender: <input type='text' name='gender'><br><br>"
				+ "  DOB: <input type='text' name='dob'><br><br>" + "  State: <input type='text' name='state'><br><br>"
				+ "  Number: <input type='text' name='number'><br><br>"
				+ "  <input type='hidden' name='action' value='create'>"
				+ "  <input type='submit' id='createFinal' onClick='insert()' value='Create'>" + "</form>";

		response.setContentType("text/html");
		response.getWriter().write(htmlResponse);
	}

	// Send the form for updating a student
	private void sendUpdateForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int index = Integer.parseInt(request.getParameter("index"));

		// Check if the index is valid
		if (index >= 0 && index < students.size()) {
			Student student = students.get(index);

			String htmlResponse = "<h2>Update Student</h2>" + "<form action='RecordServlet' method='post'>"
					+ "  Name: <input type='text' name='name' value='" + student.getName() + "'><br>"
					+ "  Age: <input type='number' name='age' value='" + student.getAge() + "'><br>"
					+ "  Gender: <input type='text' name='gender' value='" + student.getGender() + "'><br>"
					+ "  DOB: <input type='text' name='dob' value='" + student.getDob() + "'><br>"
					+ "  State: <input type='text' name='state' value='" + student.getState() + "'><br>"
					+ "  Number: <input type='text' name='number' value='" + student.getNumber() + "'><br>"
					+ "  <input type='hidden' name='index' value='" + index + "'>"
					+ "  <input type='hidden' name='action' value='update'>" + "  <input type='submit' value='Update'>"
					+ "</form>";

			response.setContentType("text/html");
			response.getWriter().write(htmlResponse);
		} else {
			response.sendRedirect("index.jsp"); // Redirect to the main page if the index is invalid
		}
	}

	private void readStudents(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Prepare the HTML response
		StringBuilder htmlResponse = new StringBuilder();
		htmlResponse.append("<table>");
		htmlResponse
				.append("<tr><th>Name</th><th>Age</th><th>Gender</th><th>DOB</th><th>State</th><th>Number</th></tr>");

		// Iterate through the students and add them to the HTML response
		for (Student student : students) {
			htmlResponse.append("<tr>");
			htmlResponse.append("<td>").append(student.getName()).append("</td>");
			htmlResponse.append("<td>").append(student.getAge()).append("</td>");
			htmlResponse.append("<td>").append(student.getGender()).append("</td>");
			htmlResponse.append("<td>").append(student.getDob()).append("</td>");
			htmlResponse.append("<td>").append(student.getState()).append("</td>");
			htmlResponse.append("<td>").append(student.getNumber()).append("</td>");
			htmlResponse.append("</tr>");
		}

		htmlResponse.append("</table>");

		response.setContentType("text/html");
		response.getWriter().write(htmlResponse.toString());
	}

	// Send the form for deleting a student
	private void sendDeleteForm(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int index = Integer.parseInt(request.getParameter("index"));

		// Check if the index is valid
		if (index >= 0 && index < students.size()) {
			Student student = students.get(index);

			String htmlResponse = "<h2>Delete Student</h2>"
					+ "<p>Are you sure you want to delete the following student?</p>" + "<p><strong>Name:</strong> "
					+ student.getName() + "</p>" + "<p><strong>Age:</strong> " + student.getAge() + "</p>"
					+ "<p><strong>Gender:</strong> " + student.getGender() + "</p>" + "<p><strong>DOB:</strong> "
					+ student.getDob() + "</p>" + "<p><strong>State:</strong> " + student.getState() + "</p>"
					+ "<p><strong>Number:</strong> " + student.getNumber() + "</p>"
					+ "<form action='RecordServlet' method='post'>" + "  <input type='hidden' name='index' value='"
					+ index + "'>" + "  <input type='hidden' name='action' value='delete'>"
					+ "  <input type='submit' value='Delete'>" + "</form>";

			response.setContentType("text/html");
			response.getWriter().write(htmlResponse);
		} else {
			response.sendRedirect("index.jsp"); // Redirect to the main page if the index is invalid
		}
	}
}
