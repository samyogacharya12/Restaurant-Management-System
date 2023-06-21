package com.example.securityservice.service;

import com.example.securityservice.dto.CustomUserDetails;
import com.example.securityservice.dto.UserDto;
import com.example.securityservice.entity.User;
import com.example.securityservice.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService, UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto save(UserDto userDto) {
        logger.info("registering user");
        Optional<User> optionalUser = this.userRepository.findByName(userDto.getName());
        if (optionalUser.isPresent()) {
            throw new RuntimeException("user already exist with this username");
        }
        User user = new User();
        user.setName(userDto.getName());
        user.setPassword(this.passwordEncoder.encode(userDto.getPassword()));
        user = this.userRepository.save(user);
        if (Objects.nonNull(user.getId())) {
            return userDto;
        }
        return null;
    }

    @Override
    public UserDto findByUserName(String userName) {
        Optional<User> user = this.userRepository.findByName(userName);
        UserDto userDto = new UserDto();
        if (user.isPresent()) {
            userDto.setName(user.get().getName());
            userDto.setPassword(user.get().getPassword());
        }
        return userDto;
    }

    @Override
    public String generateToken(String username) {
        return this.jwtService.generateToken(username);
    }

    @Override
    public void validateToken(String token) {
        this.jwtService.validateToken(token);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("fetching user by username");
        Optional<User> user = this.userRepository.findByName(username);
        return user.map(CustomUserDetails::new).orElseThrow(()
            -> new UsernameNotFoundException("user not found with name :" + username));
    }
}
