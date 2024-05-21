package com.shoppingplatform.Userservice.service;

import com.shoppingplatform.Userservice.UserServiceApplication;
import com.shoppingplatform.Userservice.model.UserModel;
import com.shoppingplatform.Userservice.model.UserRequestDTO;
import com.shoppingplatform.Userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<UserModel> getAllUsers(){
        return userRepository.findAll();
    }

    public UserModel getUserById(String id) {
        Optional<UserModel> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            throw new RuntimeException("User not found with id: " + id);
        }
    }

    public void createUser(UserRequestDTO userRequestDTO) {
        UserModel user = UserModel.builder()
                .username(userRequestDTO.getUsername())
                .password(userRequestDTO.getPassword())
                .email(userRequestDTO.getEmail())
                .phonenumber(userRequestDTO.getPhonenumber())
                .build();
        userRepository.save(user);
    }

    public UserModel updateUser(String id, UserModel userDetails){
        return userRepository.findById(id).map(userModel -> {
            userModel.setUsername(userDetails.getUsername());
            userModel.setPassword(userDetails.getPassword());
            userModel.setEmail(userDetails.getEmail());
            userModel.setPhonenumber(userDetails.getPhonenumber());
            return userRepository.save(userModel);
        }).orElseGet(() -> {
            userDetails.setId(id);
            return userRepository.save(userDetails);
        });
    }

    public void deleteUser(String  id){
        userRepository.deleteById(id);
    }

    public UserModel getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }



}
