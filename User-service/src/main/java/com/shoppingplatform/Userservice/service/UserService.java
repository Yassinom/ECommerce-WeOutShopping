package com.shoppingplatform.Userservice.service;

import com.shoppingplatform.Userservice.UserServiceApplication;
import com.shoppingplatform.Userservice.dto.AuthVerificationDTO;
import com.shoppingplatform.Userservice.model.UserModel;
import com.shoppingplatform.Userservice.model.UserRequestDTO;
import com.shoppingplatform.Userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
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

    public static String hashPassword(String password) {
        try {
            // Obtain MessageDigest instance for SHA-256
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // Convert password string to byte array
            byte[] passwordBytes = password.getBytes();

            // Update MessageDigest with password bytes
            md.update(passwordBytes);

            // Generate SHA-256 hash
            byte[] hashedBytes = md.digest();

            // Encode hashed bytes to Base64
            String hashedPassword = Base64.getEncoder().encodeToString(hashedBytes);

            return hashedPassword; // Return Base64 encoded SHA-256 hash
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null; // Return null in case of exception
        }
    }

    public void createUser(UserRequestDTO userRequestDTO) {
        Optional<UserModel> userModelOptional = userRepository.findAll().stream() // Fetch all carts and stream
                .filter(user -> user.getEmail().equals(userRequestDTO.getEmail())) // Filter by userId
                .findFirst();
        if(userModelOptional.isEmpty()) {
            UserModel user = UserModel.builder()
                    .username(userRequestDTO.getUsername())
                    .password(hashPassword(userRequestDTO.getPassword()))
                    .email(userRequestDTO.getEmail())
                    .phonenumber(userRequestDTO.getPhonenumber())
                    .build();
            userRepository.save(user);
        } else {throw new RuntimeException("user with mail : " + userRequestDTO.getEmail() + " already exist") ;}
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


    public String authUser(AuthVerificationDTO userDTO) {
        Optional<UserModel> userModelOptional = userRepository.findAll().stream() // Fetch all carts and stream
                .filter(user -> user.getEmail().equals(userDTO.getEmail())) // Filter by userId
                .findFirst();
        if (userModelOptional.isPresent()){
            UserModel userModel = userModelOptional.get();
            String encodedArrivedPassword = hashPassword(userDTO.getPassword());
            System.out.println(encodedArrivedPassword);
            if (encodedArrivedPassword.equals(userModel.getPassword())){
                return "authenticated";
            } else {return "not_authenticated";}
        }else {throw new RuntimeException("no user found with email" + userDTO.getEmail());}
    }
}
