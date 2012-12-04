package com.shanekumpf.assignment6;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import csci130.KeyboardReader;

/**
 * Menu based system for managing students grades
 * @author i7
 *
 */
public class GradePortal {
	
	public static final void main(String[] args) {
		final String USER_AND_PW = "user=skumpf&password=0138940";
		final String DATABASE = "shane";
		
		// Step 1 - Load the driver
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (ClassNotFoundException cnfe) {
			System.err.println("The MYSQL Connector/J could not be found");
			cnfe.printStackTrace(System.err);
			return;
		} catch (Exception e) {}
		
		// Step 2 - Get a connection
		Connection conn = null;
		try {
			conn = DriverManager
					.getConnection("jdbc:mysql://localhost/" + DATABASE + "?" + USER_AND_PW);
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			return;
		}
		
		// Display the menu and get the user response
		int resp = 0;
		while(resp != 5) {
			displayMenu();
			System.out.print("Enter selection: ");
			resp = KeyboardReader.readInt();
			
			switch(resp) {
				case 1:	createNewStudent(conn); break;
				case 2:	addGradesForAssignment(conn); break;
				case 3:	changeGradesForAssignment(conn); break;
				case 4:	viewAllGradesForAssignment(conn); break;
			}
		}
		
		System.out.println("Shutting down...");
		try {
			conn.close();
		} catch(Exception e) {}
		
	}
	
	/**
	 * Display the main menu
	 */
	private static void displayMenu() {
		System.out.println("Welcome to the Student Grade Portal");
		System.out.println("1) Create a new student");
		System.out.println("2) Add grades for an assignment");
		System.out.println("3) Change grades for an assignment");
		System.out.println("4) View all grade for an assignment");
		System.out.println("5) Exit");
		char[] divider = new char[60];
		Arrays.fill(divider, '*');
		System.out.println(new String(divider));
	}
	
	/**
	 * Display the prompts for adding a new student and add the student to the students table
	 * 
	 * @param conn	connection to the database
	 */
	private static void createNewStudent(Connection conn) {
		System.out.print("Enter the students first name: ");
		String firstName = KeyboardReader.readLine();
		
		System.out.print("Enter the students last name: ");
		String lastName = KeyboardReader.readLine();
		
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			String sql = "INSERT INTO students (FIRST_NAME, LAST_NAME) VALUES ('" + firstName + "','" + lastName + "')";
			System.out.println(sql);
			stmt.execute(sql);
		} catch(SQLException sqle) {
			System.err.println("ERROR: Could not add student " + firstName + " " + lastName);
			sqle.printStackTrace(System.err);
		} finally {
			try {
				stmt.close();
			} catch(Exception e) {}
		}
	}
	
	/**
	 * Add grades for all students for a particular assignment.
	 * 
	 * @param conn the database connection
	 */
	private static void addGradesForAssignment(Connection conn) {
		System.out.print("Enter the assingment number: ");
		int assignmentId = KeyboardReader.readInt();
		
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			String sql = "SELECT * FROM students";
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int idColId = rs.findColumn("ID");
				int firstNameColId = rs.findColumn("FIRST_NAME");
				int lastNameColId = rs.findColumn("LAST_NAME");
				
				int id = rs.getInt(idColId);
				String firstName = rs.getString(firstNameColId);
				String lastName = rs.getString(lastNameColId);
				
				System.out.print("Enter " + firstName + " " + lastName + "'s Assignment " + assignmentId + " Grade: ");
				String grade = KeyboardReader.readLine();
				
				Statement addGradeStmt = null;
				try {
					addGradeStmt = conn.createStatement();
					String addGradeSql = "INSERT INTO grades VALUES (" + id + "," + assignmentId + ",'" + grade + "')";
					addGradeStmt.execute(addGradeSql);
				} catch(SQLException sqle) {
					System.out.println("ERROR adding assignment " + assignmentId + " grade for " + firstName + " " + lastName);
					sqle.printStackTrace(System.err);
				} finally {
					try {
						addGradeStmt.close();
					} catch(Exception e) {}
				}
			}
		} catch(SQLException sqle) {
			System.out.println("ERROR");
			sqle.printStackTrace(System.err);
		} finally {
			try {
				stmt.close();
				rs.close();
			} catch(Exception e) {}
		}
	}
	
	/**
	 * Update a single students grade for a particular assignment
	 * 
	 * @param conn the database connection
	 */
	private static void changeGradesForAssignment(Connection conn) {
		System.out.print("Enter the assingment number: ");
		int assignmentId = KeyboardReader.readInt();
		
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			String sql = "SELECT students.ID,FIRST_NAME,LAST_NAME FROM students INNER JOIN grades ON students.ID=grades.ID WHERE grades.ASSIGNMENT = '" + assignmentId + "'";
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int idColId = rs.findColumn("ID");
				int firstNameColId = rs.findColumn("FIRST_NAME");
				int lastNameColId = rs.findColumn("LAST_NAME");
				
				int id = rs.getInt(idColId);
				String firstName = rs.getString(firstNameColId);
				String lastName = rs.getString(lastNameColId);
				
				System.out.println(id + ") " + firstName + " " + lastName);
			}
			System.out.print("Enter the student ID to update: ");
			int studentId = KeyboardReader.readInt();
			
			System.out.print("Enter the new grade: ");
			String newGrade = KeyboardReader.readLine();
			
			stmt = conn.createStatement();
			sql = "UPDATE grades SET GRADE=" + newGrade + " WHERE ID=" + studentId;
			
			if(stmt.execute(sql)) {
				System.out.println("Grade successfully updated");
			} else {
				System.err.println("Could not update grade");
			}
			
		} catch(SQLException sqle) {
			System.err.println("ERROR updating record");
			sqle.printStackTrace(System.err);
		} finally {
			try {
				rs.close();
				stmt.close();
			} catch(Exception e) {}
		}
	}
	
	/**
	 * View all students grades for a particular assignment
	 * 
	 * @param conn the database connection
	 */
	private static void viewAllGradesForAssignment(Connection conn) {
		System.out.print("Enter the assingment number: ");
		int assignmentId = KeyboardReader.readInt();
		
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			String sql = "SELECT students.ID,FIRST_NAME,LAST_NAME FROM students INNER JOIN grades ON students.ID=grades.ID WHERE grades.ASSIGNMENT = '" + assignmentId + "'";
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int idColId = rs.findColumn("ID");
				int firstNameColId = rs.findColumn("FIRST_NAME");
				int lastNameColId = rs.findColumn("LAST_NAME");
				
				int id = rs.getInt(idColId);
				String firstName = rs.getString(firstNameColId);
				String lastName = rs.getString(lastNameColId);
				
				Statement gradeStatement = conn.createStatement();
				String gradeSql = "SELECT GRADE from grades WHERE ASSIGNMENT = " + assignmentId + " AND ID = " + id;
				ResultSet gradeRs = gradeStatement.executeQuery(gradeSql);
				while(gradeRs.next()) {
					System.out.println("Assigment " + assignmentId + " grade for " + firstName + " " + lastName + ": " + gradeRs.getString(1));
				}
			}
		} catch(SQLException sqle) {
			sqle.printStackTrace(System.err);
		} finally {
			try {
				rs.close();
				stmt.close();
			} catch(Exception e) {}
		}
		
	}

}
