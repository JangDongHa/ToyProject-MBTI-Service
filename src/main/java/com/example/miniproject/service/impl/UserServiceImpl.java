package com.example.miniproject.service.impl;

import com.example.miniproject.domain.user.User;
import com.example.miniproject.domain.user.UserRepository;
import com.example.miniproject.dto.request.RequestUpdateUserDto;
import com.example.miniproject.dto.request.RequestUserDto;
import com.example.miniproject.dto.response.UserDto;
import com.example.miniproject.exception.ExceptionNamingHandler;
import com.example.miniproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.DataFormatException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional
    public boolean registerUser(RequestUserDto dto){
        checkUsernameAndPassword(dto.getUsername(), dto.getPassword());
        dto.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));

        userRepository.save(dto.toUser());

        return true;
    }

    private void checkUsernameAndPassword(String username, String password){
        if (!(username.length() >= 5 && username.length() <= 20 && !findStr("[^a-zA-Z0-9]", username))) // 5~20, 영문 or 숫자만 허용
            throw new IllegalArgumentException(ExceptionNamingHandler.USERNAME_ERROR);

        if (!(password.length() >= 8 && findStr("[a-zA-Z]", password) && findStr("[0-9]", password))) // 8+, 영문 and 숫자만 허용
            throw new IllegalArgumentException(ExceptionNamingHandler.PASSWORD_ERROR);
    }

    private boolean findStr(String regex, String str){
        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(str);
        return m.find();
    }

    @Override
    @Transactional
    public boolean updateUser(RequestUpdateUserDto dto, String usernameTK){
        User userPS = userRepository.findByUsername(usernameTK).orElseThrow(IllegalArgumentException::new);

        if (bCryptPasswordEncoder.matches(dto.getPastPassword(), userPS.getPassword())){
            dto.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
            userRepository.save(dto.toUser(userPS));
        }
        else
            throw new SecurityException();

        return true;
    }

    @Override
    @Transactional
    public boolean deleteUser(String usernameTK){
        User userPS = userRepository.findByUsername(usernameTK).orElseThrow(IllegalArgumentException::new);

        userRepository.delete(userPS);

        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto getUser(String usernameTK){
        User userPS = userRepository.findByUsername(usernameTK).orElseThrow(IllegalArgumentException::new);
        return new UserDto(userPS);
    }


}
