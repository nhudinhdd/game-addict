package com.player.data.gameaddict.model.response.auth;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRes {

    private String accessToken;
    private String refreshToken;
    private long accessTokenExpiredAt;
    private long refreshTokenExpiredAt;
}
