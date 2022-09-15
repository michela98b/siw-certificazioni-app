package it.uniroma3.siw.certificazioni.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.certificazioni.model.Credentials;
import it.uniroma3.siw.certificazioni.model.User;
import it.uniroma3.siw.certificazioni.service.CredentialsService;
import it.uniroma3.siw.certificazioni.service.UserSerivce;

import static it.uniroma3.siw.certificazioni.model.Credentials.DEFAULT_ROLE;

@Controller
public class StudentController {
	
	@Autowired
	UserSerivce userService;
	
	@Autowired
	CredentialsService credentialsService;

	@RequestMapping(value="/admin/students", method = RequestMethod.GET)
	public String getStudents(Model model) {
		List<Credentials> credentials = this.credentialsService.getRoleCredentials(DEFAULT_ROLE);
		List<User> students = new ArrayList<>();
		
		for(Credentials c: credentials) {
			students.add(c.getUser());
		}
		
		model.addAttribute("students", students);
		return "admin/students";
	}
	
	@RequestMapping(value="/admin/student", method = RequestMethod.GET)
	public String getStudentForm(Model model) {
		model.addAttribute("student", new User());
		model.addAttribute("email", "");
		return "admin/studentForm";
	}
	
	@RequestMapping(value="/admin/student/save", method = RequestMethod.POST)
	public String addEnrollment(@Valid @ModelAttribute("student") User user, BindingResult bindingResult, @ModelAttribute("email") String email, Model model) {
		
		if(email.isEmpty()) {
			return "admin/studentForm";
		}
		
		if(!bindingResult.hasErrors()) {
			
			Credentials credentials = new Credentials();
			credentials.setUser(user);
			credentials.setUsername(email);
			credentials.setPassword("test");
			credentials.setRole(DEFAULT_ROLE);

			this.userService.saveUser(user);
			this.credentialsService.saveCredentials(credentials);

			return "admin/home";

		}
		return "admin/studentForm";
	}
	
}
