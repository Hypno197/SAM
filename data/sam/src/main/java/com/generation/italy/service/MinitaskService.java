package com.generation.italy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.generation.italy.model.Minitask;
import com.generation.italy.repository.MinitaskRepository;

@Service
public class MinitaskService {

    @Autowired
    private MinitaskRepository minitaskRepository;

    public List<Minitask> getAllMinitasks() {
        return minitaskRepository.findAll();
    }

    public Minitask getMinitaskById(Long id) {
        return minitaskRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Minitask not found for this id :: " + id));
    }

    public Minitask createMinitask(Minitask minitask) {
        return minitaskRepository.save(minitask);
    }

    public void deleteMinitask(Long id) {
        Minitask minitask = minitaskRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Minitask not found for this id :: " + id));
        minitaskRepository.delete(minitask);
    }

    public Minitask updateMinitask(Long id, Minitask minitaskDetails) {
        Minitask minitask = minitaskRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Minitask not found for this id :: " + id));
        minitask.setMinitask_name(minitaskDetails.getMinitask_name());
        minitask.setMinitask_desc(minitaskDetails.getMinitask_desc());
        minitask.setMinitask_status(minitaskDetails.getMinitask_status());
        minitask.setUser_id(minitaskDetails.getUser_id());
        minitask.setStart_date(minitaskDetails.getStart_date());
        minitask.setEnd_date(minitaskDetails.getEnd_date());
        minitask.setCompletion_date(minitaskDetails.getCompletion_date());
        return minitaskRepository.save(minitask);
    }
}
