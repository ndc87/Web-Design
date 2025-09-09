package vn.iotstar.service.impl;

import vn.iotstar.service.UserService;
import vn.iotstar.dao.UserDao;
import vn.iotstar.dao.impl.UserDaoImpl;
import vn.iotstar.models.User;

public class UserServiceImpl implements UserService {
	UserDao userDao = new UserDaoImpl();

	@Override
	public vn.iotstar.models.User login(String username, String password) {
		User user = this.get(username);
		if (user != null && password.equals(user.getPassWord())) {
			return user;
		}
		return null;
	}
	
	@Override
	public vn.iotstar.models.User get(String username) {
		return userDao.get(username);
	}

	@Override
	public boolean register(String username, String password, String email, String fullname, String phone) {
		if (userDao.checkExistUsername(username)) {
			return false;
		}
		if (userDao.checkExistEmail(email)) {
			return false;
		}
		if (userDao.checkExistPhone(phone)) {
			return false;
		}
		
		User newUser = new User(email, username, fullname, password, null, phone);
		userDao.insert(newUser);
		return true;
	}

	@Override
	public void insert(User user) {
		userDao.insert(user);
	}

	@Override
	public boolean checkExistEmail(String email) {
		return userDao.checkExistEmail(email);
	}

	@Override
	public boolean checkExistUsername(String username) {
		return userDao.checkExistUsername(username);
	}

	@Override
	public boolean checkExistPhone(String phone) {
		return userDao.checkExistPhone(phone);
	}

	@Override
    public void updatePassword(String username, String newPassword) {
        userDao.updatePassword(username, newPassword);
    }
}
