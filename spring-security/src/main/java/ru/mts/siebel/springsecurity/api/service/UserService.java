package ru.mts.siebel.springsecurity.api.service;

import ru.mts.siebel.springsecurity.model.User;
import ru.mts.siebel.springsecurity.model.UserCreateForm;

import java.util.Collection;
import java.util.Optional;

public interface UserService {

    Collection<User> getAllUsers();

    Optional<User> getUserById(long id);

    Optional<User> getUserByEmail(String email);

    User create(UserCreateForm form);

}
