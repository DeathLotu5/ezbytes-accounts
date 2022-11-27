package com.ezbytes.hellowordservice.controllers;

import com.ezbytes.hellowordservice.config.AccountServiceConfig;
import com.ezbytes.hellowordservice.entity.*;
import com.ezbytes.hellowordservice.repository.AccountsRepository;
import com.ezbytes.hellowordservice.service.client.CardsFeignClient;
import com.ezbytes.hellowordservice.service.client.LoansFeignClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class AccountsController {
	
	@Autowired
	private AccountsRepository accountsRepository;

	@Autowired
	private AccountServiceConfig accountServiceConfig;

	@Autowired
	private CardsFeignClient cardsFeignClient;

	@Autowired
	private LoansFeignClient loansFeignClient;

	@PostMapping("/myAccount")
	public Accounts getAccountDetails(@RequestBody Customer customer) {

		return accountsRepository.findByCustomerId(customer.getCustomerId());
	}

	@GetMapping("/account/properties")
	public String getPropertyDetails() throws JsonProcessingException {
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		Properties properties = new Properties(accountServiceConfig.getMsg(),
				accountServiceConfig.getBuildVersion(),
				accountServiceConfig.getMailDetails(),
				accountServiceConfig.getActiveBranches()
				);
		return ow.writeValueAsString(properties);
	}

	@PostMapping("/myCustomerDetails")
	@CircuitBreaker(name = "detailsForCustomerSupportApp")
	public MyCustomerDetails myCustomerDetails(@RequestBody Customer customer) {
		Accounts account = accountsRepository.findByCustomerId(customer.getCustomerId());
		List<LoansEntity> loans = loansFeignClient.getLoanDetails(customer);
		List<CardEntity> cards = cardsFeignClient.getCardDetails(customer);

		MyCustomerDetails myCustomerDetails = new MyCustomerDetails();
		myCustomerDetails.setAccounts(account);
		myCustomerDetails.setLoans(loans);
		myCustomerDetails.setCards(cards);
		return myCustomerDetails;
	}

}