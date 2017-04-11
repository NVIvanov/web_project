package ru.mephi.kafedra.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import ru.mephi.kafedra.domain.data.entities.SystemUser
import ru.mephi.kafedra.domain.services.UserService

/**
 * @author nivanov
 * on 11.04.17.
 */

@Service
class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    UserService userService

    @Value('${admins}')
    List<String> adminUsernameList

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<SystemUser> userOptional = userService.findUser(username)
        if (userOptional.isPresent()) {
            SystemUser user = userOptional.get()
            if (adminUsernameList.contains(user.username))
                return new User(user.username, user.password, Collections.singletonList(new SimpleGrantedAuthority("ADMIN")))
            return new User(user.username, user.password, Collections.singletonList(new SimpleGrantedAuthority("USER")))
        }
        throw new UsernameNotFoundException("User not found")
    }
}
