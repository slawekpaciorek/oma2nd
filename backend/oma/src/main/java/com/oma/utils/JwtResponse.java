package com.oma.utils;

import lombok.Data;

import java.io.Serializable;

@Data
public class JwtResponse implements Serializable {

    private final String token;

    public JwtResponse(String token) {
        this.token = token;
    }
}
