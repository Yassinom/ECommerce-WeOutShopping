package com.shoppingplatform.Userservice.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserModel {

    @Id
    private String id;

    private String username;
    private String password;
    private String email;
    private String phonenumber;
}

