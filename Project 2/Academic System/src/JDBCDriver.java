/*
 * Michael Buffone
 * 169629010
 * November 26th, 2021
 * COSC4606 Project 2
 * ---
 * Academic Record System
 * JDBC Driver Object
 */

import java.sql.*;

public class JDBCDriver {
	
	// Connection string to the database
	private static Connection dbConnection = null;
	
	public JDBCDriver() {
		// Establish the connection to the database
		try
		{
			dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/academicdb", "root", "K@7p$K5#^nvEC795");
			
			// Final connection close
		}
		catch(Exception e) {
			System.out.println("Issue with connection to the database: " + e.getMessage());
		}
	}
	
	public void closeDBConnection() {
		System.out.println("Closing database connection...");
		try {
			dbConnection.close();
		}
		catch(Exception e) {
			System.out.println("Issue with closeDBConnection(): " + e.getMessage());
		}
	}
	
	// ****** Forms ******
	
	// Add a faculty member to the academic staff:
	public void clientAddFacultyMemberToStaff(String empNum, String fn, String ln, String pNum) {
		addFacultyMemberToStaff(empNum, fn, ln, pNum);
	}
	
	private void addFacultyMemberToStaff(String empNum, String fn, String ln, String pNum) {
		try
		{
			Statement statement = dbConnection.createStatement();
			statement.executeUpdate(
					"INSERT into employee VALUES ('" + empNum + "', '" + fn + "', '" + ln + "', '" + pNum + "');"
					);
		}
		catch(Exception e) {
			System.out.println("Issue with addFacultyMemberToStaff(): " + e.getMessage());
		}
	}

	// Register a student in the university:
	public void clientRegisterStudent(String stuID, String fn, String ln, String pNum, String proName, String termStatus, int coop, String full_or_part) {
		registerStudent(stuID, fn, ln, pNum, proName, termStatus, coop, full_or_part);
	}
	
	private void registerStudent(String stuID, String fn, String ln, String pNum, String proName, String termStatus, int coop, String full_or_part) {
		try
		{
			Statement statement = dbConnection.createStatement();
			statement.executeUpdate(
					"INSERT into student VALUES ('" + stuID + "', '" + fn + "', '" + ln + "', '" + pNum + "', '" + proName + "', '" + termStatus + "', '" + coop + "', '" + full_or_part + "');"
					);
		}
		catch(Exception e) {
			System.out.println("Issue with registerStudent(): " + e.getMessage());
		}
	}
	
	// Assign a faculty member to teach a given section of a given course:
	public void clientAssignFaculty(String courseID, String empNum, String sectionID) {
		assignFaculty(courseID, empNum, sectionID);
	}
	
	private void assignFaculty(String courseID, String empNum, String sectionID) {
		try
		{
			Statement statement = dbConnection.createStatement();
			statement.executeUpdate(
					"INSERT into course_staff VALUES ('" + courseID + "', '" + empNum + "', '" + sectionID + "');"
					);
		}
		catch(Exception e) {
			System.out.println("Issue with assignFaculty(): " + e.getMessage());
		}
	}
	
	// Enroll a student in a particular course:
	public void clientEnrollStudent(String courseID, String stuID, String sectionID) {
		enrollStudent(courseID, stuID, sectionID);
	}
	
	private void enrollStudent(String courseID, String stuID, String sectionID) {
		try
		{
			Statement statement = dbConnection.createStatement();
			statement.executeUpdate(
					"INSERT into course_enrollment VALUES ('" + courseID + "', '" + stuID + "', '" + sectionID + "');"
					);
		}
		catch(Exception e) {
			System.out.println("Issue with enrollStudent(): " + e.getMessage());
		}
	}
	
	// Delete a student from a particular course:
	public void clientDeleteStudent(String courseID, String stuID, String sectionID) {
		deleteStudent(courseID, stuID, sectionID);
	}
	
	private void deleteStudent(String courseID, String stuID, String sectionID) {
		try
		{
			Statement statement = dbConnection.createStatement();
			statement.executeUpdate(
					"DELETE FROM course_enrollment " + 
					"WHERE student_id = '" + stuID + "'AND course_id = '" + courseID + "'AND section_id = '" + sectionID + "';"
					);
		}
		catch(Exception e) {
			System.out.println("Issue with deleteStudent(): " + e.getMessage());
		}
	}

	// Alter a student record to permit, for example, entering a grade for a completed course:
	
	public void clientAlterStudentRecord(String courseID, String stuID, String sectionID, String grade) {
		alterStudentRecord(courseID, stuID, sectionID, grade);
	}
	
