package com.example.demo.dto.Auth;

import com.example.demo.dto.UserResponseDTO;

public record AuthResponseDTO (
    String token,
    UserResponseDTO user
){

}
