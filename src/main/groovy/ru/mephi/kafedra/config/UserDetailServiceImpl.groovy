package ru.mephi.kafedra.config

import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

/**
 * @author nivanov
 * on 11.04.17.
 */

@Service
class UserDetailServiceImpl implements UserDetailsService {
    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //TODO переделать в соответствии с ролевой моделью
        return new User("user", "pass", Collections.singletonList(new SimpleGrantedAuthority("USER")))
    }
}
