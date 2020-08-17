package edu.miu.eshop.eshopadmin.controller;

// AA

import edu.miu.eshop.eshopadmin.domain.Requirement;
import edu.miu.eshop.eshopadmin.service.RequirementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders="*")
@RestController
@RequestMapping("/requirements")
public class RequirementController {

	@Autowired
	private RequirementService requirementService;

	@GetMapping
	public ResponseEntity<List<Requirement>> findAllRequirement() {
		return new ResponseEntity<>(requirementService.getAll(), HttpStatus.OK);
	}

	@GetMapping("/{requirementId}")
	public ResponseEntity<Requirement> findById(@PathVariable("requirementId") String requirementId) {
		return new ResponseEntity<>(requirementService.findById(requirementId), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Requirement> addRequirement(@RequestBody Requirement requirement) {
		return new ResponseEntity<>(requirementService.save(requirement), HttpStatus.OK);
	}
	@GetMapping("/vendor/{vendorId}")
	public ResponseEntity<List<Requirement>> getByVendor(@PathVariable("vendorId") String vendorId){
		return new ResponseEntity<>(requirementService.findByVendorId(vendorId), HttpStatus.OK);
	}

	@GetMapping("/engineer/{engineerId}")
	public ResponseEntity<List<Requirement>> getByEngineer(@PathVariable("engineerId") String engineerId){
		return new ResponseEntity<>(requirementService.findByEngineerId(engineerId), HttpStatus.OK);
	}

	@PutMapping("/{requirementId}")
	public ResponseEntity<Requirement> editRequirement(@PathVariable("requirementId") String requirementId, @RequestBody Requirement requirement) {
		return new ResponseEntity<>(requirementService.edit(requirementId, requirement), HttpStatus.OK);
	}

	@DeleteMapping("/{requirementId}")
	public ResponseEntity<Requirement> deleteRequirement(@PathVariable("requirementId") String requirementId) {
		return new ResponseEntity<>(requirementService.delete(requirementId), HttpStatus.OK);
	}
	@PatchMapping("/assign/{requirementId}/{employeeId}")
	public ResponseEntity<Requirement> assignEngineer(@PathVariable("requirementId") String requirementId, @PathVariable("employeeId") String employeeId){
		return new ResponseEntity<>(requirementService.assignEngineer(requirementId, employeeId), HttpStatus.OK);
	}
}
