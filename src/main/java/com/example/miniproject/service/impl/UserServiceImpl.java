package com.example.miniproject.service.impl;

import com.example.miniproject.domain.user.UserRepository;
import com.example.miniproject.dto.request.RequestUserDto;
import com.example.miniproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    public boolean registerUser(RequestUserDto dto){
        dto.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));

        userRepository.save(dto.toUser());

        return true;
    }
}
