package ru.mts.siebel.springsecurity.service;

import ru.mts.siebel.springsecurity.api.service.UserService;
import ru.mts.siebel.springsecurity.model.User;
import ru.mts.siebel.springsecurity.model.UserCreateForm;
import ru.mts.siebel.springsecurity.api.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Collection<User> getAllUsers() {
        LOGGER.debug("Getting all users");
        return userRepository.findAll(Sort.by("email"));
    }

    @Override
    public Optional<User> getUserById(final long id) {
        LOGGER.debug("Getting user = {}", id);
        return Optional.of(userRepository.findById(id).get());
    }

    @Override
    public Optional<User> getUserByEmail(final String email) {
        LOGGER.debug("Getting user by email = {}", email.replaceFirst("@.*", "@***"));
        return userRepository.findOneByEmail(email);
    }

    @Override
    public User create(final UserCreateForm form) {
        User user = new User();
        user.setEmail(form.getEmail());
        user.setPasswordHash("{bcrypt}" + new BCryptPasswordEncoder().encode(form.getPassword()));
        user.setRole(form.getRole());
        return userRepository.save(user);
    }

}
