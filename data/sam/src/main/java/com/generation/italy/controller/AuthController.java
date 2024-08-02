package com.generation.italy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.italy.exception.UnauthorizedException;
import com.generation.italy.model.AuthRequest;
import com.generation.italy.model.AuthResponse;
import com.generation.italy.model.Role;
import com.generation.italy.model.User;
import com.generation.italy.service.RoleService;
import com.generation.italy.service.TokenService;
import com.generation.italy.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;
    @Autowired
private RoleService roleService; 
    // Endpoint per il login
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
    	// Trova l'utente nel database in base alla mail
    	User user = userService.findByEmail(authRequest.getEmail());
    	// Verifica se l'utente esiste e se la password è corretta
    	if (user != null && user.getPassword().equals(authRequest.getPassword())) {
    		// Genera un token e lo salva nel database
    		Role role = user.getRole();
    		String token = tokenService.createToken(user.getId()).getToken();
            return new ResponseEntity<AuthResponse>(new AuthResponse(token, role), HttpStatus.ACCEPTED);
        } else {
        	 // Se le credenziali sono errate, lancia un'eccezione di non autorizzato
            throw new UnauthorizedException();
        }
    }

    // Endpoint per il logout
    @PostMapping("/logout")
    //da rifare perché usa vecchia auth
    public void logout(HttpServletRequest request) {
    	String token = request.getSession().getAttribute("token").toString();
    	System.out.println(token);
        // Elimina il token dal database per effettuare il logout
    	tokenService.deleteByToken(token);
    	request.getSession().removeAttribute("token");
    	request.getSession().removeAttribute("logged");
    }
    
    @PostMapping("/register/{roleID}")
    public ResponseEntity<AuthResponse> userRegister(@RequestBody User user, @PathVariable Long roleID) {
        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setNomeCompleto(user.getNomeCompleto());
        newUser.setPassword(user.getPassword());
        newUser.setRole(roleService.getRoleById(roleID));
        newUser = userService.createUser(newUser);
        String token = tokenService.createToken(newUser.getId()).getToken();
        return new ResponseEntity<AuthResponse>(new AuthResponse(token, newUser.getRole()), HttpStatus.ACCEPTED);
    }
    
}