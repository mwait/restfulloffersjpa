package pl.offersjpa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.offersjpa.model.User;



@Repository("userDao")
@Qualifier("userDao")
@Transactional
public class UserDaoImpl implements UserDao {

	@PersistenceContext(unitName="restfulloffersjpa")
	private EntityManager em;
	@Autowired

	
	@Override
	public List<User> getUsers() {
	
		TypedQuery<User> query = (TypedQuery<User>) em.createQuery("SELECT p FROM User p");
		List<User> results = query.getResultList();
		return results;

	}
	@Override
	public void createUser(User user) {
		em.persist(user);
	}
	@Override
	public void deleteUser(Long toDelete) {
		User user = em.find(User.class, toDelete);
		em.remove(user);		
	}
	@Override
	public User getUser(Long id) {
		User user = em.find(User.class, id);
		return user;
	}

}
