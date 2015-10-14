package pl.offersjpa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.offersjpa.model.User;

@Controller
@RequestMapping("/useradd")
public class UserAddController {
	
	 @RequestMapping(value="", method=RequestMethod.GET)
	    public String greetingForm(Model model) {
	        model.addAttribute("user", new User());
	        return "useradd";
	    }
}
