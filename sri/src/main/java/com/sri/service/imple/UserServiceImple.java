package com.sri.service.imple;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sri.entity.User;
import com.sri.reporsitory.UserRepository;
import com.sri.service.UserService;


/**
 * Data persistence business logic layer
 * The interface-implementation architechture is required by the Spring framework (the multiple implementation is allowed)
 * The service implementations implement the actual methods and will be injected into required components(like other services or restAPI layer)
 * <p>
 * There are many common method between the services(like CRUD), so the common part is define in a BaseImple
 * This service implements get the common methods by extends the BaseService
 * The E is the Entity type of the service
 * <p>
 */
@Service
public class UserServiceImple extends BaseImple<User> implements UserService {

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void initParent() {
        repository = userRepository;
    }

	@Override
	public User getUserByName(String name) {
		return userRepository.findByName(name);
	}

	@Override
	public User getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User getUserByPhone(String phone) {
		return userRepository.findByPhone(phone);
	}

}
