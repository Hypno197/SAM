package com.generation.italy.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.generation.italy.model.Milestone;
import com.generation.italy.model.Project;
import com.generation.italy.model.Task;
import com.generation.italy.repository.MilestoneRepository;
import com.generation.italy.repository.ProjectRepository;
import com.generation.italy.repository.TaskRepository;

@Service
public class MilestoneService {

	@Autowired
	private MilestoneRepository milestoneRepository;
	@Autowired
	private TaskRepository taskRepository;
	@Autowired
	private ProjectRepository projectRepository;

	public List<Milestone> getAllMilestones() {
		return milestoneRepository.findAll();
	}

	public Milestone getMilestoneById(Long id) {
		return milestoneRepository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Milestone not found for this id :: " + id));
	}

	public Milestone createMilestone(Milestone milestone) {
		return milestoneRepository.save(milestone);
	}

	public void deleteMilestone(Long id) {
		Milestone milestone = milestoneRepository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Milestone not found for this id :: " + id));
		List<Task> associatedTasks = taskRepository.findByMilestone(milestone);
		for (Task t : associatedTasks)
			taskRepository.delete(t);
		milestoneRepository.delete(milestone);
	}

	public Milestone updateMilestone(Long id, Milestone milestoneDetails) {
		Milestone milestone = milestoneRepository.findById(id).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Milestone not found for this id :: " + id));
		milestone.setMile_name(milestoneDetails.getMile_name());
		milestone.setMile_desc(milestoneDetails.getMile_desc());
		milestone.setMile_icon(milestoneDetails.getMile_icon());
		milestone.setProjectID(milestoneDetails.getProjectID());
		milestone.setOwner_id(milestoneDetails.getOwner_id());
		milestone.setStart_date(milestoneDetails.getStart_date());
		milestone.setEnd_date(milestoneDetails.getEnd_date());
		milestone.setCompletion_date(milestoneDetails.getCompletion_date());
		return milestoneRepository.save(milestone);
	}

	public List<Milestone> findByProject(Long id) {
		return milestoneRepository.findByProjectID(id);
	}

	public void updateValue() {
		List<Project> projects = projectRepository.findAll();
		List<Milestone> milestones = milestoneRepository.findAll();
		Integer projectTotal = 0, projectValue = 0;
		for (Project p : projects) {
			projectTotal = 0;
			projectValue = 0;
			for (Milestone m : milestones) {
				Integer mileTotal = 0, mileValue = 0;
				List<Task> tasks = taskRepository.findByMilestone(m);
				for (Task t : tasks) {
					mileTotal += t.getValue();
					if (t.getStatus().getStatus().equalsIgnoreCase("Completata"))
						mileValue += t.getValue();
				}
				m.setMile_total(mileTotal);
				m.setMile_value(mileValue);
				if (m.getMile_total().equals(m.getMile_value()) && m.getMile_total() > 0) {
					m.setCompletion_date(LocalDate.now());
				} else if (!m.getMile_total().equals(m.getMile_value())) {
					m.setCompletion_date(null);
				}
				milestoneRepository.save(m);
				//
				if (p.getId() == m.getProjectID()) {
					projectValue += mileValue;
					projectTotal += mileTotal;
					p.setTotal(projectTotal);
					p.setValue(projectValue);
				if (p.getTotal().equals(p.getValue()) && p.getTotal() > 0) {
					p.setCompletion_date(LocalDate.now());
					projectRepository.save(p);
				} else if (p.getCompletion_date() != null && !p.getTotal().equals(p.getValue())
						&& p.getTotal() != null) {
					p.setCompletion_date(null);
					projectRepository.save(p);
				}
				}
			}
		}
	}
}
