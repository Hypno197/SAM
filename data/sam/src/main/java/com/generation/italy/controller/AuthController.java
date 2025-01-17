package com.generation.italy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.italy.exception.UnauthorizedException;
import com.generation.italy.model.AuthRequest;
import com.generation.italy.model.AuthResponse;
import com.generation.italy.model.User;
import com.generation.italy.service.TokenService;
import com.generation.italy.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    // Endpoint per il login
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
    	// Trova l'utente nel database in base all'username
    	User user = userService.findByEmail(authRequest.getEmail());
    	// Verifica se l'utente esiste e se la password è corretta
    	if (user != null && user.getPassword().equals(authRequest.getPassword())) {
    		// Genera un token e lo salva nel database
    		String token = tokenService.createToken(user.getId()).getToken();
            return new ResponseEntity<AuthResponse>(new AuthResponse(token), HttpStatus.ACCEPTED);
        } else {
        	 // Se le credenziali sono errate, lancia un'eccezione di non autorizzato
            throw new UnauthorizedException();
        }
    }

    // Endpoint per il logout
    @PostMapping("/logout")
    public void logout(@RequestHeader("Authorization") String token) {
    	System.out.println("my token:" + token);
        // Elimina il token dal database per effettuare il logout
    	tokenService.deleteByToken(token);
    }
}