package pl.offersjpa.dao;

import java.util.List;

import pl.offersjpa.model.User;

public interface UserDao {
	List<User> getUsers();
	void createUser(User user);
	void deleteUser(Long toDelete);
	public User getUser(Long id);

}
