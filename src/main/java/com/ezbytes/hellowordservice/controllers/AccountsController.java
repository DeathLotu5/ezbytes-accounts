package com.ezbytes.hellowordservice.controllers;

import com.ezbytes.hellowordservice.config.AccountServiceConfig;
import com.ezbytes.hellowordservice.entity.Accounts;
import com.ezbytes.hellowordservice.entity.Customer;
import com.ezbytes.hellowordservice.entity.Properties;
import com.ezbytes.hellowordservice.repository.AccountsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class AccountsController {
	
	@Autowired
	private AccountsRepository accountsRepository;

	@Autowired
	private AccountServiceConfig accountServiceConfig;

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

}