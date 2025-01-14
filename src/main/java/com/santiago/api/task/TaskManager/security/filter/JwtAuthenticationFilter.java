package com.santiago.api.task.TaskManager.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.santiago.api.task.TaskManager.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.rmi.MarshalledObject;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.santiago.api.task.TaskManager.security.TokenJwtConfig.*;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;
    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        User user;
        String username = null;
        String password = null;
        System.out.println("En el intento de autenticacion");
        try{
            user = new ObjectMapper().readValue(request.getInputStream(),User.class);
            username =user.getName();
            System.out.println(username+"uasurio nomrbe");
            password =user.getPassword();
        }catch (Exception e){
            e.printStackTrace();
            throw new AuthenticationException("Error al procesar la solicitud de autenticación") {};
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
                password);
        System.out.println(authenticationToken);
        return authenticationManager.authenticate(authenticationToken);

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) authResult.getPrincipal();
        System.out.println(" token de autenticacion");

        String username = user.getUsername();
        Collection<? extends GrantedAuthority> roles =authResult.getAuthorities();
        Claims claims = Jwts.claims().add("authorities",new ObjectMapper().writeValueAsString(roles))
                .add("username",username).build();
        String token =Jwts.builder()
                .subject(username)
                .claims(claims)
                .expiration(new Date(System.currentTimeMillis()+36000))
                .issuedAt(new Date())
                .signWith(SECRET_KEY)
                .compact();
        response.addHeader(HEADER_AUTHORIZATION, PREFIX_TOKEN+token);
        Map<String,String>body = new HashMap<>();
        body.put("token",token);
        body.put("username",username);
        body.put("message",String.format("Hola %s has iniciado sesion con exito!",username));
        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setContentType(CONTENT_TYPE);
        response.setStatus(200);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        Map<String,String>body = new HashMap<>();
        body.put("message","Error en la autenticacion username o password incorrects");
        body.put("error",failed.getMessage());
        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setStatus(401);
        response.setContentType(CONTENT_TYPE);
    }
}
