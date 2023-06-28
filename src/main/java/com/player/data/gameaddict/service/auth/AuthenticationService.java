package com.player.data.gameaddict.service.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.player.data.gameaddict.common.enums.ApplicationRole;
import com.player.data.gameaddict.common.enums.MetaDataEnum;
import com.player.data.gameaddict.entity.User;
import com.player.data.gameaddict.exception.ApplicationAuthenticationException;
import com.player.data.gameaddict.model.request.auth.LoginUserRequest;
import com.player.data.gameaddict.model.request.auth.RegisterUserRequest;
import com.player.data.gameaddict.model.response.auth.AuthenticationRes;
import com.player.data.gameaddict.model.response.common.MetaDataRes;
import com.player.data.gameaddict.repository.auth.UserRepository;
import com.player.data.gameaddict.service.AwsS3Service;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.Meta;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AwsS3Service awsS3Service;
    private final UserRepository userRepository;
    @Value("${application.security.jwt.access-expiration}")
    private long accessExpiration;
    @Value("${application.security.jwt.refresh-token}")
    private long refreshExpiration;
    public MetaDataRes<AuthenticationRes> registerUser(RegisterUserRequest request) throws IOException {
        log.info("Start register user with request = {}", request);
        Optional<User> userOptional = userRepository.findByUserName(request.getUserName());
        if (userOptional.isPresent()) {
            log.warn("Register user failed: User name has been exits");
            return new MetaDataRes<>(MetaDataEnum.USER_NAME_EXITS);
        }
        User user = new User(UUID.randomUUID().toString(), request.getUserName(), passwordEncoder.encode(request.getPassword()),
                awsS3Service.upload(request.getAvatar()), ApplicationRole.MANAGER.name());
        log.info("Start save user = {}", user);
        userRepository.save(user);
        log.info("Finish save user with userID = {}, role ={}", user.getUserID(), user.getRole());
        long accessTokenExpiredAt = System.currentTimeMillis() + accessExpiration;
        long refreshTokenExpiredAt = System.currentTimeMillis() + refreshExpiration;
        String accessToken = jwtService.generateToken(user.getUserName(), accessTokenExpiredAt);
        String refreshToken = jwtService.generateToken(user.getUserName(), refreshTokenExpiredAt);
        log.info("Finish register user");
        return new MetaDataRes<>(MetaDataEnum.SUCCESS, new AuthenticationRes(accessToken, refreshToken, accessTokenExpiredAt, refreshTokenExpiredAt));
    }

    public MetaDataRes<AuthenticationRes> login(LoginUserRequest loginUserRequest) {
        log.info("Start login with loginRequest={}", loginUserRequest);
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUserRequest.getUserName(),
                loginUserRequest.getPassword())); // validate login request
        long accessTokenExpiredAt = System.currentTimeMillis() + accessExpiration;
        long refreshTokenExpiredAt = System.currentTimeMillis() + refreshExpiration;
        String accessToken = jwtService.generateToken(loginUserRequest.getUserName(), accessTokenExpiredAt);
        String refreshToken = jwtService.generateToken(loginUserRequest.getUserName(), refreshTokenExpiredAt);
        log.info("Finish login user");
        return new MetaDataRes<>(MetaDataEnum.SUCCESS, new AuthenticationRes(accessToken, refreshToken, accessTokenExpiredAt,
                refreshTokenExpiredAt));
    }

    public MetaDataRes<AuthenticationRes> refreshToken(HttpServletRequest request) {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new ApplicationAuthenticationException("Unauthorized");
        }
        final String refreshToken = authHeader.substring(7);
        final String userName = jwtService.extractUserNameByJwt(refreshToken);
        if (userName != null) {
            Optional<User> userOptional = userRepository.findByUserName(userName);
            if (userOptional.isPresent() && jwtService.isTokenValid(refreshToken, userOptional.get().getUserName())) {
                long accessTokenExpiredAt = System.currentTimeMillis() + accessExpiration;
                String accessToken = jwtService.generateToken(userOptional.get().getUserName(), accessTokenExpiredAt);
                AuthenticationRes authResponse = new AuthenticationRes(accessToken, refreshToken, accessTokenExpiredAt,
                        jwtService.extractExpiredByJwt(refreshToken));
                return new MetaDataRes<>(MetaDataEnum.SUCCESS, authResponse);
            }
        }
        throw new ApplicationAuthenticationException("Unauthorized");
    }

    public MetaDataRes<?> logout(String accessToken, String refreshToken) {
        return null; // todo
    }
}
