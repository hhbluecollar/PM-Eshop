package edu.miu.eshop.eshopadmin.service;

// AA

import edu.miu.eshop.eshopadmin.domain.Requirement;

import java.util.List;

public interface RequirementService {
	List<Requirement> getAll();

	Requirement findById(String requirementId);

	Requirement save(Requirement requirement);

	List<Requirement> findByVendorId(String vendorId);

	Requirement edit(String requirementId, Requirement requirement);

	Requirement delete(String requirementId);

	Requirement assignEngineer(String requirementId, String employeeId);

	List<Requirement> findByEngineerId(String engineerId);
}
