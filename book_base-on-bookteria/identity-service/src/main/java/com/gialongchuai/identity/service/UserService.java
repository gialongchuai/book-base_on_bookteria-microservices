package com.gialongchuai.identity.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.gialongchuai.event.dto.NotificationEvent;
import com.gialongchuai.identity.mapper.ProfileMapper;
import com.gialongchuai.identity.repository.httpclient.ProfileClient;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gialongchuai.identity.dto.request.UserCreationRequest;
import com.gialongchuai.identity.dto.request.UserUpdationRequest;
import com.gialongchuai.identity.dto.response.UserResponse;
import com.gialongchuai.identity.entity.Role;
import com.gialongchuai.identity.entity.User;
import com.gialongchuai.identity.exception.AppException;
import com.gialongchuai.identity.exception.ErrorCode;
import com.gialongchuai.identity.mapper.UserMapper;
import com.gialongchuai.identity.repository.RoleRepository;
import com.gialongchuai.identity.repository.UserRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    RoleRepository roleRepository;
    ProfileClient profileClient;
    ProfileMapper profileMapper;
    KafkaTemplate<String, NotificationEvent> kafkaTemplate;

    public UserResponse createUser(UserCreationRequest userCreationRequest) {
        User user = userMapper.toUser(userCreationRequest);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Set<Role> roles = new HashSet<>();
        roles.add(Role.builder().name(com.gialongchuai.identity.enums.Role.USER.name()).build());
        user.setRoles(roles);

        try {
            user = userRepository.save(user);

            var profileRequest = profileMapper.toProfileCreationRequest(userCreationRequest);
            profileRequest.setUserId(user.getId());


            profileClient.createProfile(profileRequest);

            NotificationEvent event = NotificationEvent.builder()
                    .channel("EMAIL")
                    .recipient(userCreationRequest.getEmail())
                    .templateCode("WELCOME_EMAIL")
                    .subject("Welcome to our platform!")
                    .body("Hello " + userCreationRequest.getUsername() + ", welcome to our service!")
                    .build();

            kafkaTemplate.send("onboard-successful", event);
        } catch (DataIntegrityViolationException exception) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        return userMapper.toUserResponse(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getAllUser() {
        log.info("I am joining in method (getAllUser)!");
        // return userMapper.toUsersResponse(userRepository.findAll());
        var users = userRepository.findAll();
        return users.stream().map(userMapper::toUserResponse).toList();
    }

    @PostAuthorize("hasRole('USER')")
    public UserResponse getUser(String userId) {
        log.info("I am joining in method (getUser)!");
        return userMapper.toUserResponse(
                userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED)));
    }

    // chỉ cần kèm theo 1 token thì sẽ cho biết info người gửi thông qua token, từ name đó được lấy từ sub: ví dụ sub có
    // admin thì tìm username admin.
    public UserResponse getMyInfo() {
        var context = SecurityContextHolder.getContext();
        var name = context.getAuthentication().getName();
        return userMapper.toUserResponse(
                userRepository.findByUsername(name).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED)));
    }

    public UserResponse updateUser(String userId, UserUpdationRequest userUpdationRequest) {
        User user = userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        userMapper.updateUser(user, userUpdationRequest);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setRoles(new HashSet<>(roleRepository.findAllById(userUpdationRequest.getRoles())));

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }
}
