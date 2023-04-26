package com.suhacan.springsecuritydemo.util;

import com.suhacan.springsecuritydemo.model.Role;
import com.suhacan.springsecuritydemo.model.User;
import com.suhacan.springsecuritydemo.repository.RoleRepository;
import com.suhacan.springsecuritydemo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor

@Component
public class SetupDataLoader implements ApplicationRunner {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder encoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        // create roles
        Role adminRole = Role.builder()
                .id(1L)
                .role_name("ADMIN")
                .build();

        Role userRole = Role.builder()
                .id(2L)
                .role_name("USER")
                .build();

        //save roles
        roleRepository.save(adminRole);
        roleRepository.save(userRole);

        //create admin roles list
        List<Role> adminRoles = new ArrayList<>();
        adminRoles.add(adminRole);
        adminRoles.add(userRole);

        //create admin
        User admin = User.builder()
                .id(3L)
                .username("admin")
                .password(encoder.encode("1234"))
                .roles(adminRoles)
                .build();
        userRepository.save(admin);

        //create user roles list
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(userRole);

        //create user
        User user = User.builder()
                .id(4L)
                .username("suhacan")
                .password(encoder.encode("1234"))
                .roles(userRoles)
                .build();
        userRepository.save(user);

        userRepository.findAll().forEach(usr -> {
            System.out.println(usr.toString());
        });

    }
}
