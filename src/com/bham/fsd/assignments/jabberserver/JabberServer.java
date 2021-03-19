package com.bham.fsd.assignments.jabberserver;
//Student ID: 2239587
//Name: Lukas Kubin

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.PreparedStatement;

public class JabberServer {
	
	private static String dbcommand = "jdbc:postgresql://127.0.0.1:5432/postgres";
	private static String db = "postgres";
	private static String pw = "password";

	private static Connection conn;
	
	public static Connection getConnection() {
		return conn;
	}

	public static void main(String[] args) {
		JabberServer jabber = new JabberServer();
		JabberServer.connectToDatabase();
		jabber.resetDatabase();
		print2(jabber.getMutualFollowUserIDs());
	}

	private int getNextUserID(){
		String query ="select max(userid) from jabberuser;";
		int maxid=-1;
		try{
			PreparedStatement stmt = conn.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				maxid = rs.getInt(1);
			}

		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		if (maxid<0) {
			return maxid;
		}
		return maxid+1;
	}

	private int getNextJabID(){
		String query ="select max(jabid) from jab;";
		int maxid=-1;
		try{
			PreparedStatement stmt = conn.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				maxid = rs.getInt(1);
			}

		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		if (maxid<0) {
			return maxid;
		}

		return maxid+1;
	}
	
	public ArrayList<String> getFollowerUserIDs(int userid) {
		ArrayList<String> ret = new ArrayList<String>();
		String query = "select userida from follows where useridb = ?;";
		try{
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1,userid);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				ret.add(rs.getObject("userida").toString());
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

		return ret;
	}

	public ArrayList<String> getFollowingUserIDs(int userid) {
		ArrayList<String> ret = new ArrayList<String>();
		String query = "select useridb from follows where userida=?;";
		try{
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1,userid);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				ret.add(rs.getObject("useridb").toString());
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return ret;
	}
	
	public ArrayList<ArrayList<String>> getMutualFollowUserIDs() {
		ArrayList<ArrayList<String>> ret = new ArrayList<ArrayList<String>>();
		String query= "SELECT u1.useridb,u1.userida from follows as u1 where exists " +
				"( select  u2.userida,u2.useridb from follows as u2 where u2.userida= u1.useridb and u2.useridb=u1.userida);";
		try{
			PreparedStatement stmt = conn.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				ArrayList<String> tmp = new ArrayList<String>();
				tmp.add(rs.getObject("userida").toString());
				tmp.add(rs.getObject("useridb").toString());
				ret.add(tmp);
			}
			
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		/*
		Creating a reverse ArrayList of values from the followers query to get rid of duplicates.
		 */
		ArrayList<ArrayList<String>> normRev = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		for (int i =0 ; i< ret.size()-1; i++){
			ArrayList<String> tmp = new ArrayList<String>();
			String first = (ret.get(i).get(0));
			String second = ret.get(i).get(1);
			tmp.add(second);
			tmp.add(first);
			normRev.add(tmp);
			if(!normRev.contains(ret.get(i))){
				result.add(ret.get(i));
			}
		}
		return result;
	}

	public ArrayList<ArrayList<String>> getLikesOfUser(int userid) {
		ArrayList<ArrayList<String>> ret = new ArrayList<ArrayList<String>>();
		String query = " SELECT username,jabtext FROM jabberuser natural join jab natural join" +
				"(SELECT jabid FROM likes WHERE userid=?) as A1;";
		try{
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1,userid);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				ArrayList<String> tmp = new ArrayList<String>();
				tmp.add(rs.getObject("username").toString());
				tmp.add(rs.getObject("jabtext").toString());
				ret.add(tmp);
			}

		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