	private void alterStudentRecord(String courseID, String stuID, String sectionID, String grade) {
		try
		{
			Statement statement = dbConnection.createStatement();
			statement.executeUpdate(
					"UPDATE student_grades " + 
					"SET grade = '" + grade + "' " +
					"WHERE student_id = '" + stuID + "'AND course_id = '" + courseID + "'AND section_id = '" + sectionID + "';"
					);
		}
		catch(Exception e) {
			System.out.println("Issue with alterStudentRecord(): " + e.getMessage());
		}
	}
	
	// ****** Reports ******
	
	// Display a class list:
	public void clientGetClassList(String courseID, String sectionID, String term) {
		getClassList(courseID, sectionID, term);
	}
	
	private void getClassList(String courseID, String sectionID, String term) {
		try
		{
			Statement test = dbConnection.createStatement();
			ResultSet returnSet = test.executeQuery(
					"SELECT student.last_name, student.first_name, student.full_or_part, student.coop, course_info.course_id, course_info.term, course_info.section_id " +
					"FROM course_info " +
					"INNER JOIN course_enrollment ON course_info.course_id=course_enrollment.course_id " + 
					"INNER JOIN student ON course_enrollment.student_id=student.student_id " +
					"WHERE course_info.course_id='" + courseID + "' AND course_info.section_id='" + sectionID + "' AND course_info.term='" + term + "' " +
					"ORDER BY student.last_name, student.first_name;"
					);
			while (returnSet.next()) {
				System.out.println(returnSet.getString(1) + ", " + 
									returnSet.getString(2) + ", " +
									returnSet.getString(3) + ", " +
									returnSet.getString(4) + ", " +
									returnSet.getString(5) + ", " +
									returnSet.getString(6) + ", " +
									returnSet.getString(7));
			}
		}
		catch(Exception e) {
			System.out.println("Issue with getClassList(): " + e.getMessage());
		}
	}
	
	// Display a student transcript:
	public void clientGetStudentTranscript(String stuID) {
		getStudentTranscript(stuID);
	}
	
	private void getStudentTranscript(String stuID) {
		try
		{
			System.out.println("Course Enrollment:");
			getStudentTranscript_CourseEnrollment(stuID);
			
			System.out.println("\nAll Grades:");
			getStudentTranscript_AllGrades(stuID);
			
			System.out.println("\nMax Grades:");
			getStudentTranscript_MaxGrades(stuID);
			
			System.out.println("\nTerm Average Grades:");
			getStudentTranscript_TermAvgGrades(stuID);
		}
		catch(Exception e) {
			System.out.println("Issue with getStudentTranscript(): " + e.getMessage());
		}
	}
	
	// Helper 1 of transcript
	private void getStudentTranscript_CourseEnrollment(String stuID) {
		try
		{
			Statement test = dbConnection.createStatement();
			
			ResultSet returnSet = test.executeQuery(
					"SELECT course_enrollment.student_id, course_enrollment.course_id, course_enrollment.section_id " +
					"FROM course_enrollment WHERE course_enrollment.student_id='" + stuID + "' " +
					"ORDER BY course_enrollment.student_id, course_enrollment.course_id, course_enrollment.section_id;"
					);
			while (returnSet.next()) {
				System.out.println(returnSet.getString(1) + ", " + 
									returnSet.getString(2) + ", " +
									returnSet.getString(3));
			}
		}
		catch(Exception e) {
			System.out.println("Issue with getStudentTranscript_CourseEnrollment(): " + e.getMessage());
		}
	}
	
	// Helper 2 of transcript
	private void getStudentTranscript_AllGrades(String stuID) {
		try
		{
			Statement test = dbConnection.createStatement();
			
			ResultSet returnSet = test.executeQuery(
					"SELECT student_grades.student_id, student_grades.course_id, student_grades.section_id, student_grades.grade " +
					"FROM student_grades WHERE student_grades.student_id='" + stuID + "';"
					);
			while (returnSet.next()) {
				System.out.println(returnSet.getString(1) + ", " + 
									returnSet.getString(2) + ", " +
									returnSet.getString(3) + ", " +
									returnSet.getString(4));
			}
		}
		catch(Exception e) {
			System.out.println("Issue with getStudentTranscript_AllGrades(): " + e.getMessage());
		}
	}
	
	// Helper 3 of transcript
	private void getStudentTranscript_MaxGrades(String stuID) {
		try
		{
			Statement test = dbConnection.createStatement();
			
			ResultSet returnSet = test.executeQuery(
					"SELECT student_grades.student_id, student_grades.course_id, student_grades.section_id, MAX(student_grades.grade) " +
					"FROM student_grades " +
					"WHERE student_grades.student_id='" + stuID + "' " +
					"GROUP BY student_grades.course_id " +
					"ORDER BY student_grades.student_id, student_grades.course_id, student_grades.section_id;"
					);
			while (returnSet.next()) {
				System.out.println(returnSet.getString(1) + ", " + 
									returnSet.getString(2) + ", " +
									returnSet.getString(3) + ", " +
									returnSet.getString(4));
			}
		}
		catch(Exception e) {
			System.out.println("Issue with getStudentTranscript_MaxGrades(): " + e.getMessage());
		}
	}	
	
