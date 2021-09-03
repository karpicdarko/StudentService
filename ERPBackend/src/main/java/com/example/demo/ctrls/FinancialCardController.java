package com.example.demo.ctrls;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.jpa.FinancialCard;
import com.example.demo.service.FinancialCardService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class FinancialCardController {
	
	@Autowired
	private FinancialCardService financialCardService;
	
	@GetMapping("/financialCard")
	public Iterable<FinancialCard> getFinancialCards() {
		return this.financialCardService.findAll();
	}
	
	@GetMapping("/financialCardSId/{id}")
	public Optional<FinancialCard> geFinancialCardByStudentId(@PathVariable("id") Integer id) {
		return this.financialCardService.findByStudentId(id);
	}
	
	@GetMapping("/financialCardMoney/{id}")
	public void insertMoney(@PathVariable("id") Integer id) {
		this.financialCardService.insertMoneyInAccout(id);
	}
	
	
	@PostMapping("/financialCard")
	public FinancialCard insertFC(@RequestBody FinancialCard fc) {
		return this.financialCardService.saveFC(fc);
	}
	
	@PutMapping("/financialCard")
	public FinancialCard updateFC(@RequestBody FinancialCard fc) {
		return this.financialCardService.saveFC(fc);
	}
	
	@DeleteMapping("/financialCard/{id}")
	public void removeFC(@PathVariable("id") Integer id) {
		this.financialCardService.deleteFCById(id);
	}
}
