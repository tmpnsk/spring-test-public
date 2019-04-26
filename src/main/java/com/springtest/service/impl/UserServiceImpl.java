package com.springtest.service.impl;

import com.springtest.model.News;
import com.springtest.model.User;
import com.springtest.model.enums.Role;
import com.springtest.repo.NewsRepository;
import com.springtest.repo.UserRepository;
import com.springtest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService, AuthenticationProvider {

    @Qualifier("userRepository")
    @Autowired
    private UserRepository userRepository;

    @Qualifier("newsRepository")
    @Autowired
    private NewsRepository newsRepository;

    @Override
    public List<User> getAll() {

        return userRepository.findAll();
    }

    @Override
    public void saveRecordFromRequest(HttpServletRequest request) {
        User user = new User();


        String firstName = request.getParameter("firstName");
        String secondName = request.getParameter("secondName");


        user.setFirstName(firstName);
        user.setSecondName(secondName);
        user.setRole(Role.ROLE_USER);


        userRepository.save(user);
    }

    @Override
    public void saveEditFromRequest(HttpServletRequest request) {
        User user = new User();
        Long id = Long.valueOf(request.getParameter("id"));

        String firstName = request.getParameter("firstName");
        String secondName = request.getParameter("secondName");

        user.setId(id);
        user.setFirstName(firstName);
        user.setSecondName(secondName);


        userRepository.save(user);
    }

    @Override
    public User getOne(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public void delete(Long id) {
        userRepository.delete(id);
    }

    @Override
    public List<News> getNewsByUser(Long id) {
        return newsRepository.findByUser(id);
    }

    @Override
    public boolean updatePassword(String newPassword, String currentPassword, Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        Role role = user.getRole();

        if (role.equals(Role.ROLE_ADMIN) && !user.getId().equals(id)) {
            user = userRepository.findOne(id);
            user.setPassword(bCryptPasswordEncoder.encode(newPassword));
            return true;
        }
         if (isCredentialsValid(user, currentPassword)) {

            user.setPassword(bCryptPasswordEncoder.encode(newPassword));
            return true;
        } else {
             return false;
         }
    }

    /* SECURITY */

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login = authentication.getName(),
                password = authentication.getCredentials().toString();
        User user = userRepository.findByLogin(login);

        if (isCredentialsValid(user, password)) {
            SimpleGrantedAuthority userAuthority = new SimpleGrantedAuthority(user.getRole().name());   // берём роль которая на него назначена
            List<GrantedAuthority> grantedAuthorities = Collections.singletonList(userAuthority);

            return new UsernamePasswordAuthenticationToken(user, password, grantedAuthorities); // авторизуем пользователя
        } else {
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

    private boolean isCredentialsValid(User user, String password) {
        return user != null && bCryptPasswordEncoder.matches(password, user.getPassword());    // подтверждаем валидность данных для авторизации
    }
}