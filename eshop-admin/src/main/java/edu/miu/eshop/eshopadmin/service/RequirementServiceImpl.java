package edu.miu.eshop.eshopadmin.service;

// AA

import edu.miu.eshop.eshopadmin.domain.Requirement;
import edu.miu.eshop.eshopadmin.domain.RequirementStatus;
import edu.miu.eshop.eshopadmin.repository.RequirementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RequirementServiceImpl implements RequirementService {
	
	@Autowired
	private RequirementRepository requirementRepository;

	@Override
	public List<Requirement> getAll() {
		return requirementRepository.findAll();
	}

	@Override
	public Requirement findById(String requirementId) {
		return requirementRepository.findById(requirementId).get();
	}

	@Override
	public Requirement save(Requirement requirement) {
		requirement.setCreatedDate(LocalDate.now());
		return requirementRepository.save(requirement);
	}

	@Override
	public List<Requirement> findByVendorId(String vendorId) {
		return requirementRepository.findByVendorId(vendorId);
	}

	@Override
	public List<Requirement> findByEngineerId(String engineerId) {
		return requirementRepository.findByEngineerId(engineerId);
	}

	@Override
	public Requirement edit(String requirementId, Requirement editedRequirement) {
		Requirement requirement = requirementRepository.findById(requirementId).get();

		requirement.setSubject(editedRequirement.getSubject());
		requirement.setLastModifiedDate(LocalDate.now());
		requirement.setDueDate(editedRequirement.getDueDate());
		requirement.setStatus(editedRequirement.getStatus());
		requirement.setDescription(editedRequirement.getDescription());
		requirement.setComments(editedRequirement.getComments());
		requirement.setEngineer(editedRequirement.getEngineer());

		if(editedRequirement.getStatus().equals(RequirementStatus.CLOSED))
			requirement.setEndedDate(LocalDate.now());

		return requirementRepository.save(requirement);
	}

	@Override
	public Requirement delete(String requirementId) {
		Requirement requirement = requirementRepository.findById(requirementId).get();
		requirement.setStatus(RequirementStatus.DELETED);
		return requirementRepository.save(requirement);
	}

	@Override
	public Requirement assignEngineer(String requirementId, String employeeId) {
		Requirement requirement = requirementRepository.findById(requirementId).get();
		requirement.setEngineer(employeeId);
		return requirementRepository.save(requirement);
	}
}
