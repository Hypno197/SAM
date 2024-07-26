package com.generation.italy.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.generation.italy.model.LogEntry;
import com.generation.italy.repository.LogEntryRepository;

@Service
public class LogEntryService {

    @Autowired
    private LogEntryRepository logEntryRepository;

    public List<LogEntry> getAllLogEntries() {
        return logEntryRepository.findAll();
    }

    public LogEntry getLogEntryById(Long id) {
        return logEntryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "LogEntry not found for this id :: " + id));
    }

    public LogEntry createLogEntry(LogEntry logEntry) {
        return logEntryRepository.save(logEntry);
    }

    public void deleteLogEntry(Long id) {
        LogEntry logEntry = logEntryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "LogEntry not found for this id :: " + id));
        logEntryRepository.delete(logEntry);
    }

    public LogEntry updateLogEntry(Long id, LogEntry logEntryDetails) {
        LogEntry logEntry = logEntryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "LogEntry not found for this id :: " + id));
        logEntry.setUser_id(logEntryDetails.getUser_id());
        logEntry.setMilestone_id(logEntryDetails.getMilestone_id());
        logEntry.setTask_id(logEntryDetails.getTask_id());
        logEntry.setMinitask_id(logEntryDetails.getMinitask_id());
        logEntry.setMessage(logEntryDetails.getMessage());
        logEntry.setLogType(logEntryDetails.getLogType());
        logEntry.setLogAction(logEntryDetails.getLogAction());
        logEntry.setTime(logEntryDetails.getTime());
        return logEntryRepository.save(logEntry);
    }
}
