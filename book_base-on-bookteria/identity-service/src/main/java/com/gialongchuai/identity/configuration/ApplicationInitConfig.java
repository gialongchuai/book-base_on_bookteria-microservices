package com.gialongchuai.identity.configuration;

import java.util.HashSet;
import java.util.Set;

import com.gialongchuai.identity.entity.Role;
import com.gialongchuai.identity.entity.User;
import com.gialongchuai.identity.repository.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationInitConfig {
    PasswordEncoder passwordEncoder;

    // code mặc định run ứng nếu không thấy tài khoản admin thì mặc định tạo tài khoản (focus log)
    @Bean
    @ConditionalOnProperty(
            prefix = "spring",
            value = "datasource.driverClassName",
            havingValue = "com.mysql.cj.jdbc.Driver")
    ApplicationRunner applicationRunner(UserRepository userRepository) {
        // log.info("Dang chay Init Config");
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                Set<Role> roles = new HashSet<>();
                roles.add(Role.builder()
                        .name(com.gialongchuai.identity.enums.Role.ADMIN.name())
                        .build());
                User user = User.builder()
                        .roles(roles)
                        .username("admin")
                        .password(passwordEncoder.encode("admin"))
                        .build();

                userRepository.save(user);
                log.warn("Admin has been created with default username & password: admin. Please change it!");
            }
        };
    }
}
