package com.social_media.dev.service;


import com.social_media.dev.dto.UserLoginDto;
import com.social_media.dev.dto.UserRegistrationDto;
import com.social_media.dev.entity.User;
import com.social_media.dev.repository.UserRepository;
import com.social_media.dev.util.localization.Tokens;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User get(Long id) {
        var user = userRepository.findById(id);

        if (user.isEmpty() || user.get().getIsDeleted()) {
            throw new EntityNotFoundException(Tokens.M_USER_NOT_FOUND);
        }

        return user.get();
    }

    @Transactional
    public User registerUser(UserRegistrationDto registrationDto) {
        User user = new User();
        user.setUsername(registrationDto.getUsername());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        return userRepository.save(user);
    }

}
