package edu.miu.eshop.discoverybank.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.miu.eshop.discoverybank.domain.Transaction;
import edu.miu.eshop.discoverybank.domain.Discoverydata;
import edu.miu.eshop.discoverybank.service.DiscoverydataService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("discovery")
public class DiscoverydataController {

	@Autowired
	private DiscoverydataService service;
	
	
	@PostMapping("/confirm")
	public ResponseEntity<?> confirmTransaction(@RequestBody Transaction transaction) {
		Boolean confirm = service.confirmTransaction(transaction);
		return new ResponseEntity<Boolean>(confirm, HttpStatus.OK);
	
	}
	
	
	@PostMapping("/add")
	public ResponseEntity<?> addDiscovery(@RequestBody Discoverydata discoverydata) {
		service.saveDiscoverydata(discoverydata);
		return new ResponseEntity<String>("Data inserted successfully", HttpStatus.OK);
		
	}
	
	
	@GetMapping("/getdiscovery")
	public ResponseEntity<?> getDiscovery() {
		List<Discoverydata> discoverydata = service.getDiscoverydata();
		return new ResponseEntity<List<Discoverydata>>(discoverydata, HttpStatus.OK); 
	}
	
	
	@GetMapping("/getbalance")
	public ResponseEntity<?> getBalance() {
		double balance = service.getBalanceOfAccount("GROUP 3 ACCOUNT");
		return new ResponseEntity<Double>(balance, HttpStatus.OK); 
	}
	
	
}
