package com.generation.italy.config;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerInterceptor;

import com.generation.italy.controller.AuthController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class RequestInterceptor implements HandlerInterceptor { 
	
	
    // Request is intercepted by this method before reaching the Controller 
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception { 
  
        //* Business logic just when the request is received and intercepted by this interceptor before reaching the controller 
        try { 
//        	String token = request.getHeader("token");
//        	if(token != null) {
//        		Role role = tokenService.checkLogged(token);
//        		if (role == null)
//        			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "utente non loggato");
//          	} else 
//    		{
//    		response.sendError(401);
//    		throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "utente non loggato");
//    		}
        	System.out.println("interceptor" + request.getSession().getAttribute("logged"));
        	if (request.getSession().getAttribute("logged")== "true") {
        		   return true;
        	} else 
        		{
        		response.sendError(401);
        		throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "utente non loggato");
        		}
        }
        //* If the Exception is caught, this method will return false 
        catch (Exception e) { 
        	System.err.println(e.getMessage());
        } 
     return true;
    }
}