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
					"SELECT student.last_name, student.first_name, student.full_or_part, student.co-op, course_info.course_id, course_info.term, course_info.section_id " +
					"FROM course_info " +
					"INNER JOIN course_enrollment ON course_info.course_id=course_enrollment.course_id " + 
					"INNER JOIN student ON course_enrollment.student_id=student.student_id;"
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
	public void clientGetStudentTranscript() {
		getStudentTranscript();
	}
	
	private void getStudentTranscript() {
		try
		{
			Statement test = dbConnection.createStatement();
			ResultSet returnSet = test.executeQuery(
					"SELECT student.last_name, student.first_name, student.full_or_part, student.co-op, course_info.course_id, course_info.term, course_info.section_id " +
					"FROM course_info " +
					"INNER JOIN course_enrollment ON course_info.course_id=course_enrollment.course_id " + 
					"INNER JOIN student ON course_enrollment.student_id=student.student_id;"
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
			System.out.println("Issue with getStudentTranscript(): " + e.getMessage());
		}
	}
	// Display a table of all students enrolled in a given degree program:
	public void clientGetStudentsInProgram() {
		getStudentsInProgram();
	}
	
	private void getStudentsInProgram() {
		try
		{
			Statement test = dbConnection.createStatement();
			ResultSet returnSet = test.executeQuery(
					"SELECT student.last_name, student.first_name, student.full_or_part, student.co-op, course_info.course_id, course_info.term, course_info.section_id " +
					"FROM course_info " +
					"INNER JOIN course_enrollment ON course_info.course_id=course_enrollment.course_id " + 
					"INNER JOIN student ON course_enrollment.student_id=student.student_id;"
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
			System.out.println("Issue with getStudentsInProgram(): " + e.getMessage());
		}
	}
	// Display the all instructors teaching a course to some student:
	public void clientGetInstructorsForSpecificStudent() {
		getInstructorsForSpecificStudent();
	}
	
	private void getInstructorsForSpecificStudent() {
		try
		{
			Statement test = dbConnection.createStatement();
			ResultSet returnSet = test.executeQuery(
					"SELECT student.last_name, student.first_name, student.full_or_part, student.co-op, course_info.course_id, course_info.term, course_info.section_id " +
					"FROM course_info " +
					"INNER JOIN course_enrollment ON course_info.course_id=course_enrollment.course_id " + 
					"INNER JOIN student ON course_enrollment.student_id=student.student_id;"
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
			System.out.println("Issue with getInstructorsForSpecificStudent(): " + e.getMessage());
		}
	}
	
	// Display a list of all courses taught by a faculty member:
	public void clientGetCoursesByFaculty() {
		getCoursesByFaculty();
	}
	
	private void getCoursesByFaculty() {
		try
		{
			Statement test = dbConnection.createStatement();
			ResultSet returnSet = test.executeQuery(
					"SELECT student.last_name, student.first_name, student.full_or_part, student.co-op, course_info.course_id, course_info.term, course_info.section_id " +
					"FROM course_info " +
					"INNER JOIN course_enrollment ON course_info.course_id=course_enrollment.course_id " + 
					"INNER JOIN student ON course_enrollment.student_id=student.student_id;"
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
			System.out.println("Issue with getCoursesByFaculty(): " + e.getMessage());
		}
	}
}

/*
//Establish the connection to the database
		try
		{
			dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/academicdb", "root", "K@7p$K5#^nvEC795");
			Statement test = dbConnection.createStatement();
			ResultSet testSet = test.executeQuery("select * from course_enrollment;");
			while (testSet.next()) {
				System.out.println(testSet.getString(1) + ", " + 
									testSet.getString(2) + ", " +
									testSet.getString(3));
			}
		}
		catch(Exception e) {
			System.out.println(e.getStackTrace());
		}
*/

//****** Queries ******

	// Returns a class list when given a course id, section id, and term.
	
	// Returns all of the courses taught by a staff member when given an employee number and a term.
	
	// Returns all instructor information for a given student id and a term.
	
	// Returns all students in a given program.
	
	// Sub-report Section
	
	// -Returns all course ids and section ids for a given student id.

	// -Returns all course ids, section ids, and grades for a given student id.
	
	// -Returns the max grade for all course ids and section ids when given a student id.
	
	// -Returns the average grade for a student in all terms.