package com.zti.yerbastore.service;

import com.zti.yerbastore.exception.NotFoundException;
import com.zti.yerbastore.model.User;
import com.zti.yerbastore.model.UserRole;
import com.zti.yerbastore.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository, passwordEncoder);
    }

    @Test
    void testFindAll() {
        User testUser = new User("fn", "ln", "email", "pass", UserRole.ADMIN);
        when(userService.findAll()).thenReturn(Collections.singletonList(testUser));

        List<User> result = userService.findAll();

        assertEquals(1, result.size());
        assertEquals(testUser, result.get(0));
    }

    @Test
    void testFindByEmailThrows() {
        when(userRepository.findByEmail("test@email.com")).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> userService.findByEmail("test@email.com"));
    }
}