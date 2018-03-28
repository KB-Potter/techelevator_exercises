package com.techelevator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.dbcp2.BasicDataSource;

public class JDBCExample {

	public static void main(String[] args) throws SQLException {
			
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/dvdstore");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		
		Connection conn = dataSource.getConnection();
		
		Statement stmt = conn.createStatement();
		
		String sqlActionFilmsReleaseIn2006 = "SELECT film.title, film.release_year "+
				 "FROM film JOIN film_category ON film.film_id = film_category.film_id "+
				 "JOIN category ON category.category_id = film_category.category_id "+
				 "WHERE film.release_year = 2006 "+
				 "AND category.name = 'Action'";
		
//		ResultSet results = stmt.executeQuery(sqlActionFilmsReleaseIn2006);
//		
//		System.out.println("Film Title\t\t\tRelease Year");
//		while(results.next()) {
//			String title = results.getString("title");
//			int releaseYear = results.getInt("release_year");
//			System.out.println(title+"\t\t\t"+releaseYear);
//		}
		
//		/* The next query example takes a parameter (i.e. is dynamic) */
		String firstName = "Nick";
		String lastName = "Stallone";
//		String lastName = "O'Malley";     // This is an exmample of non-malicious user input that will cause the query to break
//		String sqlMoviesByActor = "SELECT film.title "+
//								  "FROM film join film_actor on film.film_id = film_actor.film_id "+
//								  "JOIN actor on actor.actor_id = film_actor.actor_id "+
//								  "WHERE actor.first_name = '"+firstName.toUpperCase()+"' "+
//								  "AND actor.last_name = '"+lastName.toUpperCase()+"'";
//		
//		ResultSet results = stmt.executeQuery(sqlMoviesByActor);
//		
//		System.out.println("\n\nFilms Starring "+firstName+" "+lastName+": ");
//		while(results.next()) {
//			String title = results.getString("title");
//			System.out.println(title);
//		}
//		String sqlMoviesByActorParameterized = "SELECT film.title "+
//				  "FROM film join film_actor on film.film_id = film_actor.film_id "+
//				  "JOIN actor on actor.actor_id = film_actor.actor_id "+
//				  "WHERE actor.first_name = ? "+
//				  "AND actor.last_name = ?";
//		
//		PreparedStatement movieByActorStmt = conn.prepareStatement(sqlMoviesByActorParameterized);
//		movieByActorStmt.setString(1, firstName.toUpperCase());
//		movieByActorStmt.setString(2,  lastName.toUpperCase());
//		
//		ResultSet results = movieByActorStmt.executeQuery();
//		
//		System.out.println("\n\nFilms Starring "+firstName+" "+lastName+": ");
//		while(results.next()) {
//			String title = results.getString("title");
//			System.out.println(title);
//		}
		
		PreparedStatement insertActorStmt = conn.prepareStatement("INSERT INTO actor(first_name, last_name) "+
				  "VALUES(?, ?)");
		String actorFirstName = "MARC";
		String actorLastName = "O'MALLEY";
		insertActorStmt.setString(1, actorFirstName);
		insertActorStmt.setString(2, actorLastName);
		
		int cnt = insertActorStmt.executeUpdate();
		System.out.println(cnt);
		
		PreparedStatement findActorByNameStmt = conn.prepareStatement("SELECT * FROM actor WHERE first_name = ? AND last_name = ?");
		findActorByNameStmt.setString(1, actorFirstName);
		findActorByNameStmt.setString(2, actorLastName);
		ResultSet results = findActorByNameStmt.executeQuery();
		System.out.println("\n\nActors named "+actorFirstName+" "+actorLastName+":");
		while(results.next()) {
			int id = results.getInt("actor_id");
			String fname = results.getString("first_name");
			String lname = results.getString("last_name");
			System.out.println("("+id+") "+fname+" "+lname);
		}
		
		conn.setAutoCommit(false);
		PreparedStatement deleteMarcOMalleyStmt = conn.prepareStatement("DELETE FROM ACTOR WHERE first_name = 'MARC' AND last_name = 'O''MALLEY'");
		deleteMarcOMalleyStmt.executeUpdate();
		
		conn.commit();
		
		
		
	}
}
