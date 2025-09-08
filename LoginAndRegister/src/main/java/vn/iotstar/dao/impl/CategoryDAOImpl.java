
package vn.iotstar.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import vn.iotstar.configs.dbconnection;
import vn.iotstar.dao.CategoryDAO;

import vn.iotstar.models.Category;

public class CategoryDAOImpl extends dbconnection implements CategoryDAO {
	@Override
	public void insert(Category category) {
		String sql = "INSERT INTO Category(cate_name,icons) VALUES (?,?)";
		try {
			Connection con = new dbconnection().getConnectionW();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, category.getCatename());
			ps.setString(2, category.getIcon());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void edit(Category category) {
		String sql = "UPDATE Category SET cate_name = ?, icons=? WHERE cate_id = ?";
		try {
			Connection con = new dbconnection().getConnectionW();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, category.getCatename());
			ps.setString(2, category.getIcon());
			ps.setInt(3, category.getCateid());
			ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM category WHERE cate_id = ?";
		try {
			Connection con = new dbconnection().getConnectionW();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Category get(int id) {
		String sql = "SELECT * FROM Category WHERE cate_id = ? ";
		try {
			Connection con = new dbconnection().getConnectionW();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Category category = new Category();
				category.setCateid(rs.getInt("cate_id"));
				category.setCatename(rs.getString("cate_name"));
				category.setIcon(rs.getString("icons"));
				return category;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Category> getAll() {
		List<Category> categories = new ArrayList<Category>();
		String sql = "SELECT * FROM Category";
		try {
			Connection conn = new dbconnection().getConnectionW();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Category category = new Category();
				category.setCateid(rs.getInt("cate_id"));
				category.setCatename(rs.getString("cate_name"));
				category.setIcon(rs.getString("icons"));
				categories.add(category);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return categories;
	}
	// Java
	@Override
	public List<Category> search(String keyword) {
	    List<Category> categories = new ArrayList<>();
	    String sql = "SELECT * FROM Category WHERE cate_name LIKE ?";
	    try {
	        Connection conn = new dbconnection().getConnectionW();
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setString(1, "%" + keyword + "%");
	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	            Category category = new Category();
	            category.setCateid(rs.getInt("cate_id"));
	            category.setCatename(rs.getString("cate_name"));
	            category.setIcon(rs.getString("icons"));
	            categories.add(category);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return categories;
	}
	@Override
	public Category get(String name) {
	    String sql = "SELECT * FROM Category WHERE cate_name = ?";
	    try {
	        Connection con = new dbconnection().getConnectionW();
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setString(1, name);
	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	            Category category = new Category();
	            category.setCateid(rs.getInt("cate_id"));
	            category.setCatename(rs.getString("cate_name"));
	            category.setIcon(rs.getString("icons"));
	            return category;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
	}

}
