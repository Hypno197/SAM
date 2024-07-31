package com.generation.italy.config;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerInterceptor;

import com.generation.italy.controller.AuthController;
import com.generation.italy.model.Token;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class RequestInterceptor implements HandlerInterceptor { 
	
	
    // Request is intercepted by this method before reaching the Controller 
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception { 
    		System.out.println("token intercept = " +  request.getHeader("token"));
    	try { 
        	if (!request.getHeader("token").isBlank()){
        		   return true;
        	} else 
        		{
        		response.sendError(401);
        		throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token non valido");
        		}
        }
        //* If the Exception is caught, this method will return false 
        catch (Exception e) { 
        	System.err.println(e.getMessage());
        } 
     return true;
    }
}