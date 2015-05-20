package com.jellybelly.user.webmvc;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.jellybelly.user.beans.Contact;

@Controller
@SessionAttributes
public class HelloWorldController {

	@RequestMapping("/hello")
	public ModelAndView helloWorld() {

		String message = "Hello World, Spring 3.0!";
		System.out.println(message);
		return new ModelAndView("hello", "message", message);
	}


	@RequestMapping(value = "/addContact", method = RequestMethod.POST)
	public String addContact(@ModelAttribute("contact")
							Contact contact, BindingResult result) {
		
		System.out.println("First Name:" + contact.getFirstname() + 
					" Last Name:" + contact.getLastname());
		
		
		return "redirect:contacts.html";
	}
	
	@RequestMapping("/contacts")
	public ModelAndView showContacts() {
		
		return new ModelAndView("contact", "command", new Contact());
	}

}