package assessment;

import java.util.List;

public interface studentDAO {
	public List<Student> getAllStudents() throws ClassNotFoundException;

	public void addStudent(Student student) throws ClassNotFoundException;

	public Student getStudentByName(String name) throws ClassNotFoundException;

	public void updateStudent(String name, Student updatedStudent) throws ClassNotFoundException;
}
