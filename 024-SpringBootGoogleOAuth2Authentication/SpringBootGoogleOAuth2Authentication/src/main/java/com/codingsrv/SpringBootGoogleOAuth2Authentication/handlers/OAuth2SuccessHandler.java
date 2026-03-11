package com.codingsrv.SpringBootGoogleOAuth2Authentication.handlers;

import com.codingsrv.SpringBootGoogleOAuth2Authentication.entities.User;
import com.codingsrv.SpringBootGoogleOAuth2Authentication.services.JwtService;
import com.codingsrv.SpringBootGoogleOAuth2Authentication.services.UserService;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final UserService userService;
    private final JwtService jwtService;

    @Value("${deploy.env}")
    private String deployEnv;  // injecting deploy environment


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException, java.io.IOException {
        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;  // casting Authentication to OAuth2AuthenticationToken
        DefaultOAuth2User oAuth2User = (DefaultOAuth2User) token.getPrincipal();

        String email = oAuth2User.getAttribute("email");

        User user = userService.getUserByEmail(email); // getUserByEmail() method throws null if user not found.
        // if user not found then create a new user using the builder pattern and save them
        if (user == null){
            User newUser = User.builder()
                    .name(oAuth2User.getAttribute("name"))
                    .email(email)
                    .build();
            user = userService.save(newUser);
        }
        String accessToken =  jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        Cookie cookie = new Cookie("refreshToken", refreshToken); // store refresh token in cookie
        cookie.setHttpOnly(true);
        cookie.setSecure("production".equals(deployEnv));
        response.addCookie(cookie);

        String frontEndUrl = "http://localhost:8080/home.html?token="+accessToken;

        getRedirectStrategy().sendRedirect(request,response,frontEndUrl);

//        response.sendRedirect(frontEndUrl);

        // if user with this email is present then log this user in console (optional)
        log.info(oAuth2User.getAttribute("email")); // retrieving the email on the console log


    }



}
