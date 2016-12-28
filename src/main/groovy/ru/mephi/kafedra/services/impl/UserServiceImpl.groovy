package ru.mephi.kafedra.services.impl

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Service
import ru.mephi.kafedra.data.entities.SystemUser
import ru.mephi.kafedra.data.repositories.UserRepository
import ru.mephi.kafedra.dto.SiteDTO
import ru.mephi.kafedra.dto.UserDTO
import ru.mephi.kafedra.services.AttachmentService
import ru.mephi.kafedra.services.SiteService
import ru.mephi.kafedra.services.UserService

/**
 * @author nivanov
 * on 26.12.16.
 */

@Service
class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repository

    @Autowired
    private AttachmentService attachmentService

    @Autowired
    private SiteService siteService

    @Override
    void createUser(UserDTO userDTO) {
        repository.findByUsername(userDTO.username).ifPresent {
            throw new IllegalStateException("User already exists")
        }
        def user = new SystemUser()
        user.name = userDTO.name
        user.password = userDTO.password
        user.username = userDTO.username
        repository.save(user)
        def site = new SiteDTO()
        site.owner = user.username
        site.relativePath = user.username
        siteService.createSite(site)
        attachmentService.createUserFolder()
    }

    @Override
    UserDTO getCurrentUser() {
        User principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal() as User
        Optional<SystemUser> userOpt = repository.findByUsername(principal.username)
        def userDTO = new UserDTO()
        if (userOpt.isPresent()) {
            userDTO.username = userOpt.get().username
            userDTO.name = userOpt.get().name
        } else
            throw new IllegalStateException()
        return userDTO
    }
}