		return ret;
	}
	
	public ArrayList<ArrayList<String>> getTimelineOfUser(int userid) {
		ArrayList<ArrayList<String>> ret = new ArrayList<ArrayList<String>>();
		String query="SELECT username,jabtext FROM jabberuser NATURAL JOIN jab NATURAL JOIN " +
				"(SELECT useridb AS userid FROM follows WHERE userida=?) as A1;";
		try{
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1,userid);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				ArrayList<String> tmp = new ArrayList<String>();
				tmp.add(rs.getObject("username").toString());
				tmp.add(rs.getObject("jabtext").toString());
				ret.add(tmp);
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}


		return ret;
	}

	public void addJab(String username, String jabtext) {
		int jabid = getNextJabID();
		String query = "insert into jab values(?,(select userid from jabberuser where username = ?),?);";
		try{
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1,jabid);
			stmt.setString(2,username);
			stmt.setString(3,jabtext);
			stmt.executeUpdate();

		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}


	}
	
	public void addUser(String username, String emailadd) {
		int newid =getNextUserID();
		String query="insert into jabberuser values(?,?,?);";
		try {
			PreparedStatement stmt= conn.prepareStatement(query);
			stmt.setInt(1,newid);
			stmt.setString(2,username);
			stmt.setString(3,emailadd);
			stmt.executeUpdate();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

	}
	
	public void addFollower(int userida, int useridb) {
		String query ="insert into follows values(?,?);";
		try {
			PreparedStatement stmt= conn.prepareStatement(query);
			stmt.setInt(1,userida);
			stmt.setInt(2,useridb);
			stmt.executeUpdate();
		}

		catch (SQLException throwables) {
			throwables.printStackTrace();
		}

	}
	
	public void addLike(int userid, int jabid) {
		String quary ="insert into likes values(?,?);";
		try {
			PreparedStatement stmt= conn.prepareStatement(quary);
			stmt.setInt(1,userid);
			stmt.setInt(2,jabid);
			stmt.executeUpdate();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}
	
	public ArrayList<String> getUsersWithMostFollowers() {
		ArrayList<String> ret = new ArrayList<String>();
		String query= "select Distinct(username) from jabberuser inner join follows on " +
				"(jabberuser.userid = follows.useridb) group by username having count(follows.useridb) >= all " +
				"(select count(useridb) from follows group by useridb order by count(useridb) desc);";
		try {
			PreparedStatement stmt= conn.prepareStatement(query);
			ResultSet rs= stmt.executeQuery();
			while (rs.next()){
				ret.add(rs.getObject("username").toString());
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}


		return ret;
	}
	
	public JabberServer() {}
	
	public static void connectToDatabase() {

		try {
			conn = DriverManager.getConnection(dbcommand,db,pw);

		}catch(Exception e) {		
			e.printStackTrace();
		}
	}

	/*
	 * Utility method to print an ArrayList of ArrayList<String>s to the console.
	 */
	private static void print2(ArrayList<ArrayList<String>> list) {
		
		for (ArrayList<String> s: list) {
			print1(s);
			System.out.println();
		}
	}
	/*
	 * Utility method to print an ArrayList to the console.
	 */
	private static void print1(ArrayList<String> list) {
		
		for (String s: list) {
			System.out.print(s + " ");
		}
	}

	public void resetDatabase() {
		
		dropTables();
		
		ArrayList<String> defs = loadSQL("jabberdef");
	
		ArrayList<String> data =  loadSQL("jabberdata");
		
		executeSQLUpdates(defs);
		executeSQLUpdates(data);
	}
	
	private void executeSQLUpdates(ArrayList<String> commands) {
	
		for (String query: commands) {
			
			try (PreparedStatement stmt = conn.prepareStatement(query)) {
				stmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private ArrayList<String> loadSQL(String sqlfile) {
		
		ArrayList<String> commands = new ArrayList<String>();
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(sqlfile + ".sql"));
			
			String command = "";
			
			String line = "";
			
			while ((line = reader.readLine())!= null) {
				
				if (line.contains(";")) {
					command += line;
					command = command.trim();
					commands.add(command);
					command = "";
				}
				
				else {
					line = line.trim();
					command += line + " ";
				}
			}
			
			reader.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return commands;
		
	}

	private void dropTables() {
		
		String[] commands = {
				"drop table jabberuser cascade;",
				"drop table jab cascade;",
				"drop table follows cascade;",
				"drop table likes cascade;"};
		
		for (String query: commands) {
			
			try (PreparedStatement stmt = conn.prepareStatement(query)) {
				stmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
