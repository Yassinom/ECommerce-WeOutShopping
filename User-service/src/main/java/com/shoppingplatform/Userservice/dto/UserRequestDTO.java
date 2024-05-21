package com.shoppingplatform.Userservice.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {

    private String username;
    private String password;
    private String email;
    private String phonenumber;
}

