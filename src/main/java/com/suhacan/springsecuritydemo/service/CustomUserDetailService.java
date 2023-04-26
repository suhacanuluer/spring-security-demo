package com.suhacan.springsecuritydemo.service;

import com.suhacan.springsecuritydemo.model.CustomUserDetail;
import com.suhacan.springsecuritydemo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByUsername(username)
                .map(CustomUserDetail::new)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found " + username));

        // OTHER OPTIONS TO DO THE SAME THING:
//        User user = userRepository.findByUsername(username);
//        if (user == null)
//            throw new UsernameNotFoundException("Username not found " + username);
//        return new CustomUserDetail(user);
        // *****************************************************
//        User user = userRepository.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("Username not found " + username));
//        return new CustomUserDetail(user);
    }

}
