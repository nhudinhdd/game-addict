package com.player.data.gameaddict.model.request.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class RegisterUserRequest {

    private String userName;
    private String password;
    private MultipartFile avatar;
    @JsonIgnore
    private String role;
}
