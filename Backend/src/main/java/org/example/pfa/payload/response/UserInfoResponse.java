package org.example.pfa.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoResponse {
    private Long id;
    private String nom;
    private String email;
    private Long phone;
    private String password;
    private List<String> roles;



}