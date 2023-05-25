package assessment;

public class Student {
	private String name;
	private int age;
	private String gender;
	private String dob;
	private String state;
	private int number;

	public Student(String name, int age, String gender, String dob, String state, int number2) {
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.dob = dob;
		this.state = state;
		this.number = number2;
	}

	// Getters and setters for the fields
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
}
