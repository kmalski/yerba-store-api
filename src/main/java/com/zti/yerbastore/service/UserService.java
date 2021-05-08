package com.zti.yerbastore.service;

import com.zti.yerbastore.exception.NotFoundException;
import com.zti.yerbastore.model.User;
import com.zti.yerbastore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User with email '" + email + "' does not exist."));
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void deleteByEmail(String email) {
        User user = findByEmail(email);

        userRepository.deleteById(user.getId());
    }

}
