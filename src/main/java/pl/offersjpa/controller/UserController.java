package pl.offersjpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.offersjpa.exception.UserNotFoundException;
import pl.offersjpa.model.User;
import pl.offersjpa.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<User> getAllCustomers(){
		return userService.getCurrent();
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public User getCustomer(@PathVariable("id") Long id){
		User user = userService.getUser(id);
		if (user!=null){
			throw new UserNotFoundException();}
		else{
		return user;}
	}
	
	@RequestMapping(value ="/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteCustomer (@PathVariable("id") Long id){
		try {
			userService.deleteUser(id);} 
		catch (Exception e) {
			throw new UserNotFoundException();
		}
	}
	
	@RequestMapping(value="", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void addCustomer(@RequestBody User user){
		try {
		userService.createUser(user); }
		catch (Exception e) {
			throw new UserNotFoundException();
		}
	}
}
