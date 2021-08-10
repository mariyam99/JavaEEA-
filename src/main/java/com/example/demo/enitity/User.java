package com.example.demo.enitity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "user")
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Username is mandatory")
    @Column(nullable = false)
    private String userName;

    @NotBlank(message = "Email is mandatory")
    @Column(unique = true,nullable = false)
    private String email;

    @NotBlank(message = "Password is mandatory")
    @Column(nullable = false)
    private String password;

    @NotBlank(message = "Phone Number is mandatory")
    @Column(nullable = false)
    private String tel;

    @NotBlank(message = "Date Of Birth is mandatory")
    @Column(nullable = false)
    private Date dateOfBirth;

    @NotBlank(message = "Role is mandatory")
    private String role;


    public boolean validatePassword(String rawPassword, String encryptedPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(rawPassword, encryptedPassword);
    }



}
