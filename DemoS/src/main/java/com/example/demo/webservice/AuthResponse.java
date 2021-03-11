package com.example.demo.webservice;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponse {
    private String jwt;
    private String errorCode;
}