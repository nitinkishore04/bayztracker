package com.server.bayztracker.entity.auth;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class LoginResponse {

    @NonNull
    private String token;

}
