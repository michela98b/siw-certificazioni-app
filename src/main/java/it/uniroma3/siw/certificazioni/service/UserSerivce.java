package it.uniroma3.siw.certificazioni.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.certificazioni.repository.UserRepository;
import it.uniroma3.siw.certificazioni.model.User;

@Service
public class UserSerivce {

	@Autowired
	protected UserRepository userRepository;
	
	public User getUser(Long id) {
		Optional<User> result = this.userRepository.findById(id);
		return result.orElse(null);
	}
	
	public List<User> findAll(){
		List<User> users = new ArrayList<>();
		
		for(User u: this.userRepository.findAll()) {
			users.add(u);
		}
		return users;
	}


	@Transactional
	public User saveUser(User user) {
		return this.userRepository.save(user);
	}
}
