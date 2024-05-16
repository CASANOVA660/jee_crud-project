package dao;

import java.util.ArrayList;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.util.List;
import BEANS.user;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class userdao implements userinterface {
	public int countUsers() {
	    String sql = "SELECT COUNT(*) AS userCount FROM user";
	    int userCount = 0;
	    try (Connection cnx = dbc.createConnection();
	         PreparedStatement stmt = cnx.prepareStatement(sql);
	         ResultSet rs = stmt.executeQuery()) {
	        if (rs.next()) {
	            userCount = rs.getInt("userCount");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return userCount;
	}

	public List<user> getUsersByInitial(char initial) {
	    List<user> users = new ArrayList<>();
	    String sql = "SELECT * FROM user WHERE name LIKE ?";
	    try (Connection cnx = dbc.createConnection();
	         PreparedStatement stmt = cnx.prepareStatement(sql)) {
	        stmt.setString(1, initial + "%");
	        ResultSet rs = stmt.executeQuery();
	        while (rs.next()) {
	            int idUser = rs.getInt("id_user");
	            String name = rs.getString("name");
	            String prenom = rs.getString("prenom");
	            String email = rs.getString("email");
	            String password = rs.getString("password");
	            String image = rs.getString("image");
	            
	            user retrievedUser = new user(idUser, name, prenom, email, password, image);
	            users.add(retrievedUser);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return users;
	}




	@Override
	public void addUser(user user) {
	    String sql = "INSERT INTO user (name, prenom, email, password, image) VALUES (?, ?, ?, ?, ?)";
	    String password = user.getPassword();
	    if (password != null) {
	        String encryptedPassword = encryptPassword(password);
	        user.setPassword(encryptedPassword);
	    } else {
	        // Handle case where password is null
	        // For example, you could throw an exception or set a default password
	        user.setPassword("default_password");
	    }
	    try (
	        Connection cnx = dbc.createConnection();
	        PreparedStatement stmt = cnx.prepareStatement(sql)) {
	        stmt.setString(1, user.getName());
	        stmt.setString(2, user.getPrenom());
	        stmt.setString(3, user.getEmail());
	        stmt.setString(4, user.getPassword());
	        stmt.setString(5, user.getImage());
	        stmt.executeUpdate();
	       /* int rowsAffected = stmt.executeUpdate();
	        if (rowsAffected > 0) {
	            return true; // Return true if operation succeeds
	        } else {
	            return false; // Return false if no rows were affected
	        } // Return true if operation succeeds*/
	    } catch (SQLException e) {
	        e.printStackTrace();
	       // return false; // Return false if operation fails
	    }
	}



	@Override
	public boolean updateUser(user user) {
	    String sql = "UPDATE user SET name=?, prenom=?, email=?, password=?, image=? WHERE id_user=?";
	    try (
	        Connection cnx = dbc.createConnection();
	        PreparedStatement stmt = cnx.prepareStatement(sql)) {
	        stmt.setString(1, user.getName());
	        stmt.setString(2, user.getPrenom());
	        stmt.setString(3, user.getEmail());
	        stmt.setString(4, user.getPassword());
	        stmt.setString(5, user.getImage());
	        stmt.setInt(6, user.getIdUser());  // Assuming id_user is the 6th parameter in your SQL statement
	        int rowsUpdated = stmt.executeUpdate();
	        return rowsUpdated > 0; // Return true if at least one row was updated
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false; // Return false if an exception occurs or no rows were updated
	    }
	}


	@Override
	public boolean deleteUser(int id) {
	    String sql = "DELETE FROM user WHERE id_user=?";
	    try (
	        Connection cnx = dbc.createConnection();
	        PreparedStatement stmt = cnx.prepareStatement(sql)) {
	        stmt.setInt(1, id);
	        int rowsDeleted = stmt.executeUpdate();
	        return rowsDeleted > 0; // Return true if at least one row was deleted
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false; // Return false if an exception occurs or no rows were deleted
	    }
	}


	@Override
	public user getUserById(int id) {
	    user retrievedUser = null;
	    String sql = "SELECT * FROM user WHERE id_user = ?";
	    try (Connection cnx = dbc.createConnection();
	         PreparedStatement stmt = cnx.prepareStatement(sql)) {
	        stmt.setInt(1, id);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            int idUser = rs.getInt("id_user");
	            String name = rs.getString("name");
	            String prenom = rs.getString("prenom");
	            String email = rs.getString("email");
	            String password = rs.getString("password");
	            String image = rs.getString("image");
	            
	            retrievedUser = new user(idUser, name, prenom, email, password, image);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return retrievedUser;
	}
	public String encryptPassword(String password) {
	    try {
	        System.out.println("Encrypting password...");
	        MessageDigest md = MessageDigest.getInstance("SHA-256");
	        byte[] hashBytes = md.digest(password.getBytes());
	        StringBuilder hexString = new StringBuilder();

	        for (byte hashByte : hashBytes) {
	            String hex = Integer.toHexString(0xff & hashByte);
	            if (hex.length() == 1) hexString.append('0');
	            hexString.append(hex);
	        }

	        String encryptedPassword = hexString.toString();
	        System.out.println("Password encrypted successfully: " + encryptedPassword);
	        return encryptedPassword;
	    } catch (NoSuchAlgorithmException e) {
	        e.printStackTrace();
	        return null; // Handle error
	    }
	}


	@Override
	public List<user> getAllUsers() {
		 try {
	            Connection maconnexion = dbc.createConnection();
	            java.sql.PreparedStatement ps = maconnexion.prepareStatement("SELECT * FROM user");
	            ResultSet rs = ps.executeQuery();

	            ArrayList<user> users = new ArrayList<>();
	            while (rs.next()) {
	                user user = new user(
	                    rs.getInt("id_user"),
	                    rs.getString("name"),
	                    rs.getString("prenom"),
	                    rs.getString("email"),
	                    rs.getString("password"),
	                    rs.getString("image")
	                    
	                );
	                users.add(user);
	            }
	            return users;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	}
}
