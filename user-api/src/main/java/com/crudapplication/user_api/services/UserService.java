package com.crudapplication.user_api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crudapplication.user_api.dto.UserDTO;
import com.crudapplication.user_api.dto.mapper.UserMapper;
import com.crudapplication.user_api.model.User;
import com.crudapplication.user_api.model.exception.InvalidEmailException;
import com.crudapplication.user_api.model.exception.ResourceNotFoundException;
import com.crudapplication.user_api.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper mapper;

    public List<UserDTO> getUsers() {

        List<User> users = userRepository.findAll();
        return mapper.toDto(users);
    }

    public Optional<UserDTO> getUserById(int id) {

        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new ResourceNotFoundException(
                String.format("ID:{%d} Not Found", id));
        }

        UserDTO dto = mapper.toDto(user);

        return Optional.of(dto);
    }

    public UserDTO createUser(User user) {

        boolean userEmailVerify = VerifyEmail.verifyEmail(user.getEmail());

        if (!userEmailVerify) {
            throw new InvalidEmailException("Invalid Email");
        }

        userRepository.save(user);
        return mapper.toDto(user);
    }

    public void deleteUser(int id) {

        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new ResourceNotFoundException(
                String.format("ID:{%d} Not Found", id));
        }

        userRepository.deleteById(id);
    } 
    
    public UserDTO updateUser(int id, User user) {

        Optional<User> optUser = userRepository.findById(id);
        boolean userEmailVerify = VerifyEmail.verifyEmail(user.getEmail());

        if (optUser.isEmpty()) {
            throw new ResourceNotFoundException(
                String.format("ID:{%d} Not Found", id));
        } 
        else if (!userEmailVerify) {
            throw new InvalidEmailException("Invalid Email");
        }

        user.setId(id);
        userRepository.save(user);

        return mapper.toDto(user);
    }
}
