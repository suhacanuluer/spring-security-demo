package com.suhacan.springsecuritydemo.config;

import com.suhacan.springsecuritydemo.service.CustomUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final CustomUserDetailService customUserDetailService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf
                .ignoringAntMatchers("/h2-console/**")) // h2-console'a csrf isteği gönderme.
            .authorizeHttpRequests(auth -> auth
                .mvcMatchers("/admin").hasAuthority("ADMIN") // sadece admin rolüne sahip olanlar.
                .mvcMatchers("/dashboard").hasAnyAuthority("ADMIN", "USER") // admin ya da user rolüne sahip olanlar.
                .antMatchers("/h2-console/**", "/index").permitAll() // bu controllera gelen isteklerin tümüne izin ver.
                .anyRequest().authenticated() // diğer tüm isteklerin yetkisi olmalı.
            )
            .userDetailsService(customUserDetailService) // kullanıcı bilgilerini veritabanından al.
            .formLogin() // form login.
            .and()
            .httpBasic(); // basic authentication.

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
