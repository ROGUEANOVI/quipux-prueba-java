package com.quipux.prueba_java.service;

import com.quipux.prueba_java.model.AuthResponse;
import com.quipux.prueba_java.model.LoginRequest;
import com.quipux.prueba_java.model.RegisterRequest;

public interface AuthService {

    AuthResponse login(LoginRequest loginRequest);

    void register(RegisterRequest registerRequest);
}
