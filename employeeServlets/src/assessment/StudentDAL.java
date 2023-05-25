package assessment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAL implements studentDAO {

	// SQL queries
	private static final String INSERT_QUERY = "INSERT INTO ak_student(name, age, gender, dob, state, number) VALUES (?,?,?,?,?,?)";
	private static final String SELECT_ALL_QUERY = "SELECT * FROM ak_student";

	private static final String UPDATE_QUERY = "UPDATE ak_student SET name = ?, age = ?, gender = ?, dob = ?, state = ?, number = ? WHERE name = ?";
	private static final String DELETE_QUERY = "DELETE FROM ak_student WHERE name = ?";

	public void addStudent(Student student) throws ClassNotFoundException {

		try {
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:postgresql://192.168.110.48:5432/plf_training?user=plf_training_admin&password=pff123");
			PreparedStatement statement = con.prepareStatement(INSERT_QUERY);

			statement.setString(1, student.getName());
			statement.setInt(2, student.getAge());
			statement.setString(3, student.getGender());
			statement.setString(4, student.getDob());
			statement.setString(5, student.getState());
			statement.setInt(6, student.getNumber());

			int rowsAffected = statement.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Student added successfully.");
			} else {
				System.out.println("Failed to add student.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Student> getAllStudents() throws ClassNotFoundException {
		List<Student> students = new ArrayList<>();
		try {
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:postgresql://192.168.110.48:5432/plf_training?user=plf_training_admin&password=pff123");
			PreparedStatement statement = con.prepareStatement(SELECT_ALL_QUERY);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				String name = resultSet.getString("name");
				int age = resultSet.getInt("age");
				String gender = resultSet.getString("gender");
				String dob = resultSet.getString("dob");
				String state = resultSet.getString("state");
				int number = resultSet.getInt("number");

				Student student = new Student(name, age, gender, dob, state, number);
				students.add(student);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return students;
	}

	public Student getStudentByName(String name) throws ClassNotFoundException {
		Student student = null;

		try {

			Class.forName("org.postgresql.Driver");

			Connection con = DriverManager.getConnection(
					"jdbc:postgresql://192.168.110.48:5432/plf_training?user=plf_training_admin&password=pff123");
			PreparedStatement statement = con.prepareStatement("SELECT * FROM ak_student WHERE name = ?");

			statement.setString(1, name);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {
				int age = resultSet.getInt("age");
				String gender = resultSet.getString("gender");
				String dob = resultSet.getString("dob");
				String state = resultSet.getString("state");
				int number = resultSet.getInt("number");

				student = new Student(name, age, gender, dob, state, number);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return student;
	}

	public void updateStudent(String name, Student updatedStudent) throws ClassNotFoundException {

		try {
			Class.forName("org.postgresql.Driver");

			Connection con = DriverManager.getConnection(
					"jdbc:postgresql://192.168.110.48:5432/plf_training?user=plf_training_admin&password=pff123");
			PreparedStatement statement = con.prepareStatement(UPDATE_QUERY);

			statement.setString(1, updatedStudent.getName());
			statement.setInt(2, updatedStudent.getAge());
			statement.setString(3, updatedStudent.getGender());
			statement.setString(4, updatedStudent.getDob());
			statement.setString(5, updatedStudent.getState());
			statement.setInt(6, updatedStudent.getNumber());
			statement.setString(7, name);

			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void deleteStudent(String name) throws ClassNotFoundException {

		try {
			Class.forName("org.postgresql.Driver");

			Connection con = DriverManager.getConnection(
					"jdbc:postgresql://192.168.110.48:5432/plf_training?user=plf_training_admin&password=pff123");
			PreparedStatement statement = con.prepareStatement("UPDATE_QUERY");

			statement.setString(1, name);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
