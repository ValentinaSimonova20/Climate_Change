package com.simonova.ecoinformerapp.models.payload.response;

import lombok.Data;

import java.util.List;

/**
 * Authentication response
 * JWT info will be used in requests to different resources that requires authentication
 */
@Data
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String email;
    private List<String> roles;

    public JwtResponse(String accessToken, Long id, String username, String email, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }
}
