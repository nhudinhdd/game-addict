package com.player.data.gameaddict.controller.auth;

import com.player.data.gameaddict.model.request.auth.LoginUserRequest;
import com.player.data.gameaddict.model.request.auth.RegisterUserRequest;
import com.player.data.gameaddict.model.response.auth.AuthenticationRes;
import com.player.data.gameaddict.model.response.common.MetaDataRes;
import com.player.data.gameaddict.service.auth.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping(value = "/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MetaDataRes<AuthenticationRes>> register(@ModelAttribute RegisterUserRequest request) throws IOException {
        MetaDataRes<AuthenticationRes> metaDataRes = authenticationService.registerUser(request);
        return new ResponseEntity<>(metaDataRes, HttpStatus.OK);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<MetaDataRes<AuthenticationRes>> login(@RequestBody LoginUserRequest request) {
        MetaDataRes<AuthenticationRes> metaDataRes = authenticationService.login(request);
        return new ResponseEntity<>(metaDataRes, HttpStatus.OK);
    }

    @PostMapping(value = "/refresh-token")
    public ResponseEntity<MetaDataRes<AuthenticationRes>> refreshToken(HttpServletRequest request) {
        MetaDataRes<AuthenticationRes> metaDataRes = authenticationService.refreshToken(request);
        return new ResponseEntity<>(metaDataRes, HttpStatus.OK);
    }
}
