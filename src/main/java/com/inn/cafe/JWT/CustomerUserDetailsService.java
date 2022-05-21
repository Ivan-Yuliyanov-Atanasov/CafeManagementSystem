package com.inn.cafe.JWT;

import com.inn.cafe.dao.UserDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@RequiredArgsConstructor
@Slf4j
@Service
public class CustomerUserDetailsService implements UserDetailsService {

    private final UserDao userDao;

    private com.inn.cafe.POJO.User userDetail;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("Inside loadUserByUsername {}",email);
        userDetail = userDao.findByEmail(email);
        if (userDetail != null) {
            return new User(userDetail.getEmail(), userDetail.getPassword(), new ArrayList<>());
        }
        throw new UsernameNotFoundException("User not found.");
    }

    public com.inn.cafe.POJO.User getUserDetail() {
        return userDetail;
    }
}
