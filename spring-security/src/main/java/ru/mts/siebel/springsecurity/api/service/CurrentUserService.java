package ru.mts.siebel.springsecurity.api.service;

import ru.mts.siebel.springsecurity.model.CurrentUser;

public interface CurrentUserService {

    boolean canAccessUser(CurrentUser currentUser, Long userId);

}
