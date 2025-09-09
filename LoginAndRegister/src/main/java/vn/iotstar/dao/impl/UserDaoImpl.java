package vn.iotstar.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vn.iotstar.configs.dbconnection;
import vn.iotstar.dao.UserDao;
import vn.iotstar.models.User;

public class UserDaoImpl implements UserDao {

	@Override
	public User get(String username) {
		String sql = "SELECT * FROM Users WHERE LOWER(username) = LOWER(?)";
		dbconnection dbConn = new dbconnection();

		try (Connection conn = dbConn.getConnectionW();
				PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setString(1, username);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					User user = new User();
					user.setId(rs.getInt("id"));
					user.setUserName(rs.getString("username"));
					user.setPassWord(rs.getString("password"));
					user.setEmail(rs.getString("email"));
					user.setFullName(rs.getString("fullname"));
					user.setPhone(rs.getString("phone"));
					// Bỏ dòng này đi để không lấy giá trị từ cột 'avatar'
					// user.setAvatar(rs.getString("avatar"));
					return user;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void insert(User user) {
		// Sửa lỗi: Bỏ cột 'avatar' khỏi câu lệnh INSERT
		String sql = "INSERT INTO [Users](email, username, fullname, password, phone) VALUES (?,?,?,?,?)";
		try (Connection conn = new dbconnection().getConnectionW();
				PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getUserName());
			ps.setString(3, user.getFullName());
			ps.setString(4, user.getPassWord());
			ps.setString(5, user.getPhone());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean checkExistEmail(String email) {
		boolean duplicate = false;
		String query = "select * from [Users] where email = ?";
		try (Connection conn = new dbconnection().getConnectionW();
				PreparedStatement ps = conn.prepareStatement(query);) {
			ps.setString(1, email);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					duplicate = true;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return duplicate;
	}

	@Override
	public boolean checkExistUsername(String username) {
		boolean duplicate = false;
		String query = "select * from [Users] where username = ?";
		try (Connection conn = new dbconnection().getConnectionW();
				PreparedStatement ps = conn.prepareStatement(query);) {
			ps.setString(1, username);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					duplicate = true;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return duplicate;
	}

	@Override
	public boolean checkExistPhone(String phone) {
		boolean duplicate = false;
		String query = "select * from [Users] where phone = ?";
		try (Connection conn = new dbconnection().getConnectionW();
				PreparedStatement ps = conn.prepareStatement(query);) {
			ps.setString(1, phone);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					duplicate = true;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return duplicate;
	}

	@Override
	public void updatePassword(String username, String newPassword) {
		String sql = "UPDATE Users SET password = ? WHERE username = ?";
		try (Connection conn = new dbconnection().getConnectionW();
				PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setString(1, newPassword);
			ps.setString(2, username);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
