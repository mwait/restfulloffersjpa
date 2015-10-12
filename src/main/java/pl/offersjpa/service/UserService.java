package pl.offersjpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import pl.offersjpa.dao.UserDao;
import pl.offersjpa.model.User;

@Service("userService")
public class UserService {
	private UserDao userDao;
	@Autowired
	@Qualifier("userDao")
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}


	public List<User> getCurrent(){
		
		return userDao.getUsers();
	}


}
