package com.ezbytes.hellowordservice.service.client;

import com.ezbytes.hellowordservice.entity.CardEntity;
import com.ezbytes.hellowordservice.entity.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("cards")
public interface CardsFeignClient {
    @RequestMapping(method = RequestMethod.POST, value = "myCards", consumes = "application/json")
    List<CardEntity> getCardDetails(@RequestBody Customer customer);
}
