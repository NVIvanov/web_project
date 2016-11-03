package ru.web.project.services.impl;

import ru.web.project.model.entities.User;
import ru.web.project.services.UserService;

import javax.validation.constraints.NotNull;
import java.util.Collection;

/**
 * @author nivanov
 *         on 03.11.16.
 */
public class UserServiceImpl implements UserService {
    @Override
    public void createUser(@NotNull User user) {

    }

    @Override
    public void updateUser(@NotNull User user) {

    }

    @Override
    public void removeUser(@NotNull String email) {

    }

    @Override
    public void updatePassword(@NotNull String email, @NotNull String oldPassword, @NotNull String newPassword) {

    }

    @Override
    public void sendRecoverEmail(@NotNull String email) {

    }

    @Override
    public User getUserByEmail(@NotNull String email) {
        return null;
    }

    @Override
    public Collection<User> getUsers(@NotNull String siteRelativePath) {
        return null;
    }
}
