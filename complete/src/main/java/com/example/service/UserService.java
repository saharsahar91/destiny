package com.example.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
//	private String encodePassword(String password) {
//		BCryptPasswordEncoder bcpe = new BC
//	}
	
	public class UserDto{
		public String username;
		public String firstName;
		public String lastName;
		public String password;
	}
	public void createUser( String username,String password, String email ) {
		String createUserQuery = 
				"INSERT INTO user_info (username, first_name, last_name, password, email) "
				+ "VALUES (:username, 'sahar', 'sahar', :password, :email) ";
		MapSqlParameterSource param = new MapSqlParameterSource("username", username)
				.addValue("password", password).addValue("email", email);
		namedParameterJdbcTemplate.update(createUserQuery, param);
		
	}
	
	public void updateUser(String username,String email, String firstName, String lastName, Integer userId) {
		String updateUserQuery = 
				"UPDATE user_info SET (username, first_name, last_name, email) "
				+ "= (:username, :firstName, :lastName, :email) "
				+ "WHERE user_id = :userId";
		MapSqlParameterSource param = new MapSqlParameterSource("username", username)
				.addValue("firstName", firstName).addValue("lastName", lastName)
				.addValue("email", email).addValue("userId", userId);
		namedParameterJdbcTemplate.update(updateUserQuery, param);
		
	}
	
	public Map<String,Object> getUser( String username, String password) {
		String getUserQuery = 
				"SELECT user_id FROM user_info WHERE username = :username AND password = :password";
		MapSqlParameterSource param = new MapSqlParameterSource("username", username)
				.addValue("password", password);
		return namedParameterJdbcTemplate.queryForMap(getUserQuery, param);
	}
	
	public Map<String,Object> getUserById(Integer userId) {
		String getUserQuery = 
				"SELECT username, first_name, last_name, email FROM user_info WHERE user_id = :userId";
		MapSqlParameterSource param = new MapSqlParameterSource("userId", userId);
		return namedParameterJdbcTemplate.queryForMap(getUserQuery, param);
	}
	
	public Boolean changePassword(String oldPassword,String newPassword, Integer userId) {
		String changePassQuery = 
				"WITH changePass AS ("
				+ "UPDATE user_info SET password "
				+ "=  CASE WHEN user_info.password = :oldPassword THEN :newPassword "
				+ " ELSE user_info.password END "
				+ "WHERE user_info.user_id = :userId "
				+ "RETURNING password)"
				+ "SELECT CASE WHEN password = :newPassword THEN true "
				+ "ELSE false END "
				+ "FROM changePass";
		MapSqlParameterSource param = new MapSqlParameterSource("oldPassword", oldPassword)
				.addValue("newPassword", newPassword).addValue("userId", userId);
		return namedParameterJdbcTemplate.queryForObject(changePassQuery, param, boolean.class);
		
	}
	
}