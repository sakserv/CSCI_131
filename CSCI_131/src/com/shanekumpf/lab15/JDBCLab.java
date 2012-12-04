package com.shanekumpf.lab15;

import java.sql.*;

public class JDBCLab {

	public static final void main(String[] args) {
		
		final String USER_AND_PW = "user=skumpf&password=0138940";
		//final String USER_AND_PW = "user=root&password=.R3dp1ll";

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
					.getConnection("jdbc:mysql://localhost/mystery?" + USER_AND_PW);
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			return;
		}

		// Database and driver version
		DatabaseMetaData dbmeta = null;
		try {
			dbmeta = conn.getMetaData();
			System.out.println("Database Version: "
					+ dbmeta.getDatabaseProductName() + " version "
					+ dbmeta.getDatabaseProductVersion());
			System.out.println("Driver Version: " + dbmeta.getDriverName()
					+ " " + dbmeta.getDriverVersion());
		} catch (SQLException sqle) {
			sqle.printStackTrace(System.err);
			return;
		}

		// Display catalogues
		ResultSet rs = null;
		try {
			rs = dbmeta.getCatalogs();
			System.out.println();
			System.out.println("Catalogue: (" + dbmeta.getCatalogTerm() + ")");
			displayResultSet(rs, false);
		} catch (SQLException sqle) {
			sqle.printStackTrace(System.err);
		} finally {
			try {
				rs.close();
			} catch (Exception e) {}
		}

		// Display tables in mystery
		rs = null;
		try {
			rs = dbmeta.getTables("mystery", null, null, null);
			System.out.println();
			System.out.println("Tables in the mystery "
					+ dbmeta.getCatalogTerm() + ": ");
			displayResultSet(rs, true);
		} catch (SQLException sqle) {
			sqle.printStackTrace(System.err);
		}

		// Display columns in all tables
		ResultSet singleTableCols = null;
		try {
			rs.beforeFirst();
			while (rs.next()) {
				String table = rs.getString(3);
				System.out.println();
				System.out.println("Columns in '" + table + "'");
				singleTableCols = dbmeta.getColumns("mystery", null, table,
						null);
				displayResultSet(singleTableCols, true);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace(System.err);
		} finally {
			try {
				rs.close();
				conn.close();
			} catch(Exception e) {}
		}

	}

	private static void displayResultSet(ResultSet rs, boolean noNewLine) {
		ResultSetMetaData rsmeta = null;
		try {
			rsmeta = rs.getMetaData();
			int columnCount = rsmeta.getColumnCount();
			for (int i = 1; i <= columnCount; i++) {
				System.out.print(rsmeta.getColumnName(i) + "\t");
			}
			System.out.println();

			while (rs.next()) {
				for (int i = 1; i <= columnCount; i++) {
					if (noNewLine) {
						System.out.print(rs.getString(i) + "\t");
					} else {
						System.out.println(rs.getString(i));
					}
				}
				if (noNewLine) {
					System.out.println();
				}
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace(System.err);
		}
	}
}
