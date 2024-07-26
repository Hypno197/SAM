package com.generation.italy.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.generation.italy.model.Role;
import com.generation.italy.model.Token;
import com.generation.italy.model.User;
import com.generation.italy.repository.TokenRepository;
import com.generation.italy.repository.UserRepository;

import jakarta.transaction.Transactional;

/* 
 * In Spring Boot, l'annotazione @Transactional viene utilizzata per gestire le transazioni in un'applicazione Spring Boot e 
 * per definire un ambito della transazione. 
 * Questa annotazione può essere applicata a livello di classe o a livello di metodo. 
 * Fornisce affidabilità e coerenza dei dati. Quando un metodo è indicato con l'annotazione @Transactional, 
 * indica che quel particolare metodo deve essere eseguito nel contesto di quella transazione. 
 * Se la transazione ha esito positivo, le modifiche apportate al database vengono confermate, se una transazione fallisce, 
 * tutte le modifiche apportate a quella particolare transazione possono essere ripristinate e ciò garantirà che il database rimanga in uno 
 * stato coerente.*/
@Transactional
@Service
public class TokenService {

    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private UserRepository userRepository;
    // Crea e salva un nuovo token per un utente
    public Token createToken(Long user_id) {
        Token token = new Token();
        token.setUser_id(user_id);
        token.setToken(generateToken());
        token.setCreatedDate(new Date());
        return tokenRepository.save(token);
    }

    public Token findByToken(String token) {
        return tokenRepository.findByToken(token);
    }

    public void deleteByToken(String token) {
        tokenRepository.deleteByToken(token);
    }

    private String generateToken() {
        // Logica per generare un token (esempio semplice)
    	/* Un UUID è un numero a 128 bit che viene generalmente rappresentato 
    	 * come una stringa esadecimale di 36 caratteri.
    	 * La classe UUID in Java fornisce metodi per generare e 
    	 * manipolare UUID. 
    	 * Il metodo randomUUID() genera un UUID casuale utilizzando 
    	 * un generatore di numeri casuali
    	 * */
        return java.util.UUID.randomUUID().toString();
    }
    
    public Role checkLogged(String token) {
    	Token userToken = tokenRepository.findByToken(token);
    	User loggedUser = userRepository.findById(userToken.getUser_id()).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));
    	return loggedUser.getRole();	
    }
}



