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

		// Menu system with roles
		JDBCDriver client = new JDBCDriver();
		
		// Testing form and report information
		//JDBCDriver client = new JDBCDriver(true);
		
		// *** Forms ***
		//client.clientAddFacultyMemberToStaff("206284377", "NewFacultyMember", "TheirNewLastName", "230-187-1111");
		//client.clientRegisterStudent("102092063", "anotherJavaStudent", "aNewLastName", "710-991-5777", "B.Sc.4 COSC", "1", 1, "part");
		//client.clientAssignFaculty("COSC1046", "296583945", "1");
		//client.clientEnrollStudent("COSC2007", "177252841", "1");
		//client.clientDeleteStudent("COSC2007", "177252841", "1");
		//client.clientAlterStudentRecord("COSC1046", "126293548", "1", "81");
		
		// *** Reports ***
		//client.clientGetClassList("COSC1046", "1", "2021F");
		//client.clientGetStudentTranscript("149514909");
		//client.clientGetStudentsInProgram("B.SC.4 COSC");
		//client.clientGetInstructorsForSpecificStudent("179918802", "2021F");
		//client.clientGetCoursesByFaculty("206284373", "2021F");
		//client.clientGetClassListForProfessor("296583945");
	}

}
