package com.player.data.gameaddict.model.request.auth;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginUserRequest {

    private String userName;
    private String password;
}
