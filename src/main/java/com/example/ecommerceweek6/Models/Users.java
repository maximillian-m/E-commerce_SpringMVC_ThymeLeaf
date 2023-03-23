package com.example.ecommerceweek6.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

@Data
@AllArgsConstructor
public class Users {
    private String firstname;
    private String username;
    private String email;
    private String phoneNumber;
    private String password;
}
