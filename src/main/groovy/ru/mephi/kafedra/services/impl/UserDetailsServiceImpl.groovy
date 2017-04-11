package ru.mephi.kafedra.services.impl

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.mephi.kafedra.data.entities.SystemUser
import ru.mephi.kafedra.data.repositories.UserRepository

/**
 * @author Nikolay Ivanov
 */
@Service
class UserDetailsServiceImpl implements UserDetailsService {
    private static final String USER_NOT_FOUND = "User with such username not found"

    @Autowired
    private UserRepository userRepository

    Logger logger = LoggerFactory.getLogger(UserDetailsService.class)

    @Override
    @Transactional(readOnly = true)
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.debug(username)
        Optional<SystemUser> userOpt = userRepository.findByUsername(username)
        if (userOpt.isPresent()) {
            UserDetails details = buildSystemUser(userOpt.get())
            return details
        } else
            throw new UsernameNotFoundException(USER_NOT_FOUND)
    }

    private static UserDetails buildSystemUser(SystemUser user) {
        Set<GrantedAuthority> authorities = new HashSet<>()
        authorities.add(new SimpleGrantedAuthority("USER"))
        return new User(user.username, user.password, authorities)
    }
}
