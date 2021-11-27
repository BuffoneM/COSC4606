/*
 * Michael Buffone
 * 169629010
 * November 26th, 2021
 * COSC4606 Project 2
 * ---
 * Academic Record System
 * JDBC Client Access
 */

import java.util.Scanner;

public class ClientAccess {

	public static void main(String[] args) {

		JDBCDriver client = new JDBCDriver();
		
		Scanner sc = new Scanner(System.in);
		
		// Testing form and report information
		
		// *** Forms ***
		//client.clientAddFacultyMemberToStaff("206284374", "javaTest", "javaLastJava", "230-187-8998");
		//client.clientRegisterStudent("102092067", "javastufirst", "asd", "710-281-5777", "B.Sc.4 COSC", "1", 0, "full");
		//client.clientAssignFaculty("COSC2007", "206284373", "1");
		//client.clientEnrollStudent("COSC2007", "177252841", "1");
		//client.clientDeleteStudent("COSC2007", "177252841", "1");
		//client.clientAlterStudentRecord("COSC1046", "126293548", "1", "65");
		
		// *** Reports ***
		//client.clientGetClassList("COSC1046", "1", "2021F");
		//client.clientGetStudentTranscript("149514909");
		//client.clientGetStudentsInProgram("B.SC.4 COSC");
		//client.clientGetInstructorsForSpecificStudent("179918802", "2021F");
		//client.clientGetCoursesByFaculty("206284373", "2021F");
	}

}
