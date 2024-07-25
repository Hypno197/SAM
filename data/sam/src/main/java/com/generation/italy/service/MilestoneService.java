package com.generation.italy.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.generation.italy.model.Milestone;
import com.generation.italy.repository.MilestoneRepository;

@Service
public class MilestoneService {

    @Autowired
    private MilestoneRepository milestoneRepository;

    public List<Milestone> getAllMilestones() {
        return milestoneRepository.findAll();
    }

    public Milestone getMilestoneById(Long id) {
        return milestoneRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Milestone not found for this id :: " + id));
    }

    public Milestone createMilestone(Milestone milestone) {
        return milestoneRepository.save(milestone);
    }

    public void deleteMilestone(Long id) {
        Milestone milestone = milestoneRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Milestone not found for this id :: " + id));
        milestoneRepository.delete(milestone);
    }

    public Milestone updateMilestone(Long id, Milestone milestoneDetails) {
        Milestone milestone = milestoneRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Milestone not found for this id :: " + id));
        milestone.setMile_name(milestoneDetails.getMile_name());
        milestone.setMile_desc(milestoneDetails.getMile_desc());
        milestone.setMile_status(milestoneDetails.getMile_status());
        milestone.setProject_id(milestoneDetails.getProject_id());
        milestone.setOwner_id(milestoneDetails.getOwner_id());
        milestone.setStart_date(milestoneDetails.getStart_date());
        milestone.setEnd_date(milestoneDetails.getEnd_date());
        milestone.setCompletion_date(milestoneDetails.getCompletion_date());
        return milestoneRepository.save(milestone);
    }
}
