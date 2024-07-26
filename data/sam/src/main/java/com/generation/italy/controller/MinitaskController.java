package com.generation.italy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.italy.model.Minitask;
import com.generation.italy.service.MinitaskService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/minitasks")
@CrossOrigin
public class MinitaskController {

    @Autowired
    private MinitaskService minitaskService;

    @GetMapping
    public List<Minitask> getAllMinitasks() {
        return minitaskService.getAllMinitasks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Minitask> getMinitaskById(@PathVariable(value = "id") Long minitaskId) {
        Minitask minitask = minitaskService.getMinitaskById(minitaskId);
        return ResponseEntity.ok().body(minitask);
    }

    @PostMapping
    public Minitask createMinitask(@Valid @RequestBody Minitask minitask) {
        return minitaskService.createMinitask(minitask);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Minitask> updateMinitask(@PathVariable(value = "id") Long minitaskId, @Valid @RequestBody Minitask minitaskDetails) {
        Minitask minitask = minitaskService.getMinitaskById(minitaskId);
        Minitask updatedMinitask = minitaskService.updateMinitask(minitaskId, minitaskDetails);
        return ResponseEntity.ok().body(updatedMinitask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMinitask(@PathVariable(value = "id") Long minitaskId) {
        minitaskService.deleteMinitask(minitaskId);
        return ResponseEntity.ok().<Void>build();
    }
}
