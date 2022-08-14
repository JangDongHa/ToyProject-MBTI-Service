package com.example.miniproject.service;

import com.example.miniproject.domain.user.User;
import com.example.miniproject.domain.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private final String USER_INIT_SQL = "classpath:db/userInit.sql";

    @BeforeEach
    public void settingData(){
        String username = "test";
        String password = bCryptPasswordEncoder.encode("passwordTest");

        User user = User.builder().username(username).password(password).build();
        userRepository.save(user);
    }

    @Test
    @Transactional
    public void registerUserTest(){
        // Given
        User user = User.builder().username("test2").password(bCryptPasswordEncoder.encode("passwordTest")).build();
        // When
        User userPS = userRepository.save(user);
        // Then
        assertEquals(user.getUsername(), userPS.getUsername());
    }

    @Sql(USER_INIT_SQL)
    @Test
    @Transactional
    public void updateUserTest(){
        // Given
        User user = User.builder().id(1L).username("changeName").password(bCryptPasswordEncoder.encode("changePassword")).build();

        // When
        User userPS = userRepository.save(user);

        // Then
        assertEquals(user.getUsername(), userPS.getUsername());
    }

    @Sql(USER_INIT_SQL)
    @Test
    @Transactional
    public void deleteUserTest(){
        // Given
        long id = 1;
        // When
        userRepository.delete(userRepository.findById(id).get());
        // Then
        assertFalse(userRepository.findById(id).isPresent());
    }

    @Sql(USER_INIT_SQL)
    @Test
    @Transactional
    public void getUserTest(){
        // Given
        String username = "test";
        // When
        User userPS = userRepository.findById(1L).get();
        // Then
        assertEquals(username ,userPS.getUsername());
    }


}
