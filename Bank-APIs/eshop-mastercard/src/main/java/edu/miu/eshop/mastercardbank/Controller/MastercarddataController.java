package edu.miu.eshop.mastercardbank.Controller;


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

import edu.miu.eshop.mastercardbank.domain.Mastercarddata;
import edu.miu.eshop.mastercardbank.domain.Transaction;
import edu.miu.eshop.mastercardbank.service.MastercarddataService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/mastercard")
public class MastercarddataController {

	@Autowired
	private MastercarddataService service;
	
	
	@PostMapping("/confirm")
	public ResponseEntity<?> confirmTransaction(@RequestBody Transaction transaction) {
		Boolean confirm = service.confirmTransaction(transaction);
		return new ResponseEntity<Boolean>(confirm, HttpStatus.OK);
	
	}
	
	
	@PostMapping("/add")
	public ResponseEntity<?> addMastercard(@RequestBody Mastercarddata mastercarddata) {
		service.saveMastercarddata(mastercarddata);
		return new ResponseEntity<String>("Data inserted successfully", HttpStatus.OK);
		
	}
	
	
	@GetMapping("/getmastercard")
	public ResponseEntity<?> getMastercard() {
		List<Mastercarddata> mastercarddata = service.getMastercarddata();
		return new ResponseEntity<List<Mastercarddata>>(mastercarddata, HttpStatus.OK); 
	}
	
	
	
	@GetMapping("/getbalance")
	public ResponseEntity<?> getBalance() {
		double balance = service.getBalanceOfAccount("GROUP 3 ACCOUNT");
		return new ResponseEntity<Double>(balance, HttpStatus.OK); 
	}
	
	
}
