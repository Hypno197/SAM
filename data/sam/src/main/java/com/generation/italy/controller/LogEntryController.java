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

import com.generation.italy.model.LogEntry;
import com.generation.italy.service.LogEntryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/logentries")
@CrossOrigin
public class LogEntryController {

    @Autowired
    private LogEntryService logEntryService;

    @GetMapping
    public List<LogEntry> getAllLogEntries() {
        return logEntryService.getAllLogEntries();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LogEntry> getLogEntryById(@PathVariable(value = "id") Long logEntryId) {
        LogEntry logEntry = logEntryService.getLogEntryById(logEntryId);
        return ResponseEntity.ok().body(logEntry);
    }

    @PostMapping
    public LogEntry createLogEntry(@Valid @RequestBody LogEntry logEntry) {
        return logEntryService.createLogEntry(logEntry);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LogEntry> updateLogEntry(@PathVariable(value = "id") Long logEntryId, @Valid @RequestBody LogEntry logEntryDetails) {
        LogEntry logEntry = logEntryService.getLogEntryById(logEntryId);
        LogEntry updatedLogEntry = logEntryService.updateLogEntry(logEntryId, logEntryDetails);
        return ResponseEntity.ok().body(updatedLogEntry);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLogEntry(@PathVariable(value = "id") Long logEntryId) {
        logEntryService.deleteLogEntry(logEntryId);
        return ResponseEntity.ok().<Void>build();
    }
}