	// Helper 4 of transcript
	private void getStudentTranscript_TermAvgGrades(String stuID) {
		try
		{
			Statement test = dbConnection.createStatement();
			
			ResultSet returnSet = test.executeQuery(
					"SELECT student_grades.student_id, student_grades.course_id, student_grades.section_id, AVG(student_grades.grade) " +
					"FROM student_grades " +
					"WHERE student_grades.student_id='" + stuID + "' " +
					"GROUP BY student_grades.course_id " +
					"ORDER BY AVG(student_grades.grade) DESC;"
					);
			while (returnSet.next()) {
				System.out.println(returnSet.getString(1) + ", " + 
									returnSet.getString(2) + ", " +
									returnSet.getString(3) + ", " +
									returnSet.getString(4));
			}
		}
		catch(Exception e) {
			System.out.println("Issue with getStudentTranscript_TermAvgGrades(): " + e.getMessage());
		}
	}
	
	// Display a table of all students enrolled in a given degree program:
	public void clientGetStudentsInProgram(String proName) {
		getStudentsInProgram(proName);
	}
	
	private void getStudentsInProgram(String proName) {
		try
		{
			Statement test = dbConnection.createStatement();
			ResultSet returnSet = test.executeQuery(
					"SELECT student.last_name, student.first_name, student.student_id, student.program_name " +
					"FROM student " + 
					"WHERE student.program_name='" + proName + "' " +
					"ORDER BY student.last_name, student.first_name, student.student_id, student.program_name;"
					);
			while (returnSet.next()) {
				System.out.println(returnSet.getString(1) + ", " + 
									returnSet.getString(2) + ", " +
									returnSet.getString(3) + ", " +
									returnSet.getString(4));
			}
		}
		catch(Exception e) {
			System.out.println("Issue with getStudentsInProgram(): " + e.getMessage());
		}
	}
	
	// Display the all instructors teaching a course to some student:
	public void clientGetInstructorsForSpecificStudent(String stuID, String term) {
		getInstructorsForSpecificStudent(stuID, term);
	}
	
	private void getInstructorsForSpecificStudent(String stuID, String term) {
		try
		{
			Statement test = dbConnection.createStatement();
			ResultSet returnSet = test.executeQuery(
					"SELECT employee.last_name, employee.first_name, course_info.course_id, employee.phone_number " +
					"FROM student " +
					"INNER JOIN course_enrollment ON student.student_id=course_enrollment.student_id " + 
					"INNER JOIN course_info ON course_enrollment.course_id=course_info.course_id " +
					"INNER JOIN course_staff ON course_enrollment.course_id=course_staff.course_id " +
					"INNER JOIN employee ON course_staff.employee_number=employee.employee_number " +
					"WHERE student.student_id='" + stuID + "' AND course_info.term='" + term + "' " +
					"ORDER BY employee.last_name, employee.first_name, course_info.course_id, employee.phone_number;"
					);
			while (returnSet.next()) {
				System.out.println(returnSet.getString(1) + ", " + 
									returnSet.getString(2) + ", " +
									returnSet.getString(3) + ", " +
									returnSet.getString(4));
			}
		}
		catch(Exception e) {
			System.out.println("Issue with getInstructorsForSpecificStudent(): " + e.getMessage());
		}
	}
	
	// Display a list of all courses taught by a faculty member:
	public void clientGetCoursesByFaculty(String empNum, String term) {
		getCoursesByFaculty(empNum, term);
	}
	
	private void getCoursesByFaculty(String empNum, String term) {
		try
		{
			Statement test = dbConnection.createStatement();
			ResultSet returnSet = test.executeQuery(
					"SELECT course_info.term, course_info.course_id, course_info.course_name, course_staff.employee_number " +
					"FROM course_staff " +
					"INNER JOIN course_info ON course_staff.course_id=course_info.course_id " + 
					"WHERE course_staff.employee_number='" + empNum + "' AND course_info.term='" + term + "' " +
					"ORDER BY course_info.term, course_info.course_id, course_info.course_name, course_staff.employee_number;"
					);
			while (returnSet.next()) {
				System.out.println(returnSet.getString(1) + ", " + 
									returnSet.getString(2) + ", " +
									returnSet.getString(3) + ", " +
									returnSet.getString(4));
			}
		}
		catch(Exception e) {
			System.out.println("Issue with getCoursesByFaculty(): " + e.getMessage());
		}
	}
}