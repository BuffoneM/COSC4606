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
import java.util.Scanner;

public class JDBCDriver {

	// Connection string to the database
	private static Connection dbConnection = null;

	public JDBCDriver() {
		// Establish the connection to the database
		try {
			dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/academicdb", "root",
					"K@7p$K5#^nvEC795");

			getUserAccountType();

		} catch (Exception e) {
			System.out.println("Issue with connection to the database: " + e.getMessage());
		}
	}

	// Driver test constructor -> no menu system
	public JDBCDriver(boolean test) {
		// Establish the connection to the database
		try {
			dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/academicdb", "root",
					"K@7p$K5#^nvEC795");

		} catch (Exception e) {
			System.out.println("Issue with connection to the database: " + e.getMessage());
		}
	}

	// Get the account type
	private void getUserAccountType() {
		// Login
		Scanner input = new Scanner(System.in);

		String userAccountType = "";
		String userAccountID = "";
		String userAssociatedID = "";

		System.out.print("Enter your username: ");
		String username = input.next();

		System.out.print("Enter your password: ");
		String password = input.next();

		try {
			Statement test = dbConnection.createStatement();
			ResultSet returnSet = test
					.executeQuery("SELECT account.account_type, account.account_id, account.associated_id FROM account "
							+ "WHERE account.active_status=1 AND account.account_id='" + username
							+ "' AND account.password='" + password + "';");

			while (returnSet.next()) {
				userAccountType = returnSet.getString(1);
				userAccountID = returnSet.getString(2);
				userAssociatedID = returnSet.getString(3);
			}
		} catch (Exception e) {
			System.out.println("Issue with getUserAccountType(): " + e.getMessage());
		}

		// Menu system navigation
		if (userAccountType.equals("")) {
			System.out.println("Invalid credentials entered...");
		} else if (userAccountType.equals("dba")) {
			dbaMenuSystem(userAccountID);
		} else if (userAccountType.equals("registrar")) {
			registrarMenuSystem(userAccountID);
		} else if (userAccountType.equals("student")) {
			studentMenuSystem(userAccountID, userAssociatedID);
		} else if (userAccountType.equals("faculty")) {
			facultyMenuSystem(userAccountID, userAssociatedID);
		}

	}

	// ****** Proper menu system depending on the type of user login ******

	// Database Administrator
	private void dbaMenuSystem(String userAccountID) {
		Scanner input = new Scanner(System.in);
		String select = "0";
		do {

			System.out.println("\nHello " + userAccountID);
			System.out.println("------ Menu ------");
			System.out.println("0: Exit system");
			System.out.println("1: Create user account");
			System.out.println("2: Update user account status");
			System.out.println("3: Delete user account");
			System.out.println("4: View user account");
			System.out.print("Option select 'X': ");
			select = input.next();

			if (select.equals("1")) {
				try {

					System.out.print("Enter an account ID: ");
					String newAccountID = input.next();
					System.out.print("\nEnter an account password: ");
					String newAccountPassword = input.next();
					System.out.print("\nEnter an account type (dba, registrar, student, faculty): ");
					String newAccountType = input.next();
					System.out.print("\nEnter the account status (0 = inactive, 1 = active): ");
					int newAccountStatus = input.nextInt();
					System.out.print("\nEnter associated id: ");
					String newAssociatedID = input.next();

					Statement statement = dbConnection.createStatement();
					statement.executeUpdate("INSERT into account VALUES ('" + newAccountID + "', '" + newAccountPassword
							+ "', '" + newAccountType + "', " + newAccountStatus + ", '" + newAssociatedID + "');");

					System.out.println("Account successfully created...");
				} catch (Exception e) {
					System.out.println("Issue with dbaMenuSystem() -> createNewAccount: " + e.getMessage());
				}
			} else if (select.equals("2")) {
				try {

					System.out.print("Enter an account ID: ");
					String accountID = input.next();
					System.out.print("\nEnter the account status (0 = inactive, 1 = active): ");
					int newAccountStatus = input.nextInt();

					Statement statement = dbConnection.createStatement();
					statement.executeUpdate("UPDATE account " + "SET account.active_status = '" + newAccountStatus
							+ "' " + "WHERE account.account_id = '" + accountID + "';");

					System.out.println("Account successfully updated...");
				} catch (Exception e) {
					System.out.println("Issue with dbaMenuSystem() -> UpdateAccount: " + e.getMessage());
				}
			} else if (select.equals("3")) {
				try {

					System.out.print("Enter an account ID: ");
					String accountID = input.next();

					if (!accountID.equals(userAccountID)) {
						Statement statement = dbConnection.createStatement();
						statement.executeUpdate(
								"DELETE FROM account " + "WHERE account.account_id = '" + accountID + "';");

						System.out.println("Account successfully deleted...");
					} else {
						System.out.println("Cannot delete own account...");
					}

				} catch (Exception e) {
					System.out.println("Issue with dbaMenuSystem() -> DeleteAccount: " + e.getMessage());
				}

			} else if (select.equals("4")) {
				try {

					System.out.print("Enter an account ID: ");
					String accountID = input.next();

					Statement test = dbConnection.createStatement();
					ResultSet returnSet = test.executeQuery(
							"SELECT account.account_id, account.account_type, account.active_status FROM account "
									+ "WHERE account.account_id='" + accountID + "';");

					while (returnSet.next()) {
						System.out.println("User account: " + returnSet.getString(1));
						System.out.println("User account type: " + returnSet.getString(2));
						System.out.println("User account status: " + returnSet.getString(3));

					}

				} catch (Exception e) {
					System.out.println("Issue with dbaMenuSystem() -> UpdateAccount: " + e.getMessage());
				}
			}

		} while (!select.equals("0"));

		System.out.println("Exiting...");
		closeDBConnection();
	}

	// Registrar
	private void registrarMenuSystem(String userAccountID) {
		Scanner input = new Scanner(System.in);
		String select = "0";
		do {

			System.out.println("\nHello " + userAccountID);
			System.out.println("------ Menu ------");
			System.out.println("0: Exit system");
			System.out.println("1: Add student record");
			System.out.println("2: Update student record");
			System.out.println("3: View student record");
			System.out.print("Option select 'X': ");
			select = input.next();

			if (select.equals("1")) {
				try {
					System.out.print("Enter student ID: ");
					String newStudentID = input.next();
					System.out.print("Enter first name: ");
					String newFN = input.next();
					System.out.print("Enter last name: ");
					String newLN = input.next();
					System.out.print("Enter phone number: ");
					String newPN = input.next();
					System.out.print("Enter program name: ");
					String clearSc = input.nextLine();
					String newProName = input.nextLine();
					System.out.print("Enter term status (1, 0): ");
					String newTerm = input.next();
					System.out.print("Enter co-op status (1, 0): ");
					int newCoop = input.nextInt();
					System.out.print("Enter full-time or part-time (full, part): ");
					String newPart = input.next();

					clientRegisterStudent(newStudentID, newFN, newLN, newPN, newProName, newTerm, newCoop, newPart);

				} catch (Exception e) {
					System.out.println("Issue with dbaMenuSystem() -> addStudentRecord: " + e.getMessage());
				}
			} else if (select.equals("2")) {
				try {
					System.out.print("\nWhat do you want to update?\n");
					System.out.println("(1) First name");
					System.out.println("(2) Last name");
					System.out.println("(3) Phone number");
					System.out.println("(4) Program name");
					System.out.println("(5) Term status (1, 0)");
					System.out.println("(6) Co-op status (1, 0)");
					System.out.println("(7) Full-time or part-time (full, part)");
					String updateOption = input.next();

					if (updateOption.equals("1")) {
						System.out.print("Enter student id: ");
						String stuID = input.next();
						System.out.print("Enter new first name: ");
						String newFN = input.next();

						Statement statement = dbConnection.createStatement();
						statement.executeUpdate("UPDATE student " + "SET student.first_name = '" + newFN + "' "
								+ "WHERE student.student_id = '" + stuID + "';");
					}

					else if (updateOption.equals("2")) {
						System.out.print("Enter student id: ");
						String stuID = input.next();
						System.out.print("Enter new last name: ");
						String newLN = input.next();

						Statement statement = dbConnection.createStatement();
						statement.executeUpdate("UPDATE student " + "SET student.last_name = '" + newLN + "' "
								+ "WHERE student.student_id = '" + stuID + "';");
					}

					else if (updateOption.equals("3")) {
						System.out.print("Enter student id: ");
						String stuID = input.next();
						System.out.print("Enter new phone number: ");
						String newPN = input.next();

						Statement statement = dbConnection.createStatement();
						statement.executeUpdate("UPDATE student " + "SET student.phone_number = '" + newPN + "' "
								+ "WHERE student.student_id = '" + stuID + "';");
					}

					else if (updateOption.equals("4")) {
						System.out.print("Enter student id: ");
						String stuID = input.next();
						System.out.print("Enter new program name: ");
						String clear = input.nextLine();
						String newPN = input.nextLine();

						Statement statement = dbConnection.createStatement();
						statement.executeUpdate("UPDATE student " + "SET student.program_name = '" + newPN + "' "
								+ "WHERE student.student_id = '" + stuID + "';");
					}

					else if (updateOption.equals("5")) {
						System.out.print("Enter student id: ");
						String stuID = input.next();
						System.out.print("Enter new term status (1, 0): ");
						String newTS = input.next();

						Statement statement = dbConnection.createStatement();
						statement.executeUpdate("UPDATE student " + "SET student.term_status = '" + newTS + "' "
								+ "WHERE student.student_id = '" + stuID + "';");
					}

					else if (updateOption.equals("6")) {
						System.out.print("Enter student id: ");
						String stuID = input.next();
						System.out.print("Enter new co-op status (1, 0): ");
						String newCS = input.next();

						Statement statement = dbConnection.createStatement();
						statement.executeUpdate("UPDATE student " + "SET student.coop = '" + newCS + "' "
								+ "WHERE student.student_id = '" + stuID + "';");
					}

					else if (updateOption.equals("7")) {
						System.out.print("Enter student id: ");
						String stuID = input.next();
						System.out.print("Enter new full-time or part-time (full, part): ");
						String newFP = input.next();

						Statement statement = dbConnection.createStatement();
						statement.executeUpdate("UPDATE student " + "SET student.full_or_part = '" + newFP + "' "
								+ "WHERE student.student_id = '" + stuID + "';");
					}

				} catch (Exception e) {
					System.out.println("Issue with dbaMenuSystem() -> UpdateAccount: " + e.getMessage());
				}
			} else if (select.equals("3")) {
				try {

					System.out.print("Enter a student ID: ");
					String studentID = input.next();

					Statement test = dbConnection.createStatement();
					ResultSet returnSet = test.executeQuery(
							"SELECT student.student_id, student.first_name, student.last_name, student.phone_number, "
									+ "student.program_name, student.term_status, student.coop, student.full_or_part FROM student "
									+ "WHERE student.student_id='" + studentID + "';");

					while (returnSet.next()) {
						System.out.println("\nStudent ID: " + returnSet.getString(1));
						System.out.println("Student first name: " + returnSet.getString(2));
						System.out.println("Student last name: " + returnSet.getString(3));
						System.out.println("Student phone number: " + returnSet.getString(4));
						System.out.println("Student program name: " + returnSet.getString(5));
						System.out.println("Student term status: " + returnSet.getString(6));
						System.out.println("Student co-op: " + returnSet.getString(7));
						System.out.println("Student full-time or part-time: " + returnSet.getString(8));
					}

				} catch (Exception e) {
					System.out.println("Issue with dbaMenuSystem() -> ViewStudentRecord: " + e.getMessage());
				}
			}

		} while (!select.equals("0"));

		System.out.println("Exiting...");
		closeDBConnection();
	}

	// Student
	private void studentMenuSystem(String userAccountID, String userAssociatedID) {
		Scanner input = new Scanner(System.in);
		String select = "0";
		do {

			System.out.println("\nHello " + userAccountID);
			System.out.println("------ Menu ------");
			System.out.println("0: Exit system");
			System.out.println("1: View academic summary");
			System.out.print("Option select 'X': ");
			select = input.next();

			if (select.equals("1")) {
				try {
					clientGetStudentTranscript(userAssociatedID);
				} catch (Exception e) {
					System.out.println("Issue with dbaMenuSystem() -> studentMenuSystem: " + e.getMessage());
				}
			}

		} while (!select.equals("0"));

		System.out.println("Exiting...");
		closeDBConnection();
	}

	// Faculty
	private void facultyMenuSystem(String userAccountID, String userAssociatedID) {
		Scanner input = new Scanner(System.in);
		String select = "0";
		do {
			System.out.println("\nHello " + userAccountID);
			System.out.println("------ Menu ------");
			System.out.println("0: Exit system");
			System.out.println("1: View class list");
			System.out.print("Option select 'X': ");
			select = input.next();

			if (select.equals("1")) {
				try {
					clientGetClassListForProfessor(userAssociatedID);
				} catch (Exception e) {
					System.out.println("Issue with dbaMenuSystem() -> createNewAccount: " + e.getMessage());
				}
			}

		} while (!select.equals("0"));

		System.out.println("Exiting...");
		closeDBConnection();
	}

	public void closeDBConnection() {
		System.out.println("Closing database connection...");
		try {
			dbConnection.close();
			System.exit(0);
		} catch (Exception e) {
			System.out.println("Issue with closeDBConnection(): " + e.getMessage());
		}
	}

	// ****** Forms ******

	// Add a faculty member to the academic staff:
	public void clientAddFacultyMemberToStaff(String empNum, String fn, String ln, String pNum) {
		addFacultyMemberToStaff(empNum, fn, ln, pNum);
	}

	private void addFacultyMemberToStaff(String empNum, String fn, String ln, String pNum) {
		try {
			Statement statement = dbConnection.createStatement();
			statement.executeUpdate(
					"INSERT into employee VALUES ('" + empNum + "', '" + fn + "', '" + ln + "', '" + pNum + "');");
		} catch (Exception e) {
			System.out.println("Issue with addFacultyMemberToStaff(): " + e.getMessage());
		}
	}

	// Register a student in the university:
	public void clientRegisterStudent(String stuID, String fn, String ln, String pNum, String proName,
			String termStatus, int coop, String full_or_part) {
		registerStudent(stuID, fn, ln, pNum, proName, termStatus, coop, full_or_part);
	}

	private void registerStudent(String stuID, String fn, String ln, String pNum, String proName, String termStatus,
			int coop, String full_or_part) {
		try {
			Statement statement = dbConnection.createStatement();
			statement.executeUpdate("INSERT into student VALUES ('" + stuID + "', '" + fn + "', '" + ln + "', '" + pNum
					+ "', '" + proName + "', '" + termStatus + "', '" + coop + "', '" + full_or_part + "');");
		} catch (Exception e) {
			System.out.println("Issue with registerStudent(): " + e.getMessage());
		}
	}

	// Assign a faculty member to teach a given section of a given course:
	public void clientAssignFaculty(String courseID, String empNum, String sectionID) {
		assignFaculty(courseID, empNum, sectionID);
	}

	private void assignFaculty(String courseID, String empNum, String sectionID) {
		try {
			Statement statement = dbConnection.createStatement();
			statement.executeUpdate(
					"INSERT into course_staff VALUES ('" + courseID + "', '" + empNum + "', '" + sectionID + "');");
		} catch (Exception e) {
			System.out.println("Issue with assignFaculty(): " + e.getMessage());
		}
	}

	// Enroll a student in a particular course:
	public void clientEnrollStudent(String courseID, String stuID, String sectionID) {
		enrollStudent(courseID, stuID, sectionID);
	}

	private void enrollStudent(String courseID, String stuID, String sectionID) {
		try {
			Statement statement = dbConnection.createStatement();
			statement.executeUpdate(
					"INSERT into course_enrollment VALUES ('" + courseID + "', '" + stuID + "', '" + sectionID + "');");
		} catch (Exception e) {
			System.out.println("Issue with enrollStudent(): " + e.getMessage());
		}
	}

	// Delete a student from a particular course:
	public void clientDeleteStudent(String courseID, String stuID, String sectionID) {
		deleteStudent(courseID, stuID, sectionID);
	}

	private void deleteStudent(String courseID, String stuID, String sectionID) {
		try {
			Statement statement = dbConnection.createStatement();
			statement.executeUpdate("DELETE FROM course_enrollment " + "WHERE student_id = '" + stuID
					+ "'AND course_id = '" + courseID + "'AND section_id = '" + sectionID + "';");
		} catch (Exception e) {
			System.out.println("Issue with deleteStudent(): " + e.getMessage());
		}
	}

	// Alter a student record to permit, for example, entering a grade for a
	// completed course:

	public void clientAlterStudentRecord(String courseID, String stuID, String sectionID, String grade) {
		alterStudentRecord(courseID, stuID, sectionID, grade);
	}

	private void alterStudentRecord(String courseID, String stuID, String sectionID, String grade) {
		try {
			Statement statement = dbConnection.createStatement();
			statement.executeUpdate("UPDATE student_grades " + "SET grade = '" + grade + "' " + "WHERE student_id = '"
					+ stuID + "'AND course_id = '" + courseID + "'AND section_id = '" + sectionID + "';");
		} catch (Exception e) {
			System.out.println("Issue with alterStudentRecord(): " + e.getMessage());
		}
	}

	// ****** Reports ******

	// Display a class list:
	public void clientGetClassList(String courseID, String sectionID, String term) {
		getClassList(courseID, sectionID, term);
	}

	private void getClassList(String courseID, String sectionID, String term) {
		try {
			Statement test = dbConnection.createStatement();
			ResultSet returnSet = test.executeQuery(
					"SELECT student.last_name, student.first_name, student.full_or_part, student.coop, course_info.course_id, course_info.term, course_info.section_id "
							+ "FROM course_info "
							+ "INNER JOIN course_enrollment ON course_info.course_id=course_enrollment.course_id "
							+ "INNER JOIN student ON course_enrollment.student_id=student.student_id "
							+ "WHERE course_info.course_id='" + courseID + "' AND course_info.section_id='" + sectionID
							+ "' AND course_info.term='" + term + "' "
							+ "ORDER BY student.last_name, student.first_name;");
			while (returnSet.next()) {
				System.out.println(returnSet.getString(1) + ", " + returnSet.getString(2) + ", "
						+ returnSet.getString(3) + ", " + returnSet.getString(4) + ", " + returnSet.getString(5) + ", "
						+ returnSet.getString(6) + ", " + returnSet.getString(7));
			}
		} catch (Exception e) {
			System.out.println("Issue with getClassList(): " + e.getMessage());
		}
	}

	// Display a student transcript:
	public void clientGetStudentTranscript(String stuID) {
		getStudentTranscript(stuID);
	}

	private void getStudentTranscript(String stuID) {
		try {
			System.out.println("Course Enrollment:");
			getStudentTranscript_CourseEnrollment(stuID);

			System.out.println("\nAll Grades:");
			getStudentTranscript_AllGrades(stuID);

			System.out.println("\nMax Grades:");
			getStudentTranscript_MaxGrades(stuID);

			System.out.println("\nTerm Average Grades:");
			getStudentTranscript_TermAvgGrades(stuID);
		} catch (Exception e) {
			System.out.println("Issue with getStudentTranscript(): " + e.getMessage());
		}
	}

	// Helper 1 of transcript
	private void getStudentTranscript_CourseEnrollment(String stuID) {
		try {
			Statement test = dbConnection.createStatement();

			ResultSet returnSet = test.executeQuery(
					"SELECT course_enrollment.student_id, course_enrollment.course_id, course_enrollment.section_id "
							+ "FROM course_enrollment WHERE course_enrollment.student_id='" + stuID + "' "
							+ "ORDER BY course_enrollment.student_id, course_enrollment.course_id, course_enrollment.section_id;");
			while (returnSet.next()) {
				System.out.println(
						returnSet.getString(1) + ", " + returnSet.getString(2) + ", " + returnSet.getString(3));
			}
		} catch (Exception e) {
			System.out.println("Issue with getStudentTranscript_CourseEnrollment(): " + e.getMessage());
		}
	}

	// Helper 2 of transcript
	private void getStudentTranscript_AllGrades(String stuID) {
		try {
			Statement test = dbConnection.createStatement();

			ResultSet returnSet = test.executeQuery(
					"SELECT student_grades.student_id, student_grades.course_id, student_grades.section_id, student_grades.grade "
							+ "FROM student_grades WHERE student_grades.student_id='" + stuID + "';");
			while (returnSet.next()) {
				System.out.println(returnSet.getString(1) + ", " + returnSet.getString(2) + ", "
						+ returnSet.getString(3) + ", " + returnSet.getString(4));
			}
		} catch (Exception e) {
			System.out.println("Issue with getStudentTranscript_AllGrades(): " + e.getMessage());
		}
	}

	// Helper 3 of transcript
	private void getStudentTranscript_MaxGrades(String stuID) {
		try {
			Statement test = dbConnection.createStatement();

			ResultSet returnSet = test.executeQuery(
					"SELECT student_grades.student_id, student_grades.course_id, student_grades.section_id, MAX(student_grades.grade) "
							+ "FROM student_grades " + "WHERE student_grades.student_id='" + stuID + "' "
							+ "GROUP BY student_grades.course_id "
							+ "ORDER BY student_grades.student_id, student_grades.course_id, student_grades.section_id;");
			while (returnSet.next()) {
				System.out.println(returnSet.getString(1) + ", " + returnSet.getString(2) + ", "
						+ returnSet.getString(3) + ", " + returnSet.getString(4));
			}
		} catch (Exception e) {
			System.out.println("Issue with getStudentTranscript_MaxGrades(): " + e.getMessage());
		}
	}

	// Helper 4 of transcript
	private void getStudentTranscript_TermAvgGrades(String stuID) {
		try {
			Statement test = dbConnection.createStatement();

			ResultSet returnSet = test.executeQuery(
					"SELECT student_grades.student_id, student_grades.course_id, student_grades.section_id, AVG(student_grades.grade) "
							+ "FROM student_grades " + "WHERE student_grades.student_id='" + stuID + "' "
							+ "GROUP BY student_grades.course_id " + "ORDER BY AVG(student_grades.grade) DESC;");
			while (returnSet.next()) {
				System.out.println(returnSet.getString(1) + ", " + returnSet.getString(2) + ", "
						+ returnSet.getString(3) + ", " + returnSet.getString(4));
			}
		} catch (Exception e) {
			System.out.println("Issue with getStudentTranscript_TermAvgGrades(): " + e.getMessage());
		}
	}

	// Display a table of all students enrolled in a given degree program:
	public void clientGetStudentsInProgram(String proName) {
		getStudentsInProgram(proName);
	}

	private void getStudentsInProgram(String proName) {
		try {
			Statement test = dbConnection.createStatement();
			ResultSet returnSet = test.executeQuery(
					"SELECT student.last_name, student.first_name, student.student_id, student.program_name "
							+ "FROM student " + "WHERE student.program_name='" + proName + "' "
							+ "ORDER BY student.last_name, student.first_name, student.student_id, student.program_name;");
			while (returnSet.next()) {
				System.out.println(returnSet.getString(1) + ", " + returnSet.getString(2) + ", "
						+ returnSet.getString(3) + ", " + returnSet.getString(4));
			}
		} catch (Exception e) {
			System.out.println("Issue with getStudentsInProgram(): " + e.getMessage());
		}
	}

	// Display the all instructors teaching a course to some student:
	public void clientGetInstructorsForSpecificStudent(String stuID, String term) {
		getInstructorsForSpecificStudent(stuID, term);
	}

	private void getInstructorsForSpecificStudent(String stuID, String term) {
		try {
			Statement test = dbConnection.createStatement();
			ResultSet returnSet = test.executeQuery(
					"SELECT employee.last_name, employee.first_name, course_info.course_id, employee.phone_number "
							+ "FROM student "
							+ "INNER JOIN course_enrollment ON student.student_id=course_enrollment.student_id "
							+ "INNER JOIN course_info ON course_enrollment.course_id=course_info.course_id "
							+ "INNER JOIN course_staff ON course_enrollment.course_id=course_staff.course_id "
							+ "INNER JOIN employee ON course_staff.employee_number=employee.employee_number "
							+ "WHERE student.student_id='" + stuID + "' AND course_info.term='" + term + "' "
							+ "ORDER BY employee.last_name, employee.first_name, course_info.course_id, employee.phone_number;");
			while (returnSet.next()) {
				System.out.println(returnSet.getString(1) + ", " + returnSet.getString(2) + ", "
						+ returnSet.getString(3) + ", " + returnSet.getString(4));
			}
		} catch (Exception e) {
			System.out.println("Issue with getInstructorsForSpecificStudent(): " + e.getMessage());
		}
	}

	// Display a list of all courses taught by a faculty member:
	public void clientGetCoursesByFaculty(String empNum, String term) {
		getCoursesByFaculty(empNum, term);
	}

	private void getCoursesByFaculty(String empNum, String term) {
		try {
			Statement test = dbConnection.createStatement();
			ResultSet returnSet = test.executeQuery(
					"SELECT course_info.term, course_info.course_id, course_info.course_name, course_staff.employee_number "
							+ "FROM course_staff "
							+ "INNER JOIN course_info ON course_staff.course_id=course_info.course_id "
							+ "WHERE course_staff.employee_number='" + empNum + "' AND course_info.term='" + term + "' "
							+ "ORDER BY course_info.term, course_info.course_id, course_info.course_name, course_staff.employee_number;");
			while (returnSet.next()) {
				System.out.println(returnSet.getString(1) + ", " + returnSet.getString(2) + ", "
						+ returnSet.getString(3) + ", " + returnSet.getString(4));
			}
		} catch (Exception e) {
			System.out.println("Issue with getCoursesByFaculty(): " + e.getMessage());
		}
	}

	// Display class list for a given faculty member:
	public void clientGetClassListForProfessor(String empNum) {
		getClassListForProfessor(empNum);
	}

	private void getClassListForProfessor(String empNum) {
		try {
			Statement test = dbConnection.createStatement();
			ResultSet returnSet = test.executeQuery(
					"SELECT course_staff.course_id, course_staff.section_id, student.last_name, student.first_name, student.student_id "
							+ "FROM course_staff "
							+ "INNER JOIN course_enrollment ON course_staff.course_id=course_enrollment.course_id AND course_staff.section_id=course_enrollment.section_id "
							+ "INNER JOIN student ON course_enrollment.student_id=student.student_id "
							+ "WHERE course_staff.employee_number='" + empNum + "' "
							+ "ORDER BY course_staff.course_id, course_staff.section_id, student.last_name, student.first_name, student.student_id;");
			while (returnSet.next()) {
				System.out.println(returnSet.getString(1) + ", " + returnSet.getString(2) + ", "
						+ returnSet.getString(3) + ", " + returnSet.getString(4) + ", " + returnSet.getString(5));
			}
		} catch (Exception e) {
			System.out.println("Issue with getClassListForProfessor(): " + e.getMessage());
		}
	}

}