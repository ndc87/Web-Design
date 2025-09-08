
package vn.iotstar.dao;

import java.util.List;
//import java.util.Locale.Category;
import vn.iotstar.models.Category;

public interface CategoryDAO {
	void insert(Category category);
	void edit(Category category);
	void delete(int id);
	Category get(int id);
	Category get(String name);
	List<Category> getAll();
	List<Category> search(String keyword);
}
