package ru.mts.siebel.springsecurity.service;

import ru.mts.siebel.springsecurity.api.service.CurrentUserService;
import ru.mts.siebel.springsecurity.model.CurrentUser;
import ru.mts.siebel.springsecurity.enumerated.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserServiceImpl implements CurrentUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrentUserDetailsService.class);

    @Override
    public boolean canAccessUser(final CurrentUser currentUser, final Long userId) {
        LOGGER.debug("Checking if user={} has access to user={}", currentUser, userId);
        return currentUser != null
                && (currentUser.getRole() == Role.ADMIN || currentUser.getId().equals(userId));
    }

}
