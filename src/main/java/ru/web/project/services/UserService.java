package ru.web.project.services;

import ru.web.project.model.entities.User;

import javax.validation.constraints.NotNull;
import java.util.Collection;

/**
 * Сервис для управления пользователями системы
 *
 * @author nivanov
 *         on 03.11.16.
 */
public interface UserService {
    /**
     * Создает пользователя по заполненным значениям
     *
     * @param user пользователь для сохранения
     * @throws javax.validation.ValidationException в случае несоответствия ограничениям,
     * @see User
     */
    void createUser(@NotNull User user);

    /**
     * Обновляет пользователя по заполненным значениям
     *
     * @param user пользователь для обновления
     * @throws javax.validation.ValidationException в случае несоответствия ограничениям,
     * @see User
     */
    void updateUser(@NotNull User user);

    /**
     * Удаляет пользователя из системы
     *
     * @param email электронная почта пользователя
     * @throws IllegalArgumentException в случае отсутствия пользователя с указанной почтой
     */
    void removeUser(@NotNull String email);

    /**
     * Обновляет пароль для пользователя с указанной электронной почтой
     *
     * @param email       электронная почта пользователя
     * @param oldPassword старый пароль
     * @param newPassword новый пароль
     */
    void updatePassword(@NotNull String email, @NotNull String oldPassword, @NotNull String newPassword);

    /**
     * Посылает ссылку для восстановления пароля по электронной почте
     *
     * @param email электронная почта пользователя
     */
    void sendRecoverEmail(@NotNull String email);

    /**
     * Получает объект пользователя с указанной электронной почтой
     *
     * @param email электронная почта пользователя
     * @return объект класса {@link User}
     */
    @NotNull
    User getUserByEmail(@NotNull String email);

    /**
     * Получает список пользователей, относящихся к сайту
     *
     * @param siteRelativePath относительный путь сайта
     * @return коллекция объектов {@link User}
     */
    @NotNull
    Collection<User> getUsers(@NotNull String siteRelativePath);
}
